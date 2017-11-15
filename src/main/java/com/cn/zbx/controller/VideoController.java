package com.cn.zbx.controller;

import java.util.ArrayList;
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
import com.cn.zbx.service.IVideoMainService;
import com.cn.zbx.vo.VideoVO;

@Controller
@RequestMapping(value="/video")
public class VideoController {
	
	@Autowired
	IVideoMainService videoMainService;
	
	
	@ResponseBody
	@RequestMapping(value="/initVideoMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String initClassifyMain(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		String videoTitle = request.getParameter("videoTitle");
		String classifyId = request.getParameter("classifyId");
		
		VideoVO videoParam = new VideoVO();
		if(videoTitle != null && !"".equals(videoTitle)){
			videoParam.setTitle(videoTitle);
		}
		if(classifyId != null && !"".equals(classifyId)){
			videoParam.setClassid(Integer.valueOf(classifyId));
		}
		if(pageCount != null || !"".equals(pageCount)){
			videoParam.setPageCount(Integer.valueOf(pageCount));
		}
		if(pageSize != null || !"".equals(pageSize)){
			videoParam.setPageSize(Integer.valueOf(pageSize));
		}
		int number = videoMainService.selectCountBySelectParam(videoParam);
		if(number > 0){
			List<VideoVO> videoVOList = videoMainService.selectBySelectParam(videoParam);
			resultMap.put("data", videoVOList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
}
