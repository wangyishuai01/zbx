package com.cn.zbx.dao;

import com.cn.zbx.pojo.BUser;

public interface BUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BUser record);

    int insertSelective(BUser record);

    BUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BUser record);

    int updateByPrimaryKey(BUser record);
    
    BUser queryUserByName(String userCode);
}