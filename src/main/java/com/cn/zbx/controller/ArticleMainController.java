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
import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.VideoMain;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.service.ICommentService;
import com.cn.zbx.service.IVideoMainService;
import com.cn.zbx.util.MapUtil;
import com.cn.zbx.vo.ArticleVO;

@Controller
@RequestMapping(value="/Article")
public class ArticleMainController {
	
	@Autowired
	IArticleMainService articleMainService;
	
	@Autowired
	ICommentService commentService;
	
	@Autowired
	IVideoMainService videoMainService;
	
	/**
	 * 根据条件查询文章信息（文章主页面初始化方法）
	 * @param request
	 * @param response
	 * @param ArticleMain article
	 * @return List<ArticleVO>
	 */
	@ResponseBody
	@RequestMapping(value="/ArticleList", method = { RequestMethod.GET, RequestMethod.POST })
	public String initArticleMain(HttpServletRequest request, HttpServletResponse response, ArticleMain article){
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
	
	/**
	 * 根据条件查询文章信息（文章主页面初始化方法）添加关键词查询
	 * @param request
	 * @param response
	 * @param ArticleMain article
	 * @return List<ArticleVO>
	 */
	@ResponseBody
	@RequestMapping(value="/initArticleMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String initArticleMainV2(HttpServletRequest request, HttpServletResponse response, ArticleMain article, String keyWords){
		Map<String, Object> resultMap = new HashMap<String, Object>();

		ArticleVO articleParam = new ArticleVO();
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
		if(keyWords != null && !"".equals(keyWords)){
			articleParam.setKeyWords(keyWords.split(","));
		}
		Integer number = articleMainService.selectCountBySelectParamV2(articleParam);
		if(number != null && number > 0){
			List<ArticleVO> resultList = articleMainService.selectBySelectParamV2(articleParam);
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
	
	/**
	 * 根据id删除文章信息（该文章下有关联的视频不能被删除）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteById", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String articleId = request.getParameter("articleId");
		if(articleId == null || "".equals(articleId)){
			resultMap.put("errorMsg", "数据传输错误！");
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		VideoMain videoParam = new VideoMain();
		videoParam.setArticleid(Integer.valueOf(articleId));
		int number = videoMainService.selectCountBySelectParam(videoParam);
		if(number > 0){
			resultMap.put("errorMsg", "该文章下有关联的视频，不能被删除！");
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		int result = articleMainService.deleteByPrimaryKey(Integer.valueOf(articleId));
		if(result <= 0){
			resultMap.put("errorMsg", "数据提交错误！");
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据文章id查询文章信息
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * 更新文章信息
	 * @param request
	 * @param response
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/articleUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request, HttpServletResponse response,ArticleMain article){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int  articleMain = articleMainService.updateByPrimaryKeySelective(article);
			resultMap.put("data", articleMain);
			resultMap.put("success", true);
		
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据条件查询文章信息（分页）
	 * @param request
	 * @param response
	 * @param ArticleMain article 
	 * @return List<ArticleMain>
	 */
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
	
	/**
	 * 根据文章id查询所属二级分类和一级分类
	 * @param request
	 * @param response
	 * @return {id:1,title:"文章标题",cid:1,cname:"二级分类名",pcid:1,pcname:"一级分类名"}
	 */
	@ResponseBody
	@RequestMapping(value="/selectClassifyByArticleId", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectClassifyByArticleId(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String articleId = request.getParameter("articleId");
		if(articleId != null && !"".equals(articleId)){
			Map<String, Object> map = articleMainService.selectClassifyByArticleId(Integer.valueOf(articleId));
			if(map != null && map.size() > 0){
				resultMap.put("data", map);
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}

	/**
	 * 编辑文章信息功能 包括价格
	 * @param request
	 * @param response
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editArticleInfoById", method = { RequestMethod.GET, RequestMethod.POST })
	public String editArticleInfoById(HttpServletRequest request, HttpServletResponse response, 
			ArticleMain article, String articlePrice, String articlePriceOld, String articlePriceId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ArticleMain articleParam = new ArticleMain();
		if(article.getId() != null && !"".equals(article.getId())){
			articleParam.setId(article.getId());
		}
		if(article.getTitle() != null && !"".equals(article.getTitle())){
			articleParam.setTitle(article.getTitle());
		}
		if(article.getImgurl() != null && !"".equals(article.getImgurl())){
			articleParam.setImgurl(article.getImgurl());
		}
		if(article.getClassid() != null && !"".equals(article.getClassid())){
			articleParam.setClassid(article.getClassid());
		}
		if(article.getExcerpt() != null && !"".equals(article.getExcerpt())){
			articleParam.setExcerpt(article.getExcerpt());
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
		if(article.getContent() != null && !"".equals(article.getContent())){
			articleParam.setContent(article.getContent());
		}
		articleParam.setModifydate(new Date());
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap = MapUtil.objectToMap(articleParam);
			paramMap.put("articlePrice", articlePrice);
			paramMap.put("articlePriceOld", articlePriceOld);
			paramMap.put("articlePriceId", articlePriceId);
			
			boolean result = articleMainService.editArticleInfoByVideoId(paramMap);
			if(result){
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			resultMap.put("msg", "数据转化错误！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 新增文章信息功能 包括价格
	 * @param request
	 * @param response
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addArticleInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String addArticleInfo(HttpServletRequest request, HttpServletResponse response, 
			ArticleMain article, String articlePrice){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date currentDate = new Date();
		
		ArticleMain articleParam = new ArticleMain();
		if(article.getId() != null && !"".equals(article.getId())){
			articleParam.setId(article.getId());
		}
		if(article.getTitle() != null && !"".equals(article.getTitle())){
			articleParam.setTitle(article.getTitle());
		}
		if(article.getImgurl() != null && !"".equals(article.getImgurl())){
			articleParam.setImgurl(article.getImgurl());
		}
		if(article.getClassid() != null && !"".equals(article.getClassid())){
			articleParam.setClassid(article.getClassid());
		}
		if(article.getExcerpt() != null && !"".equals(article.getExcerpt())){
			articleParam.setExcerpt(article.getExcerpt());
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
		if(article.getContent() != null && !"".equals(article.getContent())){
			articleParam.setContent(article.getContent());
		}
		articleParam.setMakedate(currentDate);
		articleParam.setModifydate(currentDate);
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap = MapUtil.objectToMap(articleParam);
			paramMap.put("articlePrice", articlePrice);
			
			boolean result;
			try {
				result = articleMainService.addArticleInfo(paramMap);
				if(result){
					resultMap.put("success", true);
				} else {
					resultMap.put("success", false);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultMap.put("success", false);
			}
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			resultMap.put("msg", "数据转化错误！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 根据条件查询文章信息（分页）（没有关联视频的文章）
	 * @param request
	 * @param response
	 * @param ArticleMain article 
	 * @return List<ArticleMain>
	 */
	@ResponseBody
	@RequestMapping(value="/selectArticleMainByParamNoRelationVideo", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectArticleMainByParamNoRelationVideo(HttpServletRequest request, HttpServletResponse response, ArticleMain article){
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
		
		Integer number = articleMainService.selectCountBySelectParamNoRelationVideo(articleParam);
		if(number!=null && number > 0){
			List<ArticleMain> resultList = articleMainService.selectArticleNoContentBySelectParamNoRelationVideo(articleParam);
			resultMap.put("data", resultList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
}
