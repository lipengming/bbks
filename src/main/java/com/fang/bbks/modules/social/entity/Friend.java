package com.fang.bbks.modules.social.entity;

import java.io.Serializable;
import java.util.List;

import com.fang.bbks.modules.sys.entity.User;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-12
 * @since 下午12:40:50	
 */
public class Friend implements Serializable{
    private Long id;
    private String userName;
    private String gender;
    private String face;
    private String description;
	
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    public static List<Friend> transfer(List<User> users){
    	List<Friend> fs = Lists.newArrayList();
    	for(User u : users){
    		
    		Friend f = new Friend();
    		
    		f.setId(u.getId());
    		f.setDescription(u.getDescription());
    		f.setGender(u.getGender());
    		f.setFace(u.getAvatar());
    		f.setUserName(u.getUsername());
    		
    		fs.add(f);
    	}
    	return fs;
    }
}
