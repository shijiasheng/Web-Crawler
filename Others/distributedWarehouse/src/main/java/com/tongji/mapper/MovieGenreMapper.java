package com.tongji.mapper;

import com.tongji.model.MovieGenre;
import com.tongji.model.MovieGenreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MovieGenreMapper {
    long countByExample(MovieGenreExample example);

    int deleteByExample(MovieGenreExample example);

    int deleteByPrimaryKey(@Param("movieId") Integer movieId, @Param("genreId") Integer genreId);

    int insert(MovieGenre record);

    int insertSelective(MovieGenre record);

    List<MovieGenre> selectByExample(MovieGenreExample example);

    int updateByExampleSelective(@Param("record") MovieGenre record, @Param("example") MovieGenreExample example);

    int updateByExample(@Param("record") MovieGenre record, @Param("example") MovieGenreExample example);
}