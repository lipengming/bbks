package com.fang.bbks.modules.sys.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.sys.entity.Resource;

/**
 * @Intro data access helper for Resource entity
 * @author Lee
 * @Date 2013-8-1
 */
public interface ResourceDao extends ResourceDaoCustom,CrudRepository<Resource, Integer>{
	
	@Modifying
	@Query("update Resource set delFlag='" + Resource.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Integer id);
	
	
}

interface ResourceDaoCustom extends BaseDao<Resource>{
	
}

@Repository("resourceDao")
class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDaoCustom{
	
} 
