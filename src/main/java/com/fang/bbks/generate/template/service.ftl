package com.fang.bbks.modules.${moduleName}.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.${moduleName}.dao.${ClassName}Dao;
import com.fang.bbks.modules.${moduleName}.entity.${ClassName};

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("${className}Service")
@Transactional(readOnly = true)
public class ${ClassName}Service {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(${ClassName}Service.class);
	
	@Autowired
	${ClassName}Dao ${className}Dao;
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public ${ClassName} findOne(Integer id){
		return ${className}Dao.findOne(id);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		${className}Dao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
	
	
}
