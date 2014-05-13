package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.BookMark;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午6:52:50	
 */
public interface BookMarkDao extends BookMarkDaoCustom , CrudRepository<BookMark,Long>{
	@Query("from BookMark where uid = ?1")
	public List<BookMark> findByUid(Long uid);
}

interface BookMarkDaoCustom extends BaseDao<BookMark>{
	
}

@Repository("bookMarkDao")
class BookMarkDaoImpl extends BaseDaoImpl<BookMark> implements BookMarkDaoCustom{
	
}
