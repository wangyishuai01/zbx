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
import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.service.ITClassifyService;
import com.cn.zbx.vo.TClassifyVO;

@Controller
@RequestMapping(value="/Article")
public class ArticleMainController {
	
	@Autowired
	IArticleMainService articleMainService;
	
	@ResponseBody
	@RequestMapping(value="/ArticleList", method = { RequestMethod.GET, RequestMethod.POST })
	public String initClassifyMain(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		if(pageCount == null || "".equals(pageCount)){
			pageCount = "1";
		}
		if(pageSize == null || "".equals(pageSize)){
			pageSize = "10";
		}
		ArticleMain artickeMain = new ArticleMain();
		artickeMain.setPageCount(Integer.valueOf(pageCount));
		artickeMain.setPageSize(Integer.valueOf(pageSize));
		
		List<ArticleMain> resultList = articleMainService.AllList(artickeMain);
		
			resultMap.put("data", resultList);
			resultMap.put("number", 1);
			resultMap.put("success", true);
		
		return JSONObject.toJSONString(resultMap);
	}
	
	
	
	
	

}
