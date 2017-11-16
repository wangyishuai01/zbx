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
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.service.ITClassifyService;
import com.cn.zbx.vo.TClassifyVO;

@Controller
@RequestMapping(value="/tclassify")
public class TClassifyController {
	
	@Autowired
	ITClassifyService tClassifyService;
	
	@Autowired
	IArticleMainService articleMainService;
	
	@ResponseBody
	@RequestMapping(value="/initClassifyMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String initClassifyMain(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		String className = request.getParameter("className");
		
		Tclassify tclassifyParam = new Tclassify();
		if(className != null && !"".equals(className)){
			tclassifyParam.setName(className);
		}
		if(pageCount != null || !"".equals(pageCount)){
			tclassifyParam.setPageCount(Integer.valueOf(pageCount));
		}
		if(pageSize != null || !"".equals(pageSize)){
			tclassifyParam.setPageSize(Integer.valueOf(pageSize));
		}
		int number = tClassifyService.selectCountBySelectParam(tclassifyParam);
		if(number > 0){
			List<Tclassify> tclassifyList = tClassifyService.selectBySelectParam(tclassifyParam);
			List<TClassifyVO> tclassifyVOList = articleMainService.selectCountByClassIds(tclassifyList);
			List<TClassifyVO> resultTclassifyVOList = new ArrayList<TClassifyVO>();
			for(Tclassify oo: tclassifyList){
				TClassifyVO tClassifyVO = new TClassifyVO();
				tClassifyVO.setName(oo.getName());
				tClassifyVO.setClassid(oo.getId());
				tClassifyVO.setSpareField1(String.valueOf(oo.getIsdisplay()));
				boolean isHave = false;
				for(TClassifyVO o : tclassifyVOList){
					if(o.getClassid() == oo.getId()){
						tClassifyVO.setCount(o.getCount());
						isHave = true;
						break;
					}
				}
				if(!isHave){
					tClassifyVO.setCount(0);
				}
				resultTclassifyVOList.add(tClassifyVO);
			}
			resultMap.put("data", resultTclassifyVOList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectClassifyByParam", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectClassifyByParam(HttpServletRequest request, HttpServletResponse response, Tclassify tclassify){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Tclassify tclassifyParam = new Tclassify();
		if(tclassify.getId() != null && !"".equals(tclassify.getId())){
			tclassifyParam.setId(tclassify.getId());
		}
		if(tclassify.getName() != null && !"".equals(tclassify.getName())){
			tclassifyParam.setName(tclassify.getName());
		}
		if(tclassify.getPid() != null && !"".equals(tclassify.getPid())){
			tclassifyParam.setPid(tclassify.getPid());
		}
		if(tclassify.getIsdisplay() != null && !"".equals(tclassify.getIsdisplay())){
			tclassifyParam.setIsdisplay(tclassify.getIsdisplay());
		}
		List<Tclassify> tclassifyList = tClassifyService.selectBySelectParam(tclassifyParam);
		if(tclassifyList != null && tclassifyList.size() > 0){
			resultMap.put("data", tclassifyList);
		}
		tclassifyParam = new Tclassify();
		tclassifyParam.setPid(0);
		List<Tclassify> pclassifyList = tClassifyService.selectBySelectParam(tclassifyParam);
		if(pclassifyList != null && pclassifyList.size() > 0){
			resultMap.put("pdata", pclassifyList);
		}
		if((tclassifyList == null || tclassifyList.size() <= 0) && (pclassifyList == null || pclassifyList.size() <= 0)){
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteById", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String tclassifyId = request.getParameter("tclassifyId");
		if(tclassifyId == null || "".equals(tclassifyId)){
			resultMap.put("success", false);
		}
		int result = tClassifyService.deleteByPrimaryKey(Integer.valueOf(tclassifyId));
		if(result <= 0){
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateIsDisplayById", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateIsDisplayById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String tclassifyId = request.getParameter("tclassifyId");
		String isDisplay = request.getParameter("isDisplay");
		if(tclassifyId == null || "".equals(tclassifyId) || isDisplay == null || "".equals(isDisplay)){
			resultMap.put("success", false);
		} else {
			Tclassify tclassifyParam = new Tclassify();
			tclassifyParam.setId(Integer.valueOf(tclassifyId));
			tclassifyParam.setIsdisplay(Integer.valueOf(isDisplay));
			int result = tClassifyService.updateByPrimaryKeySelective(tclassifyParam);
			if(result <= 0){
				resultMap.put("success", false);
			} else {
				resultMap.put("success", true);
			}
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addtClassify", method = { RequestMethod.GET, RequestMethod.POST })
	public String addtClassify(HttpServletRequest request, HttpServletResponse response, Tclassify tclassify){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date currentDate = new Date();
		String tclassifyName = tclassify.getName();
		Integer tclassifyLevel = tclassify.getLevel();
		Integer tclassifyPid = tclassify.getPid();
		Integer tclassifyisdisplay = tclassify.getIsdisplay();
		if(tclassifyName == null || "".equals(tclassifyName) 
				|| tclassifyLevel == null || "".equals(tclassifyLevel)
				|| tclassifyPid == null || "".equals(tclassifyPid)
				|| tclassifyisdisplay == null || "".equals(tclassifyisdisplay)){
			resultMap.put("success", false);
		} else {
			Tclassify tclassifyParam = new Tclassify();
			tclassifyParam.setName(tclassifyName);
			tclassifyParam.setLevel(tclassifyLevel);
			tclassifyParam.setCode("001");
			tclassifyParam.setPid(tclassifyPid);
			tclassifyParam.setIsdisplay(tclassifyisdisplay);
			tclassifyParam.setTorder(1);
			tclassifyParam.setMakedate(currentDate);
			tclassifyParam.setModifydate(currentDate);
			int result = tClassifyService.insertSelective(tclassifyParam);
			if(result <= 0){
				resultMap.put("success", false);
			} else {
				resultMap.put("success", true);
			}
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSelectById", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateSelectById(HttpServletRequest request, HttpServletResponse response, Tclassify tclassify){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Tclassify tclassifyParam = new Tclassify();
		if(tclassify.getId() != null && !"".equals(tclassify.getId())){
			tclassifyParam.setId(tclassify.getId());
		}
		if(tclassify.getName() != null && !"".equals(tclassify.getName())){
			tclassifyParam.setName(tclassify.getName());
		}
		if(tclassify.getPid() != null && !"".equals(tclassify.getPid())){
			tclassifyParam.setPid(tclassify.getPid());
		}
		if(tclassify.getIsdisplay() != null && !"".equals(tclassify.getIsdisplay())){
			tclassifyParam.setIsdisplay(tclassify.getIsdisplay());
		}
		tclassifyParam.setModifydate(new Date());
		int result = tClassifyService.updateByPrimaryKeySelective(tclassifyParam);
		if(result <= 0){
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
		return JSONObject.toJSONString(resultMap);
	}

	@ResponseBody
	@RequestMapping(value="/selectClassifyByParamV2", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectClassifyByParamV2(HttpServletRequest request, HttpServletResponse response, Tclassify tclassify){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Tclassify tclassifyParam = new Tclassify();
		if(tclassify.getId() != null && !"".equals(tclassify.getId())){
			tclassifyParam.setId(tclassify.getId());
		}
		if(tclassify.getName() != null && !"".equals(tclassify.getName())){
			tclassifyParam.setName(tclassify.getName());
		}
		if(tclassify.getPid() != null && !"".equals(tclassify.getPid())){
			tclassifyParam.setPid(tclassify.getPid());
		}
		if(tclassify.getIsdisplay() != null && !"".equals(tclassify.getIsdisplay())){
			tclassifyParam.setIsdisplay(tclassify.getIsdisplay());
		}
		List<Tclassify> tclassifyList = tClassifyService.selectBySelectParam(tclassifyParam);
		if(tclassifyList != null && tclassifyList.size() > 0){
			resultMap.put("data", tclassifyList);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
}
