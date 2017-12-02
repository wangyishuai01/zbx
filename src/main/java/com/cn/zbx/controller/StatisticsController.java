package com.cn.zbx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.zbx.dao.CustomerMapper;
import com.cn.zbx.pojo.Comment;
import com.cn.zbx.pojo.Customer;
import com.cn.zbx.service.IStatisticsService;
import com.cn.zbx.vo.CustomerVO;
import com.cn.zbx.vo.LogVO;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
	
	@Autowired
	IStatisticsService statisticsService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/statistics", method = { RequestMethod.GET, RequestMethod.POST })
	public String statistics(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String userName = request.getParameter("userName");
		String dateSpace = request.getParameter("dateSpace");
		String statisticsType = request.getParameter("statisticsType");
		List<Customer> cvlist = new ArrayList<Customer>();
		if(!"".equals(userName)){
			cvlist = customerMapper.selectByUserName(userName);
		}
		int userid=0;
		if(cvlist.size()>0){
			userid = cvlist.get(0).getId();
		}
		
		List list = new ArrayList();
		//文章访问量
		LogVO record = new LogVO();
		record.setUserId(userid);
		if(!"".equals(startDate)){
			record.setStartDate(startDate);
		}
		if(!"".equals(endDate)){
			record.setEndDate(endDate);
		}
		if("0".equals(dateSpace)){//时
			record.setDateType("hour");
			if("0".equals(statisticsType)){//文章与视频
				list = statisticsService.selectDateAndCount(record);
			}else if("1".equals(statisticsType)){//点击视频与购买视频
				list = statisticsService.selectDateAndCountByBuyVedio(record);
			}else if("2".equals(statisticsType)){//效益
				list = statisticsService.selectDateAndCountByBuyMoney(record);
			}
		}
		if("1".equals(dateSpace)){//天
			record.setDateType("day");
			if("0".equals(statisticsType)){//文章与视频
				list = statisticsService.selectDateAndCount(record);
			}else if("1".equals(statisticsType)){//点击视频与购买视频
				list = statisticsService.selectDateAndCountByBuyVedio(record);
			}else if("2".equals(statisticsType)){//效益
				list = statisticsService.selectDateAndCountByBuyMoney(record);
			}
		}
		if("2".equals(dateSpace)){//月
			record.setDateType("month");
			if("0".equals(statisticsType)){//文章与视频
				list = statisticsService.selectDateAndCount(record);
			}else if("1".equals(statisticsType)){//点击视频与购买视频
				list = statisticsService.selectDateAndCountByBuyVedio(record);
			}else if("2".equals(statisticsType)){//效益
				list = statisticsService.selectDateAndCountByBuyMoney(record);
			}
		}
		if("3".equals(dateSpace)){//年
			record.setDateType("year");
			if("0".equals(statisticsType)){//文章与视频
				list = statisticsService.selectDateAndCount(record);
			}else if("1".equals(statisticsType)){//点击视频与购买视频
				list = statisticsService.selectDateAndCountByBuyVedio(record);
			}else if("2".equals(statisticsType)){//效益
				list = statisticsService.selectDateAndCountByBuyMoney(record);
			}
		}
		resultMap.put("data", list);
		resultMap.put("success", true);
		
		
		
		return JSONObject.toJSONString(resultMap);
	}
	
	
	
	
	
}
