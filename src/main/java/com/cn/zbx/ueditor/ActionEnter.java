package com.cn.zbx.ueditor;

import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.ConfigManager;

public class ActionEnter {
	private HttpServletRequest request = null;

	private String rootPath = null;
	private String contextPath = null;

	private String actionType = null;

	private ConfigManager configManager = null;
	/** action统一入口 **/
	public ActionEnter ( HttpServletRequest request, String rootPath ) {

	    this.request = request;
	    /** rootPath = /Test/ **/
	    this.rootPath = rootPath;
	    /** actionType = config **/
	    this.actionType = request.getParameter( "action" );
	    /** contextPath = /Test **/
	    this.contextPath = request.getContextPath();
	    /** 调用ConfigManager **/
	    this.configManager = ConfigManager.getInstance( this.rootPath, this.contextPath, request.getRequestURI() );

	}

}
