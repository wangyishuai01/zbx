package com.cn.zbx.service;


import com.cn.zbx.pojo.Statistics;

/**
 * @Class Name IBUserLimitService
 * @Author zhangdl
 * @Create In 2017-11-21
 */

public interface StatisticsService {

	int selectCountBySelectParam(Statistics record);
	
}
