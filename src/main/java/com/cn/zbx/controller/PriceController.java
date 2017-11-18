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
import com.cn.zbx.pojo.Price;
import com.cn.zbx.service.IPriceService;

@Controller
@RequestMapping(value="/price")
public class PriceController {
	
	@Autowired
	IPriceService priceService;
	
	@ResponseBody
	@RequestMapping(value="/selectPriceByParam", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectVideoByParam(HttpServletRequest request, HttpServletResponse response, Price price){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Price priceParam = new Price();
		if(price.getId() != null && !"".equals(price.getId())){
			priceParam.setId(price.getId());
		}
		if(price.getType() != null && !"".equals(price.getType())){
			priceParam.setType(price.getType());
		}
		if(price.getProductId() != null && !"".equals(price.getProductId())){
			priceParam.setProductId(price.getProductId());
		}
		if(price.getPageSize() != null && !"".equals(price.getPageSize())){
			priceParam.setPageSize(price.getPageSize());
		}
		if(price.getPageCount() != null && !"".equals(price.getPageCount())){
			priceParam.setPageCount(price.getPageCount());
		}
		
		int number = priceService.selectCountBySelectParam(priceParam);
		if(number > 0){
			List<Price> priceList = priceService.selectPriceBySelectParam(priceParam);
			resultMap.put("data", priceList);
			resultMap.put("number", number);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
	
}
