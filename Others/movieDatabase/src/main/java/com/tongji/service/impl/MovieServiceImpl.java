package com.tongji.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.dto.SearchInfo;
import com.tongji.mapper.*;
import com.tongji.model.Actor;
import com.tongji.model.Director;
import com.tongji.model.Movie;
import com.tongji.model.MovieExample;
import com.tongji.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private DirectorMapper directorMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private MovieDirectorMapper movieDirectorMapper;

    @Autowired
    private MovieActorMapper movieActorMapper;


    @Autowired
    private MovieGenreMapper movieGenreMapper;

    @Autowired
    private DirectorActorMapper directorActorMapper;

    @Override
    public List<Movie> searchMovie(SearchInfo searchInfo) {
        //按照时间查询
        StringBuilder timeSql = new StringBuilder("select movie_id\n" +
                "from movie\n" +
                "where time_id in\n" +
                "( select time_id from time where 1 = 1 ");
        //没传入的信息则不用查
        if (searchInfo.getYear() != -2) {
            timeSql.append("and time.year = ").append(searchInfo.getYear());
        }
        if (searchInfo.getMonth() != -2) {
            timeSql.append(" and time.month = ").append(searchInfo.getMonth());
        }
        if (searchInfo.getDay() != -2) {
            timeSql.append(" and time.day = ").append(searchInfo.getDay());
        }
        if (searchInfo.getWeek() != -2) {
            timeSql.append(" and time.week = ").append(searchInfo.getWeek());
        }
        //季度查询
        timeSql.append(" and time.month in (");
        for (int i = 0; i < searchInfo.getQuarterList().size(); i++) {
            timeSql.append(searchInfo.getQuarterList().get(i));
            if (i != searchInfo.getQuarterList().size() - 1) {
                timeSql.append(",");
            }
        }

        timeSql.append("))");


        List<Integer> moviesIdByTime = null;
        List<Integer> moviesIdByGenre = null;
        List<Integer> moviesIdByDirector = null;
        List<Integer> moviesIdByTitle = null;
        List<Integer> moviesIdByActor = null;

        System.out.println(timeSql.toString());

        moviesIdByTime = jdbcTemplate.query(timeSql.toString(), new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });


        if (searchInfo.getTitle() != null) {
            String sql = "select movie_id from movie where title like ?";
            moviesIdByTitle = jdbcTemplate.query(sql, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            }, "%" + searchInfo.getTitle() + "%");
        }
        if (searchInfo.getDirector() != null) {
            String sql = "select movie_id\n" +
                    "from movie_director\n" +
                    "where director_id in (select director.director_id from director where director.name = ?)\n";
            moviesIdByDirector = jdbcTemplate.query(sql, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            }, searchInfo.getDirector());
        }

        if (searchInfo.getActor() != null) {
            String sql = "select movie_id\n" +
                    "from movie_actor\n" +
                    "where actor_id in (select actor_id from actor where actor.name = ?)";
            //查询主演
            if (searchInfo.isStarring()) {
                sql += " and is_supporting = false";
            }
            else {
                sql += " and is_supporting = true";
            }
            moviesIdByActor = jdbcTemplate.query(sql, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            }, searchInfo.getActor());
        }


        if (searchInfo.getGenre() != null) {
            String sql = "select movie_id from movie_genre where genre_id = (select genre_id from genre where genre.name = ?)";
            moviesIdByGenre = jdbcTemplate.query(sql, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            }, searchInfo.getGenre());
//            List<Director> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Director>(Director.class));
        }


//        if (moviesIdByTime != null) {
//            criteria.andMovieIdIn(moviesIdByTime);
//        }
//        if (moviesIdByGenre != null) {
//            criteria.andMovieIdIn(moviesIdByGenre);
//        }
//        if (moviesIdByTitle != null) {
//            criteria.andMovieIdIn(moviesIdByTitle);
//        }
//        if (moviesIdByActor != null) {
//            criteria.andMovieIdIn(moviesIdByActor);
//        }
//        if (moviesIdByDirector != null) {
//            criteria.andMovieIdIn(moviesIdByDirector);
//        }

        if (moviesIdByGenre != null) {
            moviesIdByTime.retainAll(moviesIdByGenre);
        }
        if (moviesIdByTitle != null) {
            moviesIdByTime.retainAll(moviesIdByTitle);
        }
        if (moviesIdByActor != null) {
            moviesIdByTime.retainAll(moviesIdByActor);
        }
        if (moviesIdByDirector != null) {
            moviesIdByTime.retainAll(moviesIdByDirector);
        }

        MovieExample movieExample = new MovieExample();
        MovieExample.Criteria criteria = movieExample.createCriteria();
        criteria.andMovieIdIn(moviesIdByTime);

        StringBuilder sql = new StringBuilder("select * from movie where star >= ? and movie_id in (");
        for (int i = 0; i < moviesIdByTime.size(); i++) {
            sql.append(moviesIdByTime.get(i));
            if (i != moviesIdByTime.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        sql.append("limit ?,?");

        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize());//分页相关
        List<Movie> movies = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Movie>(Movie.class)
                , searchInfo.getScore(), (searchInfo.getPageNum() - 1) * searchInfo.getPageSize(), searchInfo.getPageSize());


//        boolean hasPositive todo 有正面评论


//        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize());//分页相关
//        //        如需检索的字段中包含大字段类型时，必须用selectByExampleWithBLOBs
//        List<Movie> movies = movieMapper.selectByExample(movieExample);
        return movies;
    }

    @Override
    public List<Director> directors(Integer pageNum, Integer pageSize, String name) {
        String sql = "select * from director where name like ? limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Director>(Director.class)
                , "%" + name + "%", (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<Actor> actors(Integer pageNum, Integer pageSize, String name) {
        String sql = "select * from actor where name like ? limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Actor>(Actor.class)
                , "%" + name + "%", (pageNum - 1) * pageSize, pageSize);
    }
}
