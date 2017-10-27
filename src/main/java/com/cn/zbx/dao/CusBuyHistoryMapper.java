package com.cn.zbx.dao;

import com.cn.zbx.pojo.CusBuyHistory;

public interface CusBuyHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CusBuyHistory record);

    int insertSelective(CusBuyHistory record);

    CusBuyHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CusBuyHistory record);

    int updateByPrimaryKey(CusBuyHistory record);
}