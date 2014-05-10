package com.fang.bbks.modules.sys.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
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
	 * @param comment--必须具有插入module,contentId,content
	 * @return
	 */
	public Comment addComment(HttpServletRequest request,Comment comment){
		User user = sessionUtil.getSignInUser(request.getSession());
		if(user == null){
			return null;
		}
		return addComment(comment, user);
	}
	
	public Comment addComment(Comment comment,User user){
		
		comment.setName(user.getUsername());
		comment.setAvatar(user.getAvatar());
		comment.setUid(user.getId());
		
		comment.setCreateDate(new Date());
		
		return commentDao.save(comment);
	}
	
	/**
	 * 
	 * @param type---评论类型【book：图书；user:用户动态；】
	 * @param contentId---原题目id【如，图书id】
	 * @param comment---not null 评论内容【】
	 * @return
	 */
	public List<Comment> find(CommentType type,Long contentId, Comment comment){
		DetachedCriteria dc = commentDao.createDetachedCriteria();
		
		dc.add(Restrictions.eq("module",type.getType()));
		dc.add(Restrictions.eq("contentId",contentId));
		
		//评论人
		if(StringUtils.isNotBlank(comment.getName())){
			dc.add(Restrictions.eq("name",comment.getName()));
		}
		//评论的标题
		if(StringUtils.isNotBlank(comment.getTitle())){
			dc.add(Restrictions.like("title",comment.getTitle(),MatchMode.ANYWHERE));
		}
		
		
		dc.addOrder(Order.desc("createDate"));
		
		return commentDao.find(dc);
	}
	
	public Page<Comment> find(Page<Comment> page, Comment comment){
		DetachedCriteria dc = commentDao.createDetachedCriteria();
		
		if(comment.getContentId() != null){
			dc.add(Restrictions.eq("contentId",comment.getContentId()));
		}
		if(StringUtils.isNotBlank(comment.getModule())){
			dc.add(Restrictions.eq("module",comment.getModule()));
		}
		
		//评论人
		if(StringUtils.isNotBlank(comment.getName())){
			dc.add(Restrictions.eq("name",comment.getName()));
		}
		//评论的标题
		if(StringUtils.isNotBlank(comment.getTitle())){
			dc.add(Restrictions.like("title",comment.getTitle(),MatchMode.ANYWHERE));
		}
		
		
		dc.addOrder(Order.desc("id"));
		
		return commentDao.find(page, dc);
	}
}
