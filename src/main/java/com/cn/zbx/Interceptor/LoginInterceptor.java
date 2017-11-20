package com.cn.zbx.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.zbx.util.CookiesUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);
	
	/**  
     * 在业务处理器处理请求之前被调用 
     * 如果返回false   从当前的拦截器往回执行所有拦截器的afterCompletion() 
     * 如果返回true  
     *    执行下一个拦截器,直到�?有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器返回执行所有的postHandle()  
     *    接着再从最后一个拦截器返回执行所有的afterCompletion()  
     */    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if ("GET".equalsIgnoreCase(request.getMethod())) {  
            super.preHandle(request, response, handler); 
        }  
        log.info("==============执行顺序: 1、preHandle================");    
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        
        log.info("requestUri:"+requestUri);    
        log.info("contextPath:"+contextPath);    
        log.info("url:"+url);    
          
        String username =  CookiesUtil.getUserName(request);   
        if(username == null){  
            log.info("Interceptor：跳转到login页面！");  
            request.getRequestDispatcher("/login.jsp").forward(request, response);  
            return false;  
        } else { 
            return true;
		}
	}

	/** 
     * 在业务处理器处理请求执行完成时,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	/**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */ 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
