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
import com.cn.zbx.pojo.Customer;
import com.cn.zbx.service.ICustomerService;
import com.cn.zbx.vo.CustomerVO;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@ResponseBody
	@RequestMapping(value="/initCustomerMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String iniCustomerMain(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String pageCount = request.getParameter("pageCount");
		String pageSize = request.getParameter("pageSize");
		if(pageCount == null || "".equals(pageCount)){
			pageCount = "1";
		}
		if(pageSize == null || "".equals(pageSize)){
			pageSize = "10";
		}
		Customer CustomerParam = new Customer();
		CustomerParam.setPageCount(Integer.valueOf(pageCount));
		CustomerParam.setPageSize(Integer.valueOf(pageSize));
		int number = customerService.selectCountBySelectParam(CustomerParam);
		if(number > 0){
			List<CustomerVO> commentList = customerService.selectBySelectParam(CustomerParam);
			resultMap.put("data", commentList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/customerEditState", method = { RequestMethod.GET, RequestMethod.POST })
	public String customerEditState(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String CustomerId = request.getParameter("customerId");
		String isdisable = request.getParameter("isdisable");
		if(CustomerId != null && !"".equals(CustomerId)){
			if(isdisable == null || "".equals(isdisable)){
				isdisable = "1";
			}
			Customer CustomerParam = new Customer();
			CustomerParam.setId(Integer.valueOf(CustomerId));;
			CustomerParam.setIsdisable(Integer.valueOf(isdisable));
			CustomerParam.setModifydate(new Date());
			int result = customerService.updateByPrimaryKeySelective(CustomerParam);
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
	
	@ResponseBody
	@RequestMapping(value="/customerEditPassword", method = { RequestMethod.GET, RequestMethod.POST })
	public String customerEditPassword(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String CustomerId = request.getParameter("customerId");
		String password = request.getParameter("password");
		if(CustomerId != null && !"".equals(CustomerId)){
			Customer CustomerParam = new Customer();
			CustomerParam.setId(Integer.valueOf(CustomerId));;
			CustomerParam.setPassword(password);
			CustomerParam.setModifydate(new Date());
			int result = customerService.updateByPrimaryKeySelective(CustomerParam);
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
	
	
}
