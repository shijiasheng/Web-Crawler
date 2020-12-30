package com.tongji.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.dto.SearchInfo;
import com.tongji.mapper.*;
import com.tongji.model.Movie;
import com.tongji.model.MovieExample;
import com.tongji.model.Time;
import com.tongji.model.TimeExample;
import com.tongji.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
        StringBuilder timeSql = new StringBuilder("select movie_id from user where 1 = 1 and ");
        TimeExample timeExample = new TimeExample();
        TimeExample.Criteria timeExampleCriteria = timeExample.createCriteria();
        //没传入的信息则不用查
        if (searchInfo.getYear() != -2) {
            timeExampleCriteria.andYearEqualTo(searchInfo.getYear());

        }
        if (searchInfo.getMonth() != -2) {
            timeExampleCriteria.andMonthEqualTo(searchInfo.getMonth());
        }
        if (searchInfo.getDay() != -2) {
            timeExampleCriteria.andDayEqualTo(searchInfo.getDay());
        }
        if (searchInfo.getWeek() != -2) {
            timeExampleCriteria.andWeekEqualTo(searchInfo.getWeek());
        }
        //季度查询
        timeExampleCriteria.andMonthIn(searchInfo.getQuarterList());
        List<Time> times = timeMapper.selectByExample(timeExample);
        System.out.println(times);

        //得到时间的Id
        List<Integer> timesId = times.stream()
                .map(Time::getTimeId)
                .collect(Collectors.toList());


        List<Integer> moviesIdByTime = null;
        List<Integer> moviesIdByGenre = null;
        List<Integer> moviesIdByDirector = null;
        List<Integer> moviesIdByTitle = null;
        List<Integer> moviesIdByActor = null;

        if (timesId != null) {
            String sql = "";
            moviesIdByTime = jdbcTemplate.query(sql, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            }, searchInfo.getDirector());

        }

        MovieExample movieExample = new MovieExample();
        MovieExample.Criteria criteria = movieExample.createCriteria();
        if (searchInfo.getTitle() != null) {
            String sql = "select movie_id from movie where title like '%?%'";
            moviesIdByTitle = jdbcTemplate.query(sql, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            }, searchInfo.getTitle());
        }
        if (searchInfo.getDirector() != null) {
            String sql = "select movie_id\n" +
                    "from movie_director\n" +
                    "where director_id in (select director.director_id from director where director.name = 'Phillip Guzman')\n";
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
                    "where actor_id in (select actor_id from actor where actor.name = '?')";
            //查询主演
            if (searchInfo.isStarring()) {
                sql += " and is_supporting = 1";
            }
            else {
                sql += " and is_supporting = 0";
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

//        Double score
        criteria.andStarGreaterThanOrEqualTo(searchInfo.getScore());
        if (moviesIdByTime != null) {
            criteria.andMovieIdIn(moviesIdByTime);
        }
        if (moviesIdByGenre != null) {
            criteria.andMovieIdIn(moviesIdByGenre);
        }
        if (moviesIdByTitle != null) {
            criteria.andMovieIdIn(moviesIdByTitle);
        }
        if (moviesIdByActor != null) {
            criteria.andMovieIdIn(moviesIdByActor);
        }
        if (moviesIdByDirector != null) {
            criteria.andMovieIdIn(moviesIdByDirector);
        }

//        boolean hasPositive todo 有正面评论

        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize());//分页相关
//        如需检索的字段中包含大字段类型时，必须用selectByExampleWithBLOBs
        List<Movie> movies = movieMapper.selectByExampleWithBLOBs(movieExample);
        return movies;
    }
}
