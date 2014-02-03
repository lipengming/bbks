package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.Comment;
import com.fang.bbks.modules.sys.entity.BookContent;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro 评论类【图书】
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-22
 * @since 下午6:20:34	
 */
public interface CommentDao extends CommentDaoCustm,CrudRepository<Comment, Integer>{
	@Modifying
	@Query("update Comment set delFlag=" + BookContent.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Integer id);
	
	@Query("from Comment where bid = ?1")
	public List<Comment> findByBid(Integer bid);
	
}

interface CommentDaoCustm extends BaseDao<Comment>{
	/**
	 * 评论书籍
	 * @param commentUser 评论人
	 * @param content 内容
	 * @param bookId 书籍的Id
	 */
	public void makeComment(User commentUser,String content,Integer bookId);
	
	/**
	 * 列出书籍的评论
	 * @param bookId
	 */
	public List<Comment> listComment(Integer bookId);
	
}

@Repository("commentDao")
class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDaoCustm{
	
	
	@Override
	public void makeComment(User commentUser, String content, Integer bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> listComment(Integer bookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
