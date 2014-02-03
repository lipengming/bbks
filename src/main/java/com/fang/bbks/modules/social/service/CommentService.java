package com.fang.bbks.modules.social.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.modules.social.dao.CommentDao;
import com.fang.bbks.modules.social.entity.Comment;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("commentService")
public class CommentService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	@Autowired
	CommentDao commentDao;
	
	/**
	 * 给书添加一则评论
	 * @param content 评论内容
	 * @param bookid 书id
	 */
	public void save(String content,Integer bookid){
		Comment comment = new Comment();
		
		comment.setContent(content);
		comment.setIsRead(Comment.READ_HIDE);
		comment.setBid(bookid);
		
		commentDao.save(comment);
	}
	
	/**
	 * 找出书籍bid相关的所有评论
	 * @param bookid
	 * @return
	 */
	public List<Comment> findAll(Integer bookid){
		return commentDao.findByBid(bookid);
	}
	
	/**
	 * [分页]找出书籍bid相关的所有评论
	 * @param page
	 * @param bookid
	 * @return
	 */
	public Page<Comment> findAll(Page<Comment> page,Integer bookid){
		String qlString = "from Comment where bid = ?";
		return commentDao.find(page, qlString, bookid);
	}
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Comment findOne(Integer id){
		return commentDao.findOne(id);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		commentDao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
}
