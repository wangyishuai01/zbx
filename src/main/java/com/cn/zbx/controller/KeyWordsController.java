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
import com.cn.zbx.pojo.KeyWords;
import com.cn.zbx.service.IKeyWordsService;
import com.cn.zbx.vo.KeyWordsVO;

/**
 * 关键词Controller
 * @author zdl
 *
 */
@Controller
@RequestMapping(value="/keyWords")
public class KeyWordsController {
	
	@Autowired
	IKeyWordsService keyWordsService;
	
	/**
	 * 关键词主页面初始化方法
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/initKeyWordsMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String initKeyWordsMain(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		String keyWordsName = request.getParameter("keyWordsName");
		
		KeyWords keyWordsParam = new KeyWords();
		if(keyWordsName != null && !"".equals(keyWordsName)){
			keyWordsParam.setName(keyWordsName);
		}
		if(pageCount != null || !"".equals(pageCount)){
			keyWordsParam.setPageCount(Integer.valueOf(pageCount));
		}
		if(pageSize != null || !"".equals(pageSize)){
			keyWordsParam.setPageSize(Integer.valueOf(pageSize));
		}
		int number = keyWordsService.selectCountBySelectParam(keyWordsParam);
		if(number > 0){
			List<KeyWordsVO> keyWordsList = keyWordsService.selectBySelectParam(keyWordsParam);
			resultMap.put("data", keyWordsList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据条件查询关键词信息
	 * @param request
	 * @param response
	 * @param keyWords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectKeyWordsByParam", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectKeyWordsByParam(HttpServletRequest request, HttpServletResponse response, KeyWords keyWords){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		KeyWords keyWordsParam = new KeyWords();
		if(keyWords.getId() != null && !"".equals(keyWords.getId())){
			keyWordsParam.setId(keyWords.getId());
		}
		if(keyWords.getName() != null && !"".equals(keyWords.getName())){
			keyWordsParam.setName(keyWords.getName());
		}
		if(keyWords.getState() != null && !"".equals(keyWords.getState())){
			keyWordsParam.setState(keyWords.getState());
		}
		List<KeyWordsVO> keyWordsList = keyWordsService.selectBySelectParam(keyWordsParam);
		if(keyWordsList != null && keyWordsList.size() > 0){
			resultMap.put("data", keyWordsList);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据id删除关键词（若该关键词关联有文章或者视频不能被删除）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteById", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String keyWordsId = request.getParameter("keyWordsId");
		if(keyWordsId == null || "".equals(keyWordsId)){
			resultMap.put("errorMsg", "数据传输错误！");
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		KeyWords keyWordsParam = new KeyWords();
		keyWordsParam.setId(Integer.valueOf(keyWordsId));
		List<KeyWordsVO> keyWordsList = keyWordsService.selectBySelectParam(keyWordsParam);
		if(keyWordsList != null && keyWordsList.size() != 0){
			KeyWordsVO tKeyWordsVO = keyWordsList.get(0);
			if(tKeyWordsVO.getArticleCount() == 0 && tKeyWordsVO.getVideoCount() == 0){
				int result = keyWordsService.deleteByPrimaryKey(Integer.valueOf(keyWordsId));
				if(result <= 0){
					resultMap.put("errorMsg", "数据提交错误！");
					resultMap.put("success", false);
				} else {
					resultMap.put("success", true);
				}
			} else {
				resultMap.put("errorMsg", "该关键词关联有文章或者视频不能被删除！");
				resultMap.put("success", false);
			}
		} else {
			resultMap.put("errorMsg", "查询关键词失败！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 添加关键词信息（添加的关键词名字已有的不能被添加）
	 * @param request
	 * @param response
	 * @param keyWords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addKeyWords", method = { RequestMethod.GET, RequestMethod.POST })
	public String addKeyWords(HttpServletRequest request, HttpServletResponse response, KeyWords keyWords){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date currentDate = new Date();
		
		KeyWords keyWordsParam = new KeyWords();
		if(keyWords.getName() != null && !"".equals(keyWords.getName())){
			keyWordsParam.setName(keyWords.getName());
			int num = keyWordsService.selectCountBySelectParam(keyWordsParam);
			if(num > 0){
				resultMap.put("errorMsg", "已有该关键词，不能被添加！");
				resultMap.put("success", false);
			} else {
				if(keyWords.getExcerpt() != null && !"".equals(keyWords.getExcerpt())){
					keyWordsParam.setExcerpt(keyWords.getExcerpt());
				}
				if(keyWords.getState() != null && !"".equals(keyWords.getState())){
					keyWordsParam.setState(keyWords.getState());
				}
				keyWordsParam.setMakedate(currentDate);
				keyWordsParam.setModifydate(currentDate);
				
				int result = keyWordsService.insertSelective(keyWordsParam);
				if(result <= 0){
					resultMap.put("errorMsg", "数据提交错误！");
					resultMap.put("success", false);
				} else {
					resultMap.put("success", true);
				}
			}
		} else {
			resultMap.put("errorMsg", "数据传输错误！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 修改关键词信息（修改的关键词名字已有的不能被修改）
	 * @param request
	 * @param response
	 * @param keyWords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSelectById", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateSelectById(HttpServletRequest request, HttpServletResponse response, KeyWords keyWords){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		KeyWords keyWordsParam = new KeyWords();
		if(keyWords.getId() != null && !"".equals(keyWords.getId())){
			KeyWords tKeyWords = keyWordsService.selectByPrimaryKey(keyWords.getId());
			if(tKeyWords != null){
				boolean b = true;
				if(keyWords.getName() != null && !"".equals(keyWords.getName())){
					keyWordsParam.setName(keyWords.getName());
					List<KeyWordsVO> keyWordsList = keyWordsService.selectBySelectParam(keyWordsParam);
					for(KeyWordsVO kv : keyWordsList){
						if(kv.getId() != tKeyWords.getId() && kv.getName().equals(keyWords.getName())){
							b = false;
							break;
						}
					}
				}
				if(b){
					keyWordsParam.setId(keyWords.getId());
					if(keyWords.getExcerpt() != null && !"".equals(keyWords.getExcerpt())){
						keyWordsParam.setExcerpt(keyWords.getExcerpt());
					}
					if(keyWords.getState() != null && !"".equals(keyWords.getState())){
						keyWordsParam.setState(keyWords.getState());
					}
					keyWordsParam.setModifydate(new Date());
					int result = keyWordsService.updateByPrimaryKeySelective(keyWordsParam);
					if(result <= 0){
						resultMap.put("errorMsg", "数据提交失败！");
						resultMap.put("success", false);
					} else {
						resultMap.put("success", true);
					}
				} else {
					resultMap.put("errorMsg", "修改的关键词名字已存在！");
					resultMap.put("success", false);
				} 
			} else {
				resultMap.put("errorMsg", "分类查询失败！");
				resultMap.put("success", false);
			}
		}
		return JSONObject.toJSONString(resultMap);
	}

}
