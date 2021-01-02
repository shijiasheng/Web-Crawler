package com.tongji.mapper;

import com.tongji.model.MovieActor;
import com.tongji.model.MovieActorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MovieActorMapper {
    long countByExample(MovieActorExample example);

    int deleteByExample(MovieActorExample example);

    int deleteByPrimaryKey(@Param("movieId") Integer movieId, @Param("actorId") Integer actorId);

    int insert(MovieActor record);

    int insertSelective(MovieActor record);

    List<MovieActor> selectByExample(MovieActorExample example);

    MovieActor selectByPrimaryKey(@Param("movieId") Integer movieId, @Param("actorId") Integer actorId);

    int updateByExampleSelective(@Param("record") MovieActor record, @Param("example") MovieActorExample example);

    int updateByExample(@Param("record") MovieActor record, @Param("example") MovieActorExample example);

    int updateByPrimaryKeySelective(MovieActor record);

    int updateByPrimaryKey(MovieActor record);
}