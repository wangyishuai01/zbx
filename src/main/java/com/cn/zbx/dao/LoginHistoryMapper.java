package com.cn.zbx.dao;

import com.cn.zbx.pojo.LoginHistory;

public interface LoginHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginHistory record);

    int insertSelective(LoginHistory record);

    LoginHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginHistory record);

    int updateByPrimaryKey(LoginHistory record);
}