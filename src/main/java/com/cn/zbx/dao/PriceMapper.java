package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.Price;

public interface PriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
    
    List<Price> selectPriceBySelectParam(Price record);
    
    int selectCountBySelectParam(Price record);
}