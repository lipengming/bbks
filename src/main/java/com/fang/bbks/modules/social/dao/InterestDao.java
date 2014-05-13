package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.BookMark;
import com.fang.bbks.modules.social.entity.Interest;
import com.fang.bbks.modules.social.entity.InterestType;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午6:54:25	
 */
public interface InterestDao extends InterestDaoCustom , CrudRepository<Interest,Long>{
	@Query("from Interest where uid = ?1")
	public List<Interest> findByUid(Long uid);
	
	@Query("from Interest where uid = ?1 and type=?2")
	public List<Interest> findByUidAndType(Long uid,InterestType type);
	
	@Query("from Interest where uid = ?1 and mid=?2 and type = ?3")
	public Interest findByUidAndMidAndtype(Long uid,Long mid,InterestType type);
}

interface InterestDaoCustom extends BaseDao<Interest>{
	
}

@Repository("interestDao")
class InterestDaoImpl extends BaseDaoImpl<Interest> implements InterestDaoCustom{
	
}