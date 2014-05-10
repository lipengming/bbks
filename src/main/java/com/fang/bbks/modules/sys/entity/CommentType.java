package com.fang.bbks.modules.sys.entity;

/**
 * @Intro 评论类型
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午7:55:19	
 */
public enum CommentType {
	BOOK(1,"book"),
	USER(2,"user"),
	DYNAMIC(3,"dynamic");
	
	private Integer code;
	private String type;
	
	private CommentType(int code,String type) {
		this.code = code;
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
