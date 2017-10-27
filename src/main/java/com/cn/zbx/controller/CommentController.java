package com.cn.zbx.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.zbx.pojo.Comment;
import com.cn.zbx.service.ICommentService;
import com.cn.zbx.vo.CommentVO;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/initCommentMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String iniCommentMain(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		if(pageCount == null || "".equals(pageCount)){
			pageCount = "1";
		}
		if(pageSize == null || "".equals(pageSize)){
			pageSize = "10";
		}
		Comment CommentParam = new Comment();
		CommentParam.setPageCount(Integer.valueOf(pageCount));
		CommentParam.setPageSize(Integer.valueOf(pageSize));
		int number = commentService.selectCountBySelectParam(CommentParam);
		if(number > 0){
			List<CommentVO> commentList = commentService.selectBySelectParam(CommentParam);
			resultMap.put("data", commentList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteById", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String CommentId = request.getParameter("commentId");
		if(CommentId == null || "".equals(CommentId)){
			resultMap.put("success", false);
		}
		int result = commentService.deleteByPrimaryKey(Integer.valueOf(CommentId));
		if(result <= 0){
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
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
