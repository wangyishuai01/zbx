package com.cn.zbx.controller;

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
import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.service.ICommentService;
import com.cn.zbx.vo.ArticleVO;

@Controller
@RequestMapping(value="/Article")
public class ArticleMainController {
	
	@Autowired
	IArticleMainService articleMainService;
	
	@Autowired
	ICommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/ArticleList", method = { RequestMethod.GET, RequestMethod.POST })
	public String initClassifyMain(HttpServletRequest request, HttpServletResponse response, ArticleMain article){
		Map<String, Object> resultMap = new HashMap<String, Object>();

		ArticleMain articleParam = new ArticleMain();
		if(article.getId() != null && !"".equals(article.getId())){
			articleParam.setId(article.getId());
		}
		if(article.getTitle() != null && !"".equals(article.getTitle())){
			articleParam.setTitle(article.getTitle());
		}
		if(article.getClassid() != null && !"".equals(article.getClassid())){
			articleParam.setClassid(article.getClassid());
		}
		if(article.getIsfree() != null && !"".equals(article.getIsfree())){
			articleParam.setIsfree(article.getIsfree());
		}
		if(article.getNocomment() != null && !"".equals(article.getNocomment())){
			articleParam.setNocomment(article.getNocomment());
		}
		if(article.getState() != null && !"".equals(article.getState())){
			articleParam.setState(article.getState());
		}
		if(article.getPageCount() != null && !"".equals(article.getPageCount())){
			articleParam.setPageCount(article.getPageCount());
		}
		if(article.getPageSize() != null && !"".equals(article.getPageSize())){
			articleParam.setPageSize(article.getPageSize());
		}
		Integer number = articleMainService.selectCountBySelectParam(articleParam);
		if(number != null && number > 0){
			List<ArticleVO> resultList = articleMainService.selectBySelectParam(articleParam);
			for(ArticleVO article1:resultList){
				int count = commentService.selectByArticleId(article1.getId());
				article1.setCommentCount(count);
			}
			resultMap.put("data", resultList);
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
		String articleId = request.getParameter("articleId");
		if(articleId == null || "".equals(articleId)){
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		int result = articleMainService.deleteByPrimaryKey(Integer.valueOf(articleId));
		if(result <= 0){
			resultMap.put("success", false);
		} else {
			/*
			 * 是否需要删除评论！！！
			 */
			resultMap.put("success", true);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectById", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String articleId = request.getParameter("articleId");
		if(articleId == null || "".equals(articleId)){
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		ArticleMain articleMain = articleMainService.selectByPrimaryKey(Integer.valueOf(articleId));
		if(articleMain.getContent()!=null){
			String str = new String(articleMain.getContent());
			articleMain.setContentStr(str);
			System.out.println("************8"+str);
		}
		
			resultMap.put("data", articleMain);
			resultMap.put("success", true);
		
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/articleUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request, HttpServletResponse response,ArticleMain article){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int  articleMain = articleMainService.updateByPrimaryKeySelective(article);
			resultMap.put("data", articleMain);
			resultMap.put("success", true);
		
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectArticleMainByParam", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectArticleMainByParam(HttpServletRequest request, HttpServletResponse response, ArticleMain article){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ArticleMain articleParam = new ArticleMain();
		if(article.getId() != null && !"".equals(article.getId())){
			articleParam.setId(article.getId());
		}
		if(article.getTitle() != null && !"".equals(article.getTitle())){
			articleParam.setTitle(article.getTitle());
		}
		if(article.getClassid() != null && !"".equals(article.getClassid())){
			articleParam.setClassid(article.getClassid());
		}
		if(article.getIsfree() != null && !"".equals(article.getIsfree())){
			articleParam.setIsfree(article.getIsfree());
		}
		if(article.getNocomment() != null && !"".equals(article.getNocomment())){
			articleParam.setNocomment(article.getNocomment());
		}
		if(article.getState() != null && !"".equals(article.getState())){
			articleParam.setState(article.getState());
		}
		if(article.getPageCount() != null && !"".equals(article.getPageCount())){
			articleParam.setPageCount(article.getPageCount());
		}
		if(article.getPageSize() != null && !"".equals(article.getPageSize())){
			articleParam.setPageSize(article.getPageSize());
		}
		
		Integer number = articleMainService.selectCountBySelectParam(articleParam);
		if(number!=null && number > 0){
			List<ArticleMain> resultList = articleMainService.selectArticleNoContentBySelectParam(articleParam);
			resultMap.put("data", resultList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	

}
