package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.Captcha;

/**
 * @Class Name ICaptchaService
 * @Author zhangdl
 * @Create In 2016-8-22
 */

public interface ICaptchaService {

	List<Captcha> selectBySelective(Captcha record);

	int selectCountBySelective(Captcha record);
	
}
