package com.cn.zbx.dao;

import com.cn.zbx.pojo.KeyRelation;

public interface KeyRelationMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteBySelective(KeyRelation record);

    int insert(KeyRelation record);

    int insertSelective(KeyRelation record);

    KeyRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KeyRelation record);

    int updateByPrimaryKey(KeyRelation record);
}