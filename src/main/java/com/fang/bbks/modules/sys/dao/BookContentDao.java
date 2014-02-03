package com.fang.bbks.modules.sys.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Sort;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.search.FullTextSession;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.modules.sys.entity.BookContent;

/**
 * @Intro data access helper for BookContent entity
 * @author Lee
 * @Date 2013-8-1
 */
public interface BookContentDao extends BookContentDaoCustom,CrudRepository<BookContent, Integer>{
	
	@Modifying
	@Query("update BookContent set delFlag=" + BookContent.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Integer id);
	
	
}

interface BookContentDaoCustom extends BaseDao<BookContent>{
	
}

@Repository("bookContentDao")
class BookContentDaoImpl extends BaseDaoImpl<BookContent> implements BookContentDaoCustom{
	
}