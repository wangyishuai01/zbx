package com.cn.zbx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.zbx.service.IBUserService;

@Controller
@RequestMapping(value="/bUser")
public class BUserController {
	
	@Autowired
	IBUserService bUserService;
	
	/**
	 * 根据用户名修改密码
	 * @param request
	 * @param response
	 * @param price
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editLoginPwd", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectVideoByParam(HttpServletRequest request, HttpServletResponse response, String userCode, String password){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(userCode == null || "".equals(userCode)){
			resultMap.put("errorMsg", "用户名不能为空！");
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		if(password == null || "".equals(password)){
			resultMap.put("errorMsg", "密码不能为空！");
			resultMap.put("success", false);
			return JSONObject.toJSONString(resultMap);
		}
		resultMap = bUserService.updateByPrimaryKeySelective(userCode, password);
		return JSONObject.toJSONString(resultMap);
	}
	
}
