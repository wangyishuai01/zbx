package com.cn.zbx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.cn.zbx.pojo.Comment;
import com.cn.zbx.service.IStatisticsService;
import com.cn.zbx.vo.StatisticsVO;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
	
	@Autowired
	IStatisticsService statisticsService;
	
	@ResponseBody
	@RequestMapping(value="/statistics", method = { RequestMethod.GET, RequestMethod.POST })
	public String statistics(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String startDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String userName = request.getParameter("userName");
        
		//文章访问量
		StatisticsVO record = new StatisticsVO();
		try {
			record.setStartDate(sdf.parse(startDate));
			record.setEndDate(sdf.parse(endDate));
			List<StatisticsVO> sv = statisticsService.selectDateAndCount(record);
			resultMap.put("data", sv);
			resultMap.put("success", true);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return JSONObject.toJSONString(resultMap);
	}
	
	
	
	
	
}
