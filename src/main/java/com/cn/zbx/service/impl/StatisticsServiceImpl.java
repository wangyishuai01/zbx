package com.cn.zbx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List selectHourAndCount(StatisticsVO record) {
		// TODO Auto-generated method stub
		List<StatisticsVO> svList = statisticsMapper.selectHourAndCount(record);
		List list = new ArrayList();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh");
		if(svList!=null){
			int [] countStr = new int[svList.size()];
			String [] dataStr = new String[svList.size()];
			for(int i=0;i<svList.size();i++){
				String str = sdf.format(svList.get(i).getCreateDate());
				dataStr[i]=str;
				countStr[i]=svList.get(i).getCount();
			}
			list.add(dataStr);
			list.add(countStr);
		}
		
		return list;
	}

	

}
