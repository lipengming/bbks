package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.Dynamic;
import com.fang.bbks.modules.sys.entity.BookContent;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-22
 * @since 下午6:21:10	
 */
public interface DynamicDao extends DynamicDaoCustm, CrudRepository<Dynamic, Long>{
	@Modifying
	@Query("update Dynamic set delFlag = " + Dynamic.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Long id);
	
	@Query("from Dynamic where delFlag = '" + Dynamic.DEL_FLAG_NORMAL + "' and creatBy = ?1")
	public List<Dynamic> findByCreatBy(Long creatBy);
	
	
}

interface DynamicDaoCustm extends BaseDao<Dynamic>{
	/**
	 * 【登录】用户发布个人动态消息
	 * @param u 【登录】用户
	 * @param dynamic 动态消息
	 */
	public void publish(User u,Dynamic dynamic);
	
	/**
	 * 【登录】用户 相关的 圈子 的动态
	 * @param u 【登录】用户
	 * @param uids 用户id集合
	 * @return
	 */
	public List<Dynamic> groupDynamics(User u, List<Long> uids); 
}

@Repository("dynamicDao")
class DynamicDaoImpl extends BaseDaoImpl<Dynamic> implements DynamicDaoCustm{

	@Override
	public void publish(User u, Dynamic dynamic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Dynamic> groupDynamics(User u, List<Long> uids) {
		// TODO Auto-generated method stub
		return null;
	}
	
}