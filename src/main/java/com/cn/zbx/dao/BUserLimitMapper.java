package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.BUserLimit;
import com.cn.zbx.pojo.Menu;

public interface BUserLimitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BUserLimit record);

    int insertSelective(BUserLimit record);

    BUserLimit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BUserLimit record);

    int updateByPrimaryKey(BUserLimit record);
    
    List<Menu> queryUserMenuByUserId(Integer userId);
}