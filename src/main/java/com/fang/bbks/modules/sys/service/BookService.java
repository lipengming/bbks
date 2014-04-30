package com.fang.bbks.modules.sys.service;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.common.utils.StringUtils;
import com.fang.bbks.modules.sys.dao.BookDao;
import com.fang.bbks.modules.sys.entity.Book;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("bookService")
@Transactional(readOnly = true)
public class BookService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	BookDao bookDao;
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Book findOne(Integer id){
		return bookDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Book save(Book book){
		return bookDao.save(book);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		bookDao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
	
	/**
	 * 全文检索
	 * @param page
	 * @param keywords
	 * @return
	 */
	public Page<Book> findByKeyWords(Page<Book> page,String keywords){
		// 设置查询条件
		BooleanQuery query = bookDao.getFullTextQuery(keywords, "bookName","outline","author");
		// 设置过滤条件
		BooleanQuery queryFilter = bookDao.getFullTextQuery(new BooleanClause(
				new TermQuery(new Term("delFlag", Book.DEL_FLAG_NORMAL+"")), Occur.MUST));
		// 设置排序
		//Sort sort = new Sort(new SortField("updateTime", SortField.DOC, true));
		// 全文检索
		bookDao.search(page, query, queryFilter, null);
		// 关键字高亮
		bookDao.keywordsHighlight(query, page.getList(), "outline");
		
		return page;		
	}
	
	/**
	 * 找书
	 */
	public Page<Book> findBook(Page<Book> page,Book book){
		DetachedCriteria dc = bookDao.createDetachedCriteria();
		//设置查询条件
		addCondition(dc,book);
		//dc.add(Restrictions.eq("delFlag", Book.DEL_FLAG_NORMAL));
		//dc.addOrder(Order.asc("likeCount"));
		return bookDao.find(page, dc);
	}
	
	public Page<Book> findBookByCatLog(Page<Book> page,String catelog){
		
		return null;
	}
	
	/**
	 * 添加查询条件
	 * @param dc
	 * @param book
	 */
	private void addCondition(DetachedCriteria dc,Book book){
		if(book == null) return;
		if(book.isFree()){
			//查询免费书籍
			dc.add(Restrictions.eq("isFreee", book.isFree()));
		}
		if(StringUtils.isNotEmpty(book.getBookName())){
			//根据书名查询
			dc.add(Restrictions.like("bookName", book.getBookName()+"%"));
		}
		if(StringUtils.isNotEmpty(book.getAuthor())){
			//根据作者查询
			dc.add(Restrictions.like("author", book.getAuthor()+"%"));
		}
		if(StringUtils.isNotEmpty(book.getTranslator())){
			dc.add(Restrictions.like("thranslator", book.getTranslator()+"%"));
		}
	}
	
	/**
	 * 跟新索引
	 */
	public void createdAndUpdateIndex(){
		long startTime=System.currentTimeMillis();   //获取开始时间
		logger.debug("执行索引开始。。。{}",startTime);
		bookDao.createIndex();
		long endTime=System.currentTimeMillis();   //获取结束时间
		logger.debug("执行索引结束。。。{},{}",endTime,(endTime-startTime));
		
	}
}
