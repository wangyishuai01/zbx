package com.cn.zbx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.StatisticsMapper;
import com.cn.zbx.pojo.BLog;
import com.cn.zbx.service.IStatisticsService;
import com.cn.zbx.vo.LogVO;

@Service(value="StatisticsService")
public class StatisticsServiceImpl implements IStatisticsService {
	
	@Autowired
	StatisticsMapper statisticsMapper;

	@Override
	public int selectCountBySelectParam(BLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List selectDateAndCount(LogVO record) {
		// TODO Auto-generated method stub
		//统计文章
		record.setProductType(1);
		String articleList = statisticsMapper.selectDateAndCount(record);
		//统计视频
		record.setProductType(2);
		String videoList = statisticsMapper.selectDateAndCount(record);
		//购买视频
		String cusBuyHistoryList = statisticsMapper.selectCusBuyHistoryByDateAndCount(record);
		List list = new ArrayList();
		if("hour".equals(record.getDateType())){
			//文章
			int [] articleCountStr = new int[24];
			String [] articleDataStr = new String[24];
			String[] strs=articleList.split("\\|");
			for(int i=0,len=strs.length;i<len;i++){
				String s = strs[i].toString();
				String[] str=s.split(" ");
				String[] ss = str[1].split(":");
				articleDataStr[i] = ss[0];
				articleCountStr[i] = Integer.parseInt(ss[1]);
				}
			list.add(articleDataStr);
			list.add(articleCountStr);
			//视频
			int [] videoCountStr = new int[24];
			String[] videostrs=videoList.split("\\|");
			for(int i=0,len=videostrs.length;i<len;i++){
				String s = videostrs[i].toString();
				String[] str=s.split(" ");
				String[] ss = str[1].split(":");
				videoCountStr[i] = Integer.parseInt(ss[1]);
				}
			list.add(videoCountStr);
			//购买视频
			int [] cusBuyHistoryCountStr = new int[24];
			String[] cusBuyHistorystrs=cusBuyHistoryList.split("\\|");
			for(int i=0,len=cusBuyHistorystrs.length;i<len;i++){
				String s = cusBuyHistorystrs[i].toString();
				String[] str=s.split(" ");
				String[] ss = str[1].split(":");
				cusBuyHistoryCountStr[i] = Integer.parseInt(ss[1]);
				}
			list.add(cusBuyHistoryCountStr);
		}else {
			//文章
			String[] strs=articleList.split("\\|");
			int [] articleCountStr = new int[strs.length];
			String [] articleDataStr = new String[strs.length];
			for(int i=0,len=strs.length;i<len;i++){
				String s = strs[i].toString();
				String[] str=s.split(":");
				articleDataStr[i] = str[0];
				articleCountStr[i] = Integer.parseInt(str[1]);
				}
			list.add(articleDataStr);
			list.add(articleCountStr);
			//视频
			String[] videostrs=videoList.split("\\|");
			int [] videoCountStr = new int[strs.length];
			for(int i=0,len=videostrs.length;i<len;i++){
				String s = videostrs[i].toString();
				String[] str=s.split(":");
				videoCountStr[i] = Integer.parseInt(str[1]);
				}
			list.add(videoCountStr);
			//购买视频
			String[] cusBuyHistorystrs=cusBuyHistoryList.split("\\|");
			int [] cusBuyHistoryCountStr = new int[strs.length];
			for(int i=0,len=cusBuyHistorystrs.length;i<len;i++){
				String s = cusBuyHistorystrs[i].toString();
				String[] str=s.split(":");
				cusBuyHistoryCountStr[i] = Integer.parseInt(str[1]);
				}
			list.add(cusBuyHistoryCountStr);
		}
		
		return list;
	}

	

}
