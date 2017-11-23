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

/**
 * 分类 Controller
 * @author zdl
 *
 */
@Controller
@RequestMapping(value="/tclassify")
public class TClassifyController {
	
	@Autowired
	ITClassifyService tClassifyService;
	
	@Autowired
	IArticleMainService articleMainService;
	
	/**
	 * 分类主页初始化方法
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * 根据条件查询分类和对应的一级分类
	 * @param request
	 * @param response
	 * @param tclassify
	 * @return
	 */
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
	
	/**
	 * 删除分类（该分类下有关联的文章不能被删除；如果是一级分类，分类下有二级分类不能被删除）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteById", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteById(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String tclassifyId = request.getParameter("tclassifyId");
		if(tclassifyId != null && !"".equals(tclassifyId)){
			boolean b = true;
			Tclassify classify = tClassifyService.selectByPrimaryKey(Integer.valueOf(tclassifyId));
			if(classify.getLevel().intValue() == 1){
				Tclassify tclassifyParam = new Tclassify();
				tclassifyParam.setPid(classify.getId());
				int number = tClassifyService.selectCountBySelectParam(tclassifyParam);
				if(number > 0){
					b = false;
					resultMap.put("errorMsg", "该一级分类下有二级分类，不能删除！");
					resultMap.put("success", false);
				}
			} else {
				ArticleMain articleParam = new ArticleMain();
				articleParam.setClassid(Integer.valueOf(tclassifyId));
				Integer number = articleMainService.selectCountBySelectParam(articleParam);
				if(number > 0){
					b = false;
					resultMap.put("errorMsg", "该分类下有关联的文章，不能删除！");
					resultMap.put("success", false);
				}
			}
			if(b) {
				int result = tClassifyService.deleteByPrimaryKey(Integer.valueOf(tclassifyId));
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
	 * 更新分类的显示状态 
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * 添加分类（同级下不能添加相同名字的分类）
	 * @param request
	 * @param response
	 * @param tclassify
	 * @return
	 */
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
			resultMap.put("errorMsg", "数据传输错误！");
			resultMap.put("success", false);
		} else {
			Tclassify tclassifyParam = new Tclassify();
			tclassifyParam.setName(tclassifyName);
			tclassifyParam.setPid(tclassifyPid);
			int num = tClassifyService.selectCountBySelectParam(tclassifyParam);
			if(num == 0){
				tclassifyParam.setLevel(tclassifyLevel);
				tclassifyParam.setCode("001");
				tclassifyParam.setIsdisplay(tclassifyisdisplay);
				tclassifyParam.setTorder(1);
				tclassifyParam.setMakedate(currentDate);
				tclassifyParam.setModifydate(currentDate);
				int result = tClassifyService.insertSelective(tclassifyParam);
				if(result <= 0){
					resultMap.put("errorMsg", "插入数据失败！");
					resultMap.put("success", false);
				} else {
					resultMap.put("success", true);
				}
			} else {
				resultMap.put("errorMsg", "同级下不能添加相同名字的分类！");
				resultMap.put("success", false);
			} 
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 修改分类（同级下不能有相同名字的分类）
	 * @param request
	 * @param response
	 * @param tclassify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSelectById", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateSelectById(HttpServletRequest request, HttpServletResponse response, Tclassify tclassify){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Tclassify tclassifyParam = new Tclassify();
		if(tclassify.getId() != null && !"".equals(tclassify.getId())){
			Tclassify classify = tClassifyService.selectByPrimaryKey(tclassify.getId());
			if(classify != null){
				boolean b = true;
				if(tclassify.getName() != null && !"".equals(tclassify.getName())){
					tclassifyParam.setName(tclassify.getName());
					tclassifyParam.setPid(classify.getPid());
					List<Tclassify> tclassifyList = tClassifyService.selectBySelectParam(tclassifyParam);
					for(Tclassify c : tclassifyList){
						if(c.getId() != classify.getId() && c.getName().equals(classify.getName())){
							b = false;
							break;
						}
					}
				}
				if(b){
					tclassifyParam.setId(tclassify.getId());
					if(tclassify.getPid() != null && !"".equals(tclassify.getPid())){
						tclassifyParam.setPid(tclassify.getPid());
					}
					if(tclassify.getIsdisplay() != null && !"".equals(tclassify.getIsdisplay())){
						tclassifyParam.setIsdisplay(tclassify.getIsdisplay());
					}
					tclassifyParam.setModifydate(new Date());
					int result = tClassifyService.updateByPrimaryKeySelective(tclassifyParam);
					if(result <= 0){
						resultMap.put("errorMsg", "数据提交失败！");
						resultMap.put("success", false);
					} else {
						resultMap.put("success", true);
					}
				} else {
					resultMap.put("errorMsg", "同级下不能有相同名字的分类！");
					resultMap.put("success", false);
				} 
			} else {
				resultMap.put("errorMsg", "分类查询失败！");
				resultMap.put("success", false);
			}
		} else {
			resultMap.put("errorMsg", "数据传输错误！");
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}

	/**
	 * 根据条件查询分类（不分页）
	 * @param request
	 * @param response
	 * @param tclassify
	 * @return
	 */
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
