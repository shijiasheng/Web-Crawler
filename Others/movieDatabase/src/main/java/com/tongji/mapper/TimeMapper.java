package com.tongji.mapper;

import com.tongji.model.Time;
import com.tongji.model.TimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TimeMapper {
    long countByExample(TimeExample example);

    int deleteByExample(TimeExample example);

    int deleteByPrimaryKey(Integer timeId);

    int insert(Time record);

    int insertSelective(Time record);

    List<Time> selectByExampleWithBLOBs(TimeExample example);

    List<Time> selectByExample(TimeExample example);

    Time selectByPrimaryKey(Integer timeId);

    int updateByExampleSelective(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByExampleWithBLOBs(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByExample(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByPrimaryKeySelective(Time record);

    int updateByPrimaryKeyWithBLOBs(Time record);

    int updateByPrimaryKey(Time record);
}