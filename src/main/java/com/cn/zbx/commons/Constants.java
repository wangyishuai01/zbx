package com.cn.zbx.commons;

import java.awt.Color;


/**
 * 业务常量
 * @author wzh
 *
 */
public interface Constants {
	
	
	public static final Integer TOKEN_TIME_OUT = 60 * 60 * 48;

	/******************************************solr请求地址**************************************************/
	
	/**
	 * solr请求地址
	 */
	public static final String SOLR_URL = "http://118.244.157.201:10000/solr";
	
	/**
	 * redis请求地址
	 */
	public static final String REDIS_URL = "118.244.157.201";
	
	/**
	 * redis端口号
	 */
	public static final Integer REDIS_PORT = 6379;
	
	
	
	/******************************************solr请求地址**************************************************/
	
	/**
	 * 未删除状态
	 */
	public static final Integer IS_DELETE_NOT = 1;	
	
	/**
	 * 删除状态
	 */
	public static final Integer IS_DELETE_YES = 2;
	
	
	/******************************************二维码配置信息**************************************************/
	/**
	 * logo默认边框宽度  
	 */
	public static final Integer QRCODE_BORDER = 2;  					 // 
	/**
	 * logo默认边框颜色  
	 */
	public static final Color QRCODE_BORDER_COLOR = Color.WHITE; 
	/**
	 * logo大小默认为照片的1/5  
	 */
	public static final Integer QRCODE_DEFAULT_LOGOPART = 3;  
	/**
	 * 二维码logo路径
	 */
	public static final String QRCODE_LOGO_PATH = "D://1.jpg";
	
	/**
	 * 二维码 宽度
	 */
	public static final Integer QRCODE_WIDTH = 300;  
	
	/** 
	 * 二维码 高度
	 */
	public static final Integer QRCODE_HEIGHT = 300;  
	
}
