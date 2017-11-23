package com.cn.zbx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.zbx.util.CookiesUtil;

public class LoginFilter implements Filter{
	
	private final Logger log = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String username =  CookiesUtil.getUserName(httpRequest); 
		String url = httpRequest.getRequestURI();
        if(username == null && !url.substring(url.lastIndexOf("/")).equals("/login.jsp")){  
        	httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp");
        } else {
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
