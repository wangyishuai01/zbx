package com.cn.zbx.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.BUserMapper;
import com.cn.zbx.pojo.BUser;
import com.cn.zbx.service.IBUserService;
import com.cn.zbx.util.MD5Util;

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

	/**
	 * 根据用户名修改密码
	 */
	@Override
	public Map<String, Object> updateByPrimaryKeySelective(String userCode, String password) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		BUser bUser = bUserMapper.queryUserByName(userCode);
		bUser.setPassword(MD5Util.MD5Encode(password));
		bUser.setModifydate(new Date());
		int num = bUserMapper.updateByPrimaryKeySelective(bUser);
		if(num <= 0){
			resultMap.put("errorMsg", "数据提交失败！");
			resultMap.put("success", false);
			return resultMap;
		}
		resultMap.put("success", true);
		return resultMap;
	}

}
