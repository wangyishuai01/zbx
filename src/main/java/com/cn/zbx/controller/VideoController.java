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
import com.cn.zbx.util.MapUtil;
import com.cn.zbx.vo.VideoVO;

@Controller
@RequestMapping(value="/video")
public class VideoController {
	
	@Autowired
	IVideoMainService videoMainService;
	
	/**
	 * 视频主页面初始化方法
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * 根据视频id更新视频信息
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 */
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
	
	/**
	 * 编辑视频信息功能 包括价格
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editVideoInfoById", method = { RequestMethod.GET, RequestMethod.POST })
	public String editVideoInfoById(HttpServletRequest request, HttpServletResponse response, 
			VideoMain video, String videoPrice, String videoPriceOld, String videoPriceId){
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
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap = MapUtil.objectToMap(videoParam);
			paramMap.put("videoPrice", videoPrice);
			paramMap.put("videoPriceOld", videoPriceOld);
			paramMap.put("videoPriceId", videoPriceId);
			
			boolean result = videoMainService.editVideoInfoByVideoId(paramMap);
			if(result){
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			resultMap.put("msg", "数据转化错误！");
			resultMap.put("success", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("msg", "数据提交错误！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据条件查询视频
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectVideoByParam", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectVideoByParam(HttpServletRequest request, HttpServletResponse response, VideoMain video){
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
		if(video.getPageSize() != null && !"".equals(video.getPageSize())){
			videoParam.setPageSize(video.getPageSize());
		}
		if(video.getPageCount() != null && !"".equals(video.getPageCount())){
			videoParam.setPageCount(video.getPageCount());
		}
		
		int number = videoMainService.selectCountBySelectParam(videoParam);
		if(number > 0){
			List<VideoMain> videoMainList = videoMainService.selectBySelectParam(videoParam);
			resultMap.put("data", videoMainList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 删除视频信息
	 * @param request
	 * @param response
	 * @param videoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteVideoById", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteVideoById(HttpServletRequest request, HttpServletResponse response, String videoId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(videoId != null && !"".equals(videoId)){
			int result = videoMainService.deleteByPrimaryKey(Integer.valueOf(videoId));
			if(result <= 0){
				resultMap.put("success", false);
			} else {
				resultMap.put("success", true);
			}
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 新增视频功能
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addVideoInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String addVideoInfo(HttpServletRequest request, HttpServletResponse response, VideoMain video, String videoPrice){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date currentDate = new Date();
		
		VideoMain videoParam = new VideoMain();
		if(video.getId() != null && !"".equals(video.getId())){
			videoParam.setId(video.getId());
		}
		if(video.getTitle() != null && !"".equals(video.getTitle())){
			videoParam.setTitle(video.getTitle());
		}
		if(video.getImgurl() != null && !"".equals(video.getImgurl())){
			videoParam.setImgurl(video.getImgurl());
		}
		if(video.getArticleid() != null && !"".equals(video.getArticleid())){
			videoParam.setArticleid(video.getArticleid());
		}
		if(video.getSize() != null && !"".equals(video.getSize())){
			videoParam.setSize(video.getSize());
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
		videoParam.setVideopath(video.getVideopath());
		videoParam.setMakedate(currentDate);
		videoParam.setModifydate(currentDate);
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap = MapUtil.objectToMap(videoParam);
			paramMap.put("videoPrice", videoPrice);
			
			boolean result = videoMainService.addVideoInfo(paramMap);
			if(result){
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			resultMap.put("msg", "数据转化错误！");
			resultMap.put("success", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("msg", "数据提交错误！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据视频id查询视频
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectVideoById", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectVideoById(HttpServletRequest request, HttpServletResponse response, String videoId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(videoId == null || "".equals(videoId)){
			resultMap.put("success", false);
			resultMap.put("errorMsg", "查询Id为空！");
			return JSONObject.toJSONString(resultMap);
		}
		VideoMain video = videoMainService.selectByPrimaryKey(Integer.valueOf(videoId));
		if(video != null){
			resultMap.put("data", video);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("errorMsg", "查询数据为空！");
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据分类id查询相关视频
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectRelationVideoByClassifyId", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectRelationVideoByClassifyId(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		String classifyId = request.getParameter("classifyId");
		
		VideoVO videoParam = new VideoVO();
		if(classifyId != null && !"".equals(classifyId)){
			videoParam.setClassid(Integer.valueOf(classifyId));
		}
		if(pageCount != null || !"".equals(pageCount)){
			videoParam.setPageCount(Integer.valueOf(pageCount));
		}
		if(pageSize != null || !"".equals(pageSize)){
			videoParam.setPageSize(Integer.valueOf(pageSize));
		}
		List<VideoVO> videoVOList = videoMainService.selectBySelectParam(videoParam);
		if(videoVOList != null && videoVOList.size() != 0){
			resultMap.put("data", videoVOList);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("errorMsg", "查询数据为空！");
		}
		return JSONObject.toJSONString(resultMap);
	}
}
