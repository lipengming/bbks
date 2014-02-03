package com.fang.bbks.modules.social.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.Relation;
import com.fang.bbks.modules.sys.entity.BookContent;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-19
 * @since 下午5:27:16	
 */
public interface RelationDao extends RelationDaoCumstom , CrudRepository<Relation, Integer>{
	@Modifying
	@Query("update Relation set delFlag=" + BookContent.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Integer id);
}


interface RelationDaoCumstom extends BaseDao<Relation>{
	/**
	 * 【登录】用户关注某人
	 * @param u 【登录】用户
	 * @param uid 被关注的用户id
	 */
	public void followSomeOne(User u,Integer uid);
	
	/**
	 * 取消对id用户的关注
	 * @param u 【登录】用户
	 * @param uid 被关注的用户id
	 */
	public void unFollowSomeOne(User u,Integer uid);
	
	/**
	 * 获取用户关注的人
	 * @param uid 用户
	 */
	public void getFollowings(Integer uid);
	/**
	 * 获取用户的粉丝
	 * @param uid 用户
	 */
	public void getFolloweds(Integer uid);
	
}

@Repository("relationDao")
class RelationDaoImpl extends BaseDaoImpl<Relation> implements RelationDaoCumstom{

	@Override
	public void followSomeOne(User u, Integer uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unFollowSomeOne(User u, Integer uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFollowings(Integer uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFolloweds(Integer uid) {
		// TODO Auto-generated method stub
		
	}
	
}