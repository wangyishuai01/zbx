package com.cn.zbx.service;


import com.cn.zbx.pojo.BUser;

/**
 * @Class Name IBUserService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IBUserService {

    BUser queryUserByName(String userCode);
	
}
