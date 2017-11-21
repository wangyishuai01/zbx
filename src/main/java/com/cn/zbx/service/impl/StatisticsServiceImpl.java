package com.cn.zbx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.StatisticsMapper;
import com.cn.zbx.pojo.Statistics;
import com.cn.zbx.service.StatisticsService;

@Service(value="StatisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	StatisticsMapper statisticsMapper;

	@Override
	public int selectCountBySelectParam(Statistics record) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
