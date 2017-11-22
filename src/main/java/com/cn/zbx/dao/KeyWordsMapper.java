package com.cn.zbx.dao;

import com.cn.zbx.pojo.KeyWords;

public interface KeyWordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KeyWords record);

    int insertSelective(KeyWords record);

    KeyWords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KeyWords record);

    int updateByPrimaryKey(KeyWords record);
}