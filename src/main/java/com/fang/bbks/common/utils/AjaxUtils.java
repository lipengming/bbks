package com.fang.bbks.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-11
 * @since 下午1:11:19	
 */

@Component
public class AjaxUtils {
	
	/**
	 * judge the request is ajax
	 * 
	 * @param request
	 * @return
	 */
	public boolean isAjax(HttpServletRequest request){
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? 
				"XMLHttpRequest".equals(requestedWith)
				: false;
	}
}
