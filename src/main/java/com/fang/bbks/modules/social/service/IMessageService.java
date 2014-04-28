package com.fang.bbks.modules.social.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.entity.Message;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-4-28
 * @since 下午4:25:42	
 */
public interface IMessageService {

	/**
	 * 传送一则消息
	 * @param content
	 * @param from
	 * @param to
	 */
	public void sendMessage(String content, Integer from, Integer to);

	/**
	 * 我发出的消息
	 * @param from
	 * @return
	 */
	public List<Message> ISend(Integer from);

	/**
	 * 我收到的消息
	 * @param to
	 * @return
	 */
	public List<Message> IRecived(Integer to);

	/**
	 * 我的未读消息
	 * @param to
	 * @return
	 */
	public List<Message> unRead(Integer to);

	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Message findOne(Integer id);

	@Transactional(readOnly = false)
	public void delete(Integer id);

}