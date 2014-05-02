package com.fang.bbks.common.persistence;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午7:04:36	
 */
public enum Code {
	UNKNOWN(-1,""),
	EXCEPTION(0,"未知异常"),
	DATABASE_EXCEPTION(1,"数据库异常"),
	SUCCESS(2,"成功"),
	FALIL(3,"失败")
	;
	private Integer code;
	private String message;
		
	private Code(int code,String message){
		this.code = code;
		this.message = message;
	}
	
}
