package com.cn.zbx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.BUserMapper;
import com.cn.zbx.pojo.BUser;
import com.cn.zbx.service.IBUserService;

@Service(value="bUserService")
public class BUserServiceImpl implements IBUserService {
	
	@Autowired
	BUserMapper bUserMapper;

	/**
	 * 根据用户名查询用户信息
	 */
	@Override
	public BUser queryUserByName(String userCode) {
		// TODO Auto-generated method stub
		return bUserMapper.queryUserByName(userCode);
	}

}
