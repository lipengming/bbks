package com.fang.bbks.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro data access helper for User entity
 * @author Lee
 * @Date 2013-8-1
 */
public interface UserDao extends UserDaoCustom,CrudRepository<User, Integer>{
	
	@Modifying
	@Query("update User set delFlag=" + User.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Integer id);
	
	@Query("from User where username = ?1 and password = ?2 and delFlag = " +User.DEL_FLAG_NORMAL)
	public User findByUserNameAndPassword(String name,String pwd);
	
	@Query("from User where username = ?1 and delFlag = " +User.DEL_FLAG_NORMAL)
	public User findByUserName(String userName);
	
	@Query("from User where email = ?1 and delFlag = " +User.DEL_FLAG_NORMAL)
	public User findByEmail(String email);
}

interface UserDaoCustom extends BaseDao<User>{
	
	///////////////////////////////////////
	//			用户关系操作				//				
	//////////////////////////////////////
	
	/**
	 * 用户u 【关注】 id的用户
	 * @param u 当前用户
	 * @param id 被关注人的id
	 * @return
	 */
	public boolean following(User u,Integer id);
	
	/**
	 * 获取用户[id]关注的人
	 * @return
	 */
	public List<User> getFollowing(Integer id);
	
	/**
	 * 获取用户[id]的粉丝
	 * @return
	 */
	public List<User> getFollowed(Integer id);
	
}

@Repository("userDao")
class UserDaoImpl extends BaseDaoImpl<User> implements UserDaoCustom{

	@Override
	public boolean following(User u, Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getFollowing(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFollowed(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
} 
