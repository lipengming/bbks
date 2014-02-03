/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.common.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;

/**
 * @author Lee
 */
public class UserUtils  implements ApplicationContextAware {
	
	//@Autowired
	private static UserService userService;
	
	/**
	 * 静态方法！！！！！！！！
	 * 获取当前用户信息
	 * @return
	 */
	public static User getUser(){
		User user = (User)getCache("user");
		if (user == null){
			Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = null;//userService.getByUserName(principal.getName(),principal.getName());
				putCache("user", user);
			}
		}
		return user;
	}
	
	
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		userService = (UserService)applicationContext.getBean("userService");
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		Object obj = getCacheMap().get(key);
		return obj==null?null:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	private static Map<String, Object> getCacheMap(){
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		return principal!=null?principal.getCacheMap():new HashMap<String, Object>();
	}
}
