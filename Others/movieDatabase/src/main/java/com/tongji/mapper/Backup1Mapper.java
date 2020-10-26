package com.tongji.mapper;

import com.tongji.model.Backup1;
import com.tongji.model.Backup1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Backup1Mapper {
    long countByExample(Backup1Example example);

    int deleteByExample(Backup1Example example);

    int deleteByPrimaryKey(String id);

    int insert(Backup1 record);

    int insertSelective(Backup1 record);

    List<Backup1> selectByExample(Backup1Example example);

    Backup1 selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Backup1 record, @Param("example") Backup1Example example);

    int updateByExample(@Param("record") Backup1 record, @Param("example") Backup1Example example);

    int updateByPrimaryKeySelective(Backup1 record);

    int updateByPrimaryKey(Backup1 record);
}