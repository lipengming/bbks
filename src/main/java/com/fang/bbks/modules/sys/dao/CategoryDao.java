package com.fang.bbks.modules.sys.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.sys.entity.Category;

/**
 * @Intro data access helper for Category entity
 * @author Lee
 * @Date 2013-8-1
 */
public interface CategoryDao extends CategoryDaoCustom,CrudRepository<Category, Integer>{
	
	@Modifying
	@Query("update Category set delFlag=" + Category.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Integer id);
	
	
}

interface CategoryDaoCustom extends BaseDao<Category>{
	
}

@Repository("categoryDao")
class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDaoCustom{
	
} 
