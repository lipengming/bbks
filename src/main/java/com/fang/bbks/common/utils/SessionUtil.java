package com.fang.bbks.common.utils;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.fang.bbks.common.constant.ApplicationCanstant;
import com.fang.bbks.modules.sys.entity.User;

@Component
public class SessionUtil {
	
	/**
	 * 获取最后一次访问URL
	 * 
	 * @param session
	 * @return
	 */
	public static String getLastVisitedUrl(HttpSession session){
		String lastVisitedUrl = (String) session.getAttribute(ApplicationCanstant.SESSION_LAST_VISITED_URL);
		return lastVisitedUrl != null ? lastVisitedUrl : "/";
	}
	
	/**
	 * 设置上次访问的url
	 * @param session
	 * @param url
	 */
	public void setLastVistUrl(HttpSession session,String url ){
		session.setAttribute(ApplicationCanstant.SESSION_LAST_VISITED_URL, url);
	}
	
	/**
	 * 获取登陆用户信息
	 * 
	 * @param session
	 * @return
	 */
	public static User getSignInUser(HttpSession session){
		return (User)session.getAttribute(ApplicationCanstant.APPLICATION_SIGNIN_USER);
	}
	
	/**
	 * 登录--yes
	 * 未登录--No
	 * @param session
	 * @return
	 */
	public static Boolean isLogin(HttpSession session){
		return session.getAttribute(ApplicationCanstant.APPLICATION_SIGNIN_USER) != null;
	}
	
	
	/**
	 * 设置登录用户信息
	 * @param session
	 * @param user
	 */
	public static void setSignInUser(HttpSession session,User user){
		session.setAttribute(ApplicationCanstant.APPLICATION_SIGNIN_USER, user);
	}
	
	/**
	 * 登出
	 * @param session
	 */
	public static void logOut(HttpSession session){
		if(session != null && session.getAttribute(ApplicationCanstant.APPLICATION_SIGNIN_USER) != null){
			session.removeAttribute(ApplicationCanstant.APPLICATION_SIGNIN_USER);
			session.invalidate();
		}
	}
	
}
