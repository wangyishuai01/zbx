package com.cn.zbx.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReturnJsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReturnJsonUtil.class);
	/**
	 * 统一请求返回数据调用方法
	 * @param code
	 * @param msg
	 * @param errorName
	 * @return
	 */
	public static void returnDataMsg(Object data,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("data", data);
		responseWriterPrint(response, JsonUtil.toJson(map));
		map.clear();
	}
	public static void returnDataMsg(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		responseWriterPrint(response, JsonUtil.toJson(map));
		map.clear();
	}
	
	
	/**
	 * 接口相应失败返回数据调用方法
	 * @param success
	 * @param data
	 * @param code
	 * @return
	 */
	public static void returnErrorMsg(String code, Object msg,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("code", code);
		map.put("msg", msg);
		response.setCharacterEncoding("utf-8");
		responseWriterPrint(response, JsonUtil.toJson(map));
		map.clear();
	}
	public static void returnErrorMsg(String code, Object msg, Object data, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("code", code);
		map.put("data", data);
		map.put("msg", msg);
		response.setCharacterEncoding("utf-8");
		responseWriterPrint(response, JsonUtil.toJson(map));
		map.clear();
	}
	
	
	public static void responseWriterPrint(HttpServletResponse response,String jsonStr){
		LOGGER.info(jsonStr);
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(jsonStr);  
			out.flush();  
			out.close();
		} catch (IOException e) {
			//TODO 异常处理
		}
	}
	
}
