package com.fang.bbks.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fang.bbks.common.utils.AjaxUtils;
import com.fang.bbks.common.utils.SessionUtil;

/**
 * @Intro last vist url interceptor
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-11
 * @since 下午1:09:15	
 */
public class LastVistUrlInterceptor extends HandlerInterceptorAdapter{
	

	@Autowired
	private SessionUtil sessionUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(!AjaxUtils.isAjax(request) && 
				request.getMethod().equals(RequestMethod.GET.name())){
			
			HttpSession session = request.getSession();
			
			String referUrl = StringUtils.trimAllWhitespace(request.getHeader("referer"));
			
			if(StringUtils.hasText(referUrl) &&
					!referUrl.endsWith(".js") &&
					!referUrl.endsWith(".csss") &&
					!referUrl.contains("/logout") &&
					!referUrl.contains("/login") &&
					!referUrl.contains("/signIn")&&
					!referUrl.contains("/regist") &&
					!referUrl.contains("/signUp")){
				//System.out.println("last vist..." + request.getHeader("referer"));
				sessionUtil.setLastVistUrl(session,request.getHeader("referer"));
			}
		}
		return super.preHandle(request, response, handler);
	}

}
