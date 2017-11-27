package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.Statistics;
import com.cn.zbx.vo.StatisticsVO;

/**
 * @Class Name IBUserLimitService
 * @Author zhangdl
 * @Create In 2017-11-21
 */

public interface IStatisticsService {

	int selectCountBySelectParam(Statistics record);
	
	List selectDateAndCount(StatisticsVO record);
	
}
