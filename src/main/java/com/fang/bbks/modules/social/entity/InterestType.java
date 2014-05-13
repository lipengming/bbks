package com.fang.bbks.modules.social.entity;

import com.fang.bbks.common.utils.StringUtils;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午6:32:06	
 */
public enum InterestType {
	unknow("未知",-1),
	book_search("搜过",0),
	book_reading("在读",1),
	book_wantRead("想读",2),
	book_hasRead("已读",3),
	book_like("喜欢",4);
	
	
	private String type;
	private int code;
	
	private InterestType(String type,int code) {
		this.type = type;
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public static InterestType getTypeById(Integer code){
		if(code == null){
			return unknow;
		}
		for(InterestType type : values()){
			if(code == type.getCode()){
				return type;
			}
		}
		return unknow;
	}
	public static InterestType getTypeByType(String type){
		if(StringUtils.isEmpty(type)){
			return unknow;
		}
		for(InterestType typeInfo : values()){
			if(type.equals(typeInfo.getType())){
				return typeInfo;
			}
		}
		return unknow;
	}
}
