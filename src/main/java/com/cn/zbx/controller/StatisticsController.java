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
import com.cn.zbx.pojo.Comment;
import com.cn.zbx.service.IStatisticsService;
import com.cn.zbx.vo.StatisticsVO;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
	
	@Autowired
	IStatisticsService statisticsService;
	
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
		
        
		//文章访问量
		StatisticsVO record = new StatisticsVO();
		if(!"".equals(startDate)){
			record.setStartDate(startDate);
		}
		if(!"".equals(endDate)){
			record.setEndDate(endDate);
		}
		if("0".equals(dateSpace)){//时
			//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			/*String str=sdf2.format(record.getStartDate());
			record.setEndDate(sdf2.parse(str));
			Date startD = record.getEndDate();
			startD.setHours(0);
			String str2=sdf2.format(record.getEndDate());
			record.setEndDate(sdf2.parse(str2));
			Date endD = record.getEndDate();
			endD.setHours(23);
			record.setStartDate(startD);
			record.setEndDate(endD);*/
			List sv = statisticsService.selectHourAndCount(record);
		}
		if("1".equals(dateSpace)){//天
			
		}
		if("2".equals(dateSpace)){//月
			
		}
		if("3".equals(dateSpace)){//年
			
		}
		List<StatisticsVO> sv = statisticsService.selectDateAndCount(record);
		int [] countStr = new int[sv.size()];
		String [] dataStr = new String[sv.size()];
		for(int i=0;i<sv.size();i++){
			String str = sdf.format(sv.get(i).getCreateDate());
			dataStr[i]=str;
			countStr[i]=sv.get(i).getCount();
		}
		List list = new ArrayList();
		list.add(dataStr);
		list.add(countStr);
		resultMap.put("data", list);
		resultMap.put("success", true);
		
		
		
		return JSONObject.toJSONString(resultMap);
	}
	
	
	
	
	
}
