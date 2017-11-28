package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.BLog;
import com.cn.zbx.vo.LogVO;

/**
 * @Class Name IBUserLimitService
 * @Author zhangdl
 * @Create In 2017-11-21
 */

public interface IStatisticsService {

	int selectCountBySelectParam(BLog record);
	
	List selectDateAndCount(LogVO record);
	
}
