package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.BUser;
import com.cn.zbx.pojo.Menu;

/**
 * @Class Name IBUserLimitService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IBUserLimitService {

    List<Menu> queryUserMenuByUser(BUser user);
	
}
