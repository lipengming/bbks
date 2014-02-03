package com.fang.bbks.modules.social.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.dao.RelationDao;
import com.fang.bbks.modules.social.entity.Relation;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("relationService")
@Transactional(readOnly = true)
public class RelationService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(RelationService.class);
	
	@Autowired
	RelationDao relationDao;
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Relation findOne(Integer id){
		return relationDao.findOne(id);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		relationDao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
	
	
}
