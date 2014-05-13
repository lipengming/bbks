package com.fang.bbks.modules.social.entity;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午6:32:06	
 */
public enum DonateType {
	donate_txt("捐书-txt",0),
	donate_epub("捐书-epub",1);
	
	
	private String type;
	private int code;
	
	private DonateType(String type,int code) {
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
	
	
}
