package com.fang.bbks.modules.social.service;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.dao.MessageDao;
import com.fang.bbks.modules.social.entity.Message;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("messageService")
@Transactional(readOnly = true)
public class MessageService{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	MessageDao messageDao;
	
	/**
	 * 传送一则消息
	 * @param content
	 * @param from
	 * @param to
	 */
	public void sendMessage(String content,Long from,Long to){
		
		Message m = new Message();
		
		m.setContent(content);
		m.setFromu(from);
		m.setTou(to);
		
		m.setIsRead(Message.READ_HIDE);
		m.setCreatAt(new Date());
		
		messageDao.save(m);
	}
	
	/**
	 * 我发出的消息
	 * @param from
	 * @return
	 */
	public List<Message> ISend(Long from){
		return messageDao.findByFrom(from);
	}
	
	/**
	 * 我收到的消息
	 * @param to
	 * @return
	 */
	public List<Message> IRecived(Long to){
		return messageDao.findByTo(to);
	}
	
	/**
	 * 我的未读消息
	 * @param to
	 * @return
	 */
	public List<Message> unRead(Long to){
		return messageDao.findUnRead(to);
	}
		
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Message findOne(Long id){
		return messageDao.findOne(id);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		messageDao.deleteById(id);
	}
	
	
}
