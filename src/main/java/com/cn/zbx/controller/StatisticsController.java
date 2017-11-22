package com.cn.zbx.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.cn.zbx.service.ICommentService;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
	
	@Autowired
	ICommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/statistics", method = { RequestMethod.GET, RequestMethod.POST })
	public String statistics(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String userName = request.getParameter("userName");
        
		//文章访问量
			
			resultMap.put("data", "");
			resultMap.put("success", true);
		
		return JSONObject.toJSONString(resultMap);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/addtComment", method = { RequestMethod.GET, RequestMethod.POST })
	public String addtComment(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date currentDate = new Date();
		String commentTitle = request.getParameter("commentTitle");
		String comment = request.getParameter("comment");
		String commentType = request.getParameter("commentType");
		String customerId = request.getParameter("customerId");
		if(commentTitle == null || "".equals(commentTitle) 
				|| commentType == null || "".equals(commentType)
				|| customerId == null || "".equals(customerId)){
			resultMap.put("success", false);
		} else {
			Comment commentParam = new Comment();
			commentParam.setTitle(commentTitle);
			commentParam.setComment(comment);
			commentParam.setCommenttype(commentType);
			commentParam.setCustomerId(Integer.valueOf(customerId));
			commentParam.setMakedate(currentDate);
			commentParam.setModifydate(currentDate);
			int result = commentService.insertSelective(commentParam);
			if(result <= 0){
				resultMap.put("success", false);
			} else {
				resultMap.put("success", true);
			}
		}
		return JSONObject.toJSONString(resultMap);
	}
	
}
