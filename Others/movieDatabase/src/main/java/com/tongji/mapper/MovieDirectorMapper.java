package com.tongji.mapper;

import com.tongji.model.MovieDirector;
import com.tongji.model.MovieDirectorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MovieDirectorMapper {
    long countByExample(MovieDirectorExample example);

    int deleteByExample(MovieDirectorExample example);

    int deleteByPrimaryKey(@Param("movieId") Integer movieId, @Param("directorId") Integer directorId);

    int insert(MovieDirector record);

    int insertSelective(MovieDirector record);

    List<MovieDirector> selectByExample(MovieDirectorExample example);

    int updateByExampleSelective(@Param("record") MovieDirector record, @Param("example") MovieDirectorExample example);

    int updateByExample(@Param("record") MovieDirector record, @Param("example") MovieDirectorExample example);
}