package com.tongji.mapper;

import com.tongji.model.Actor;
import com.tongji.model.ActorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActorMapper {
    long countByExample(ActorExample example);

    int deleteByExample(ActorExample example);

    int deleteByPrimaryKey(Integer actorId);

    int insert(Actor record);

    int insertSelective(Actor record);

    List<Actor> selectByExampleWithBLOBs(ActorExample example);

    List<Actor> selectByExample(ActorExample example);

    Actor selectByPrimaryKey(Integer actorId);

    int updateByExampleSelective(@Param("record") Actor record, @Param("example") ActorExample example);

    int updateByExampleWithBLOBs(@Param("record") Actor record, @Param("example") ActorExample example);

    int updateByExample(@Param("record") Actor record, @Param("example") ActorExample example);

    int updateByPrimaryKeySelective(Actor record);

    int updateByPrimaryKeyWithBLOBs(Actor record);

    int updateByPrimaryKey(Actor record);
}