package com.fang.bbks.modules.social.service;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.modules.social.dao.DynamicDao;
import com.fang.bbks.modules.social.entity.Dynamic;
import com.fang.bbks.modules.sys.dao.UserDao;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("dynamicService")
@Transactional(readOnly = true)
public class DynamicService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DynamicService.class);
	
	@Autowired
	private DynamicDao dynamicDao;
	@Autowired
	private UserDao userDao; 
	
	/**
	 * 发布动态
	 * @param content
	 * @param uid
	 */
	@Transactional(readOnly = false)
	public void publishDynamic(String content,Long uid){
		Dynamic d = new Dynamic();
		User u = userDao.findOne(uid);
		
		d.setCreatBy(uid);
		d.setContent(content);
		
		d.setCreateAt(new Date());
		d.setCreatedname(u.getUsername());
		d.setCreatedAvatar(u.getAvatar());
		
		dynamicDao.save(d);
	}
	
	/**
	 * 我的动态
	 * @param creatBy
	 * @return
	 */
	public List<Dynamic> listDynamic(Long creatBy){
		return dynamicDao.findByCreatBy(creatBy);
	}
	
//	public List<Dynamic> listDynamic(List<Long> uids){
//		return null;
//	}
	
//	public Page<Dynamic> page(Page<Dynamic> page,List<Long> uids){
//		
//	}
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Dynamic findOne(Long id){
		return dynamicDao.findOne(id);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		dynamicDao.deleteById(id);
	}
	
	
}
