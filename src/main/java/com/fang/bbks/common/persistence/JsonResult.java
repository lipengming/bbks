package com.fang.bbks.common.persistence;

import java.io.Serializable;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.modules.sys.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午6:54:05	
 */
public class JsonResult implements Serializable{
	private Integer code;
	private String message;
	private Boolean isSuccess;
	private String user_id;
	
	private Object obj;
	private List rows;
	
	public JsonResult(){}
	public JsonResult(HttpServletRequest request){
		User u = SessionUtil.getSignInUser(request.getSession());
		user_id = u.getId()+"";
	}
	
	
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public String toJson(JsonResult jr){
		return new Gson().toJson(jr);
	}
}
