package com.fang.bbks.modules.sys.service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.utils.MD5;
import com.fang.bbks.modules.social.dao.DynamicDao;
import com.fang.bbks.modules.social.dao.MessageDao;
import com.fang.bbks.modules.social.dao.RelationDao;
import com.fang.bbks.modules.social.service.RelationService;
import com.fang.bbks.modules.sys.dao.UserDao;
import com.fang.bbks.modules.sys.entity.User;
import com.google.common.collect.Maps;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDao userDao;
	
	
	/**
	 * 跟新个人状态
	 * @param description
	 */
	@Transactional(readOnly = false)
	public void updateState(String description,Long id){
		userDao.update("update User set description = ? where id = ?", description,id);
	}
	
	@Transactional(readOnly = false)
	public void updateAvatar(String avatar,Long id){
		userDao.update("update User set avatar = ? where id = ?", avatar,id);
	}
	
	
	public User findOne(Long id){
		return userDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		userDao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
	
	@Transactional(readOnly = false)
	public User signUp(String name,String email,String pwd){
		User user = new User();
		user.setUsername(name);
		user.setEmail(email);
		try {
			user.setPassword(MD5.encode(pwd));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			user.setPassword(pwd);
		}
		return userDao.save(user);
	}
	
	public User signIn(String name,String pwd){
		String md5;
		try {
			md5 = MD5.encode(pwd);
		} catch (NoSuchAlgorithmException e) {
			md5 = pwd;
			e.printStackTrace();
		}
		
		User u = userDao.findByUserNameAndPassword(name, md5);
		if(u == null || !u.getUsername().equals(u.getUsername())){
			return null;
		}
		
		return u;
	}
	
	/**
	 * 判断用户名为name的用户是否存在
	 * ture 存在
	 * @param name
	 * @return
	 */
	public boolean isExit(String name){
		return userDao.findByUserName(name) != null ;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean isExitEmail(String email){
		return userDao.findByEmail(email) != null ;
	}
	
	/**
	 * 关注
	 * @param flow
	 * @param flowed
	 */
	@Transactional(readOnly=false)
	public void flow(Long flow, Long flowed){
		int count = findOne(flow).getFlowings() + 1;
		int counted = findOne(flowed).getFloweds() + 1;
		
		this.flow( flow, count,  flowed, counted);
	}
	
	@Transactional(readOnly=false)
	public void unflow(Long flow, Long flowed){
		int count = findOne(flow).getFlowings() - 1;
		int counted = findOne(flowed).getFloweds() - 1;
		
		this.flow( flow, count,  flowed, counted);
	}
	
	/**
	 * 关注
	 * @param flow
	 * @param flowed
	 */
	public void flow(Long flow,int count, Long flowed,int counted){
		userDao.setFlowing(count,flow);
		userDao.setFlowed(counted,flowed);
	}
}
