/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fang.bbks.common.web;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fang.bbks.common.utils.BeanValidators;
import com.fang.bbks.common.utils.DateUtils;
import com.fang.bbks.common.utils.SessionUtil;



/**
 * 控制器支持类
 */
public abstract class BaseController {
	
	/**
	 * 设置管理端访问路径（ADMIN_PATH或FRONT_PATH可允许一个为空）
	 * 1. 修改本类 ADMIN_PATH 常量
	 * 2. 修改 applicationContext-shiro.xml 中的 shiroFilter
	 * 3. 修改 decorators.xml 中的 default
	 * 4. 修改 spring-mvc.xml 中的 mvc:view-controller
	 */
	public static final String ADMIN_PATH = "/a";
	
	/**
	 * 设置网站前端路径（ADMIN_PATH或FRONT_PATH可允许一个为空）
	 * 1. 修改本类 FRONT_PATH 常量
	 * 2. 修改 spring-mvc.xml 中的 mvc:view-controller
	 */
	public static final String FRONT_PATH = "/f";

	/**
	 * 设置访问URL后缀
	 */
	public static final String URL_SUFFIX = ".html";
	
	/**
	 * API
	 */
	public static final String REST_PREFIX = "/api";
	
	protected final static String HANDLER_MSG = "handler_msg";

	
	/**
	 * 请求对象
	 */
	protected HttpServletRequest request;
	
	/**
	 * 响应对象
	 */
	protected HttpServletResponse response;
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;


	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	*/ 
	@InitBinder
	protected void initBinder(WebDataBinder binder, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		//将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}

	/////////////////////////////////////////////////////////
	
	public static String getAdminPath() {
		return ADMIN_PATH;
	}

	public static String getFrontPath() {
		return FRONT_PATH;
	}

	public static String getUrlSuffix() {
		return URL_SUFFIX;
	}
	
	/////////////////////////////////////////////////////////
	
	/**
	 * 请求失败数据格式
	 * @return
	 */
	public BaseResponse defaultNotImply(){
		return new BaseResponse(402, "接口尚未实现");
	}
	
	/**
	 * 请求成功
	 * @return
	 */
	public BaseResponse defaultOK(){
		return new BaseResponse(200, "请求完成");
	}
	
	/**
	 * 请求成功
	 * @param message
	 * @return
	 */
	public BaseResponse defaultOK(String message){
		return new BaseResponse(200, message);
	}
	
	
	/**
	 * 请求失败数据格式
	 * @return
	 */
	public BaseResponse defaultError(){
		return new BaseResponse(400, "Token失效");
	}
	
	/**
	 * 请求失败数据格式
	 * @param message
	 * @return
	 */
	public BaseResponse defaultError(String message){
		return new BaseResponse(400, message);
	}
	
	/**
	 * 校验成功
	 * @return
	 */
	public BaseResponse validateOK(){
		return new BaseResponse(200, "校验通过");
	}
	
	/**
	 * 校验成功
	 * @return
	 */
	public BaseResponse validateOK(String message){
		return new BaseResponse(200, message);
	}
	
	/**
	 * 校验失败
	 * @return
	 */
	public BaseResponse validateError(){
		return new BaseResponse(400, "校验失败");
	}
	
	/**
	 * 校验失败
	 * @return
	 */
	public BaseResponse validateError(String message){
		return new BaseResponse(400, message);
	}
	
	public static String getBaseRepository(){
		return "/static/upload";
	}
	
	public static String getRepositor(Long uid){
		return getBaseRepository() + "/" + uid;
	}
	
	public static String getAvatarRepositor(Long uid){
		return getRepositor(uid) + "/" + "avatar";
	}
	
	public static String getNewFileName(String orgName){
		return new StringBuilder().append(System.currentTimeMillis())
			.append(orgName.substring(orgName.lastIndexOf('.'))).toString();
	}
}
