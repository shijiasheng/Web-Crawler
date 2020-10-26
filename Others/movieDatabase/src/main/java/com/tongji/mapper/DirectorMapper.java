package com.tongji.mapper;

import com.tongji.model.Director;
import com.tongji.model.DirectorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirectorMapper {
    long countByExample(DirectorExample example);

    int deleteByExample(DirectorExample example);

    int deleteByPrimaryKey(String name);

    int insert(Director record);

    int insertSelective(Director record);

    List<Director> selectByExampleWithBLOBs(DirectorExample example);

    List<Director> selectByExample(DirectorExample example);

    Director selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") Director record, @Param("example") DirectorExample example);

    int updateByExampleWithBLOBs(@Param("record") Director record, @Param("example") DirectorExample example);

    int updateByExample(@Param("record") Director record, @Param("example") DirectorExample example);

    int updateByPrimaryKeySelective(Director record);

    int updateByPrimaryKeyWithBLOBs(Director record);
}