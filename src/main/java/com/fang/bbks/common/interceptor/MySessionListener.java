package com.fang.bbks.common.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fang.bbks.common.constant.ApplicationCanstant;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-9
 * @since 下午6:15:51	
 */
public class MySessionListener implements Filter{
	private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();   
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//尝试获取session
	    HttpSession session = ((HttpServletRequest)request).getSession(false);
	    if(session == null){
	    	return;
	    }
	    User login = (User) session.getAttribute(ApplicationCanstant.APPLICATION_SIGNIN_USER);
	    User user = (User) threadLocal.get();
	    
	    if(login != null && user == null){
	    	threadLocal.set(login);
	    }
	    
	    if(user != null && login == null){
	    	System.out.println("登陆超时。。。。");
	    }
	    
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
