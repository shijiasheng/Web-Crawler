package com.tongji.mapper;

import com.tongji.model.Productdata;
import com.tongji.model.ProductdataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductdataMapper {
    long countByExample(ProductdataExample example);

    int deleteByExample(ProductdataExample example);

    int deleteByPrimaryKey(String id);

    int insert(Productdata record);

    int insertSelective(Productdata record);

    List<Productdata> selectByExample(ProductdataExample example);

    Productdata selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Productdata record, @Param("example") ProductdataExample example);

    int updateByExample(@Param("record") Productdata record, @Param("example") ProductdataExample example);

    int updateByPrimaryKeySelective(Productdata record);

    int updateByPrimaryKey(Productdata record);
}