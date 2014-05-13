package com.fang.bbks.modules.sys.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.sys.entity.Book;

/**
 * @Intro data access helper for book entity
 * @author Lee
 * @Date 2013-8-1
 */
public interface BookDao extends BookDaoCustom,CrudRepository<Book, Long>{
	
	/**
	 * 设置图书为删除状态【实际数据仍旧保留】
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Book set delFlag=" + Book.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Long id);
	
	@Query("from Book where id = ?1 and delFlag = " + Book.DEL_FLAG_NORMAL)
	public Book findOne(Long id);
	
	@Query("from Book where bookName = ?1 or isbn = ?2")
	public Book findByBookNameOrIsbn(String bookName,String isbn);
	
	@Query("from Book where isbn = ?1")
	public Book findByIsbn(String isbn);
}


interface BookDaoCustom extends BaseDao<Book>{
	
}

@Repository("bookDao")
class BookDaoImpl extends BaseDaoImpl<Book> implements BookDaoCustom{
	
} 
