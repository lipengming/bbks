package com.fang.bbks.modules.sys.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.sys.entity.Comment;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午7:45:39	
 */
public interface CommentDao extends CommentDaoCustom,CrudRepository<Comment, Long> {
	@Modifying
	@Query("update Comment set delFlag=" + Comment.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Long id);
	
	@Query("from Comment where id = ?1 and delFlag = " + Comment.DEL_FLAG_NORMAL)
	public Comment findOne(Long id);
}


interface CommentDaoCustom extends BaseDao<Comment>{
	
}

@Repository("commontDao")
class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDaoCustom{
	
}