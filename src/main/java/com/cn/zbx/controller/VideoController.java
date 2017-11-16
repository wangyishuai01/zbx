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
import com.cn.zbx.pojo.VideoMain;
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
	
	@ResponseBody
	@RequestMapping(value="/updateSelectById", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateSelectById(HttpServletRequest request, HttpServletResponse response, VideoMain video){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		VideoMain videoParam = new VideoMain();
		if(video.getId() != null && !"".equals(video.getId())){
			videoParam.setId(video.getId());
		}
		if(video.getTitle() != null && !"".equals(video.getTitle())){
			videoParam.setTitle(video.getTitle());
		}
		if(video.getVideopath() != null && !"".equals(video.getVideopath())){
			videoParam.setVideopath(video.getVideopath());
		}
		if(video.getImgurl() != null && !"".equals(video.getImgurl())){
			videoParam.setImgurl(video.getImgurl());
		}
		if(video.getArticleid() != null && !"".equals(video.getArticleid())){
			videoParam.setArticleid(video.getArticleid());
		}
		if(video.getSuffix() != null && !"".equals(video.getSuffix())){
			videoParam.setSuffix(video.getSuffix());
		}
		if(video.getIsfree() != null && !"".equals(video.getIsfree())){
			videoParam.setIsfree(video.getIsfree());
		}
		if(video.getNocomment() != null && !"".equals(video.getNocomment())){
			videoParam.setNocomment(video.getNocomment());
		}
		videoParam.setModifydate(new Date());
		int result = videoMainService.updateByPrimaryKeySelective(videoParam);
		if(result <= 0){
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
}
