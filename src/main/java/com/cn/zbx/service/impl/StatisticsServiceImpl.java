package com.cn.zbx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.StatisticsMapper;
import com.cn.zbx.pojo.Statistics;
import com.cn.zbx.service.IStatisticsService;
import com.cn.zbx.vo.StatisticsVO;

@Service(value="StatisticsService")
public class StatisticsServiceImpl implements IStatisticsService {
	
	@Autowired
	StatisticsMapper statisticsMapper;

	@Override
	public int selectCountBySelectParam(Statistics record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StatisticsVO> selectDateAndCount(StatisticsVO record) {
		// TODO Auto-generated method stub
		return statisticsMapper.selectDateAndCount(record);
	}

	

}
