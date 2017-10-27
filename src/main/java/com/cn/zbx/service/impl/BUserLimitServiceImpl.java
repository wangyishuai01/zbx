package com.cn.zbx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.BUserLimitMapper;
import com.cn.zbx.pojo.BUser;
import com.cn.zbx.pojo.Menu;
import com.cn.zbx.service.IBUserLimitService;


@Service(value="bUserLimitService")
public class BUserLimitServiceImpl implements IBUserLimitService {
	
	@Autowired
	BUserLimitMapper bUserLimitMapper;

	@Override
	public List<Menu> queryUserMenuByUser(BUser user) {
		// TODO Auto-generated method stub
		return bUserLimitMapper.queryUserMenuByUserId(user.getId());
	}

}
