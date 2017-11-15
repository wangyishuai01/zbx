package com.cn.zbx.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonInterceptor.class);

	/**  
     * 在业务处理器处理请求之前被调用 
     * 如果返回false   从当前的拦截器往回执行所有拦截器的afterCompletion(),再�??出拦截器�? 
     * 如果返回true  
     *    执行下一个拦截器,直到�?有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器�?回执行所有的postHandle()  
     *    接着再从�?后一个拦截器�?回执行所有的afterCompletion()  
     */     
    @Override    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	response.setCharacterEncoding("utf-8");
//    	LOGGER.info("========================================================拦截�?1========================================================================");    
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        if(url.length() > 0){
//        	String substring = url.substring(1,url.length());
//        	String className = substring.substring(0, substring.indexOf("/"));//调用的类�?
//        	String funcName = substring.substring(substring.indexOf("/")+1,substring.length());//调用的方法名
        	LOGGER.info("requestUri ---- => :{}",requestUri );   
//        	LOGGER.info("contextPath --- => :{}",contextPath );  
//        	LOGGER.info("url ----------- => :{}",url );  
//        	LOGGER.info("str ----------- => :{}",className );  
//        	LOGGER.info("strF ---------- => :{}",funcName );  
        }
        
        return true;
    }    
    
    /** 
     * 在业务处理器处理请求执行完成�?,生成视图之前执行的动�?    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {     
//    	LOGGER.info("==============执行顺序: 2、postHandle================");    
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调�?,可用于清理资源等   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {    
//    	LOGGER.info("==============执行顺序: 3、afterCompletion================");    
    }    
}
