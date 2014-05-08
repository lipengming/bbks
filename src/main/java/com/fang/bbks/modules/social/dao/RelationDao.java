package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.Message;
import com.fang.bbks.modules.social.entity.RelationShip;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-8
 * @since 下午3:40:26	
 */
public interface RelationDao extends RelationDaoCustm,CrudRepository<RelationShip,Long>{
	@Modifying
	@Query("update RelationShip set delFlag=" + RelationShip.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Long id);
	
	@Modifying
	@Query("update RelationShip set delFlag=" + RelationShip.DEL_FLAG_DELETE + " where flowId = ?1 and flowedId=?2")
	public int unFlow(Long flowid,Long flowedId);
	
	
	/**
	 * 粉丝
	 * @param uid
	 * @return
	 */
	@Query("from RelationShip where flowedId = ?1 and  delFlag=" + RelationShip.DEL_FLAG_NORMAL)
	public List<RelationShip> findFlowing(Long uid);
	
	/**
	 * 偶像
	 * @param uid
	 * @return
	 */
	@Query("from RelationShip where flowId = ?1  and  delFlag=" + RelationShip.DEL_FLAG_NORMAL)
	public List<RelationShip> findFlowed(Long uid);
	
	/**
	 * 查询二者之间的关系
	 * @param fing
	 * @param fed
	 * @return
	 */
	@Query("from RelationShip where flowId = ?1 and flowedId=?2  and  delFlag=" + RelationShip.DEL_FLAG_NORMAL)
	public RelationShip findOne(Long fing,Long fed);
}



interface RelationDaoCustm extends BaseDao<RelationShip>{
}

@Repository("relationDao")
class RelationDaoImpl extends BaseDaoImpl<RelationShip> implements RelationDaoCustm{
	
}