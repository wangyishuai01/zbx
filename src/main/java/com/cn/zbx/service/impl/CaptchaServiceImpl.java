package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.CaptchaDao;
import com.cn.zbx.pojo.Captcha;
import com.cn.zbx.service.ICaptchaService;

/**
 * @Class Name CaptchaServiceImpl
 * @Author zhangdl
 * @Create In 2016-8-22
 */
@Service(value="captchaService")
public class CaptchaServiceImpl implements ICaptchaService {
	
	@Autowired
	CaptchaDao captchaMapper;
	
	@Override
	public List<Captcha> selectBySelective(Captcha record){
		return captchaMapper.selectBySelective(record);
	}
	
	@Override
	public int selectCountBySelective(Captcha record){
		return captchaMapper.selectCountBySelective(record);
	}
}
