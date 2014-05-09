package com.fang.bbks.modules.sys.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.utils.StringUtils;
import com.fang.bbks.modules.sys.dao.CommentDao;
import com.fang.bbks.modules.sys.entity.Comment;
import com.fang.bbks.modules.sys.entity.CommentType;
import com.fang.bbks.modules.sys.entity.User;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午7:50:28	
 */

@Service("commentService")
@Transactional(readOnly = true)
public class CommentService {
	
	@Resource
	private CommentDao commentDao;
	@Autowired
	private SessionUtil sessionUtil;
	
	/**
	 * 添加评论
	 * @param type
	 * @param request
	 * @param comment--必须具有插入contentId,content
	 * @return
	 */
	public Comment addComment(CommentType type,HttpServletRequest request,Comment comment){
		User user = sessionUtil.getSignInUser(request.getSession());
		if(user == null){
			return null;
		}
		return addComment(type, comment, user);
	}
	
	public Comment addComment(CommentType type,Comment comment,User user){
		
		comment.setModule(type.getType());
		comment.setCreateDate(new Date());
		
		comment.setName(user.getUsername());
		comment.setAvatar(user.getAvatar());
		
		return commentDao.save(comment);
	}
	
	public List<Comment> find(CommentType type,Long contentId, Comment comment){
		DetachedCriteria dc = commentDao.createDetachedCriteria();
		
		dc.add(Restrictions.eq("module",type.getType()));
		dc.add(Restrictions.eq("contentId",contentId));
		
		if(StringUtils.isNotBlank(comment.getName())){
			dc.add(Restrictions.eq("name",comment.getName()));
		}
		
		dc.addOrder(Order.desc("createDate"));
		
		return commentDao.find(dc);
	}
}
