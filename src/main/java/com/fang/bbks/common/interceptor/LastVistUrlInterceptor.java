package com.fang.bbks.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fang.bbks.common.utils.AjaxUtils;
import com.fang.bbks.common.utils.SessionUtil;

/**
 * @Intro last vist url interceptor
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-11
 * @since 下午1:09:15	
 */
public class LastVistUrlInterceptor implements HandlerInterceptor{
	
	@Autowired
	SessionUtil sessionUtil;
	
	@Autowired
	AjaxUtils ajaxUtils;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		if(ajaxUtils.isAjax(request) && request.getMethod().equals(RequestMethod.GET.name())){
			HttpSession session = request.getSession();
			String referUrl = StringUtils.trimAllWhitespace(request.getHeader("referer"));
			if(StringUtils.hasText(referUrl) &&
					!referUrl.endsWith(".js") &&
					!referUrl.endsWith(".csss")){
				sessionUtil.setLastVistUrl(session, referUrl);
				return true;
			}
		}
		return false;
	}

}
