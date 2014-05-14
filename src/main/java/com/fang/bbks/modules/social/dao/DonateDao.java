package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.Donate;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午6:53:01	
 */
public interface DonateDao extends DonateDaoCustom , CrudRepository<Donate,Long>{
	@Query("from Donate where uid=?1")
	public List<Donate> findByUid(Long uid);
}

interface DonateDaoCustom extends BaseDao<Donate>{
	
}

@Repository("donateDao")
class DonateDaoImpl extends BaseDaoImpl<Donate> implements DonateDaoCustom{
	
}
