package com.fang.bbks.modules.${moduleName}.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;

import com.fang.bbks.modules.${moduleName}.entity.${ClassName};

/**
 * @Intro data access helper for ${ClassName} entity
 * @author Lee
 * @Date 2013-8-1
 */
public interface ${ClassName}Dao extends ${ClassName}DaoCustom,CrudRepository<${ClassName}, Integer>{
	
	@Modifying
	@Query("update ${ClassName} set delFlag='" + ${ClassName}.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Integer id);
	
	
}

interface ${ClassName}DaoCustom extends BaseDao<${ClassName}>{
	
}

@Repository
class ${ClassName}DaoImpl extends BaseDaoImpl<${ClassName}> implements ${ClassName}DaoCustom{
	
} 
