package com.tongji.mapper;

import com.tongji.model.Genre;
import com.tongji.model.GenreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenreMapper {
    long countByExample(GenreExample example);

    int deleteByExample(GenreExample example);

    int deleteByPrimaryKey(Integer genreId);

    int insert(Genre record);

    int insertSelective(Genre record);

    List<Genre> selectByExampleWithBLOBs(GenreExample example);

    List<Genre> selectByExample(GenreExample example);

    Genre selectByPrimaryKey(Integer genreId);

    int updateByExampleSelective(@Param("record") Genre record, @Param("example") GenreExample example);

    int updateByExampleWithBLOBs(@Param("record") Genre record, @Param("example") GenreExample example);

    int updateByExample(@Param("record") Genre record, @Param("example") GenreExample example);

    int updateByPrimaryKeySelective(Genre record);

    int updateByPrimaryKeyWithBLOBs(Genre record);

    int updateByPrimaryKey(Genre record);
}