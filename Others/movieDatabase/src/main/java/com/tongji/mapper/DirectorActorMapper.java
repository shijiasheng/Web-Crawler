package com.tongji.mapper;

import com.tongji.model.DirectorActor;
import com.tongji.model.DirectorActorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirectorActorMapper {
    long countByExample(DirectorActorExample example);

    int deleteByExample(DirectorActorExample example);

    int deleteByPrimaryKey(@Param("directorId") Integer directorId, @Param("actorId") Integer actorId);

    int insert(DirectorActor record);

    int insertSelective(DirectorActor record);

    List<DirectorActor> selectByExample(DirectorActorExample example);

    DirectorActor selectByPrimaryKey(@Param("directorId") Integer directorId, @Param("actorId") Integer actorId);

    int updateByExampleSelective(@Param("record") DirectorActor record, @Param("example") DirectorActorExample example);

    int updateByExample(@Param("record") DirectorActor record, @Param("example") DirectorActorExample example);

    int updateByPrimaryKeySelective(DirectorActor record);

    int updateByPrimaryKey(DirectorActor record);
}