package com.fang.bbks.common.web;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * @Intro 基本的结果类型
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-4
 * @since 下午8:18:53	
 */
@SuppressWarnings("serial")
public class BaseResponse implements Serializable{
	private Integer code;
	private String message;
	
	public BaseResponse(int code , String message){
		this.code = code;
		this.message = message;
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
	
	
}
