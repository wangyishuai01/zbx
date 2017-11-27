package com.cn.zbx.service.impl;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List selectDateAndCount(StatisticsVO record) {
		// TODO Auto-generated method stub
		String svList = statisticsMapper.selectDateAndCount(record);
		List list = new ArrayList();
		if("hour".equals(record.getDateType())){
			int [] countStr = new int[24];
			String [] dataStr = new String[24];
			String[] strs=svList.split("\\|");
			for(int i=0,len=strs.length;i<len;i++){
				String s = strs[i].toString();
				String[] str=s.split(" ");
				String[] ss = str[1].split(":");
				dataStr[i] = ss[0];
				countStr[i] = Integer.parseInt(ss[1]);
				}
			list.add(dataStr);
			list.add(countStr);
		}else {
			String[] strs=svList.split("\\|");
			int [] countStr = new int[strs.length];
			String [] dataStr = new String[strs.length];
			for(int i=0,len=strs.length;i<len;i++){
				String s = strs[i].toString();
				String[] str=s.split(":");
				dataStr[i] = str[0];
				countStr[i] = Integer.parseInt(str[1]);
				}
			list.add(dataStr);
			list.add(countStr);
		}
		
		return list;
	}

	

}
