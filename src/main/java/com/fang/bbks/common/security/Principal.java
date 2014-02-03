/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.common.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fang.bbks.modules.sys.entity.User;

/**
 * @author Lee
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Map<String, Object> cacheMap;

	public Principal(User user) {
		this.id = user.getId();
		this.name = user.getUsername();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Map<String, Object> getCacheMap() {
		if (cacheMap==null){
			cacheMap = new HashMap<String, Object>();
		}
		return cacheMap;
	}

}
