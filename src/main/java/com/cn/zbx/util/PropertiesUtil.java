package com.cn.zbx.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取配置文件工具类
 * @author zyw
 *
 */
public class PropertiesUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);
	
	public static Properties getProperties(String path) {
		Properties pro = new Properties();
		try {
			pro.load(PropertiesUtil.class.getResourceAsStream(path));
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return pro;
	}
	
	public static String get(Properties properties, String key) {
		if(null == properties){
			return null;
		}else{
			return properties.getProperty(key);
		}
	}
	
	
}