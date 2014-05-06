package com.fang.bbks.modules.sys.service;

import java.util.Date;

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
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.common.utils.StringUtils;
import com.fang.bbks.modules.sys.dao.BookDao;
import com.fang.bbks.modules.sys.dao.CategoryDao;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.BookContent;
import com.fang.bbks.modules.sys.entity.Category;

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
	
	@Autowired
	CategoryDao categoryDao;
	
	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Book findOne(Long id){
		return bookDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Book save(Book book){
		book.setUpdateAt(new Date());
		bookDao.clear();
		return bookDao.save(book);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		bookDao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
	
	/**
	 * 全文检索
	 * @param page
	 * @param keywords
	 * @return
	 */
	public Page<Book> findByKeyWords(Page<Book> page,String keywords,String sortBy,Integer orders){
		
		System.out.println(sortBy+"--"+orders);

		
		// 设置查询条件
		BooleanQuery query = bookDao.getFullTextQuery(keywords, "bookName","outline","author");
		// 设置过滤条件
		BooleanQuery queryFilter = bookDao.getFullTextQuery(
				new BooleanClause(new TermQuery(new Term("delFlag", Book.DEL_FLAG_NORMAL+"")), Occur.MUST));
		//设置排序
		Sort sort = new Sort(new SortField("updateTime", SortField.DOC, true));
		if(StringUtils.isNotEmpty(sortBy)){
			if( "comment".equals(sortBy)){
				if(orders == 0){
					sort.setSort(new SortField("commentCount", SortField.DOC,true));
				}else{
					sort.setSort(new SortField("commentCount", SortField.DOC,false));
				}
			}else if("price".equals(sortBy)){
				if(orders == 0){
					sort.setSort(new SortField("pubPrice", SortField.DOC,true));
				}else{
					sort.setSort(new SortField("pubPrice", SortField.DOC,false));
				}
			}
		}
		
		// 全文检索
		//bookDao.search(page, query, queryFilter, sort);
		bookDao.search(page, query, null, sort);
		// 关键字高亮
		//bookDao.keywordsHighlight(query, page.getList(), "outline");
		
		return page;		
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
		Sort sort = new Sort(new SortField("updateTime", SortField.DOC, true));
		// 全文检索
		//bookDao.search(page, query, queryFilter, sort);
		bookDao.search(page, query, null, null);
		// 关键字高亮
		//bookDao.keywordsHighlight(query, page.getList(), "outline");
		
		return page;		
	}
	
	/**
	 * 分页分类查询图书
	 * @param page
	 * @param catlogId
	 * @return
	 */
	public Page<Book> findBook(Page<Book> page,Long catlogId){
		Book b = new Book();
		if(catlogId != null && catlogId > 0){
			Category catlog = categoryDao.findOne(catlogId);
			if(catlog == null){
				return null;
			}
			b.setCategory(catlog);
		}
		return findBook(page, b);
		
		
	}
	
	public Page<Book> findBook(Page<Book> page,Book book,String sortBy,int orders){
		DetachedCriteria dc = bookDao.createDetachedCriteria();
		//设置查询条件
		addCondition(dc,book);
		
		System.out.println(sortBy+"--"+orders);
		
		if(StringUtils.isNotEmpty(sortBy)){
			if( "comment".equals(sortBy)){
				if(orders == 0){
					dc.addOrder(Order.desc("commentCount"));
				}else{
					dc.addOrder(Order.asc("commentCount"));
				}
				
			}else if("price".equals(sortBy)){
				if(orders == 0){
					dc.addOrder(Order.desc("pubPrice"));
				}else{
					dc.addOrder(Order.asc("pubPrice"));
				}
				
			}
		}
		return bookDao.find(page, dc);

	}
	
	
	/**
	 * 找书
	 */
	public Page<Book> findBook(Page<Book> page,Book book){
		DetachedCriteria dc = bookDao.createDetachedCriteria();
		//设置查询条件
		addCondition(dc,book);
		return bookDao.find(page, dc);
	}
	
	
	//////////////////////////
	//////////////////////////
	//////////////////////////
	
	/**
	 * 添加查询条件
	 * @param dc
	 * @param book
	 */
	private void addCondition(DetachedCriteria dc,Book book){
		if(book == null) return;
		
		dc.createAlias("category", "category");
		
		if(book.isFree()){
			//查询免费书籍
			dc.add(Restrictions.eq("isFreee", book.isFree()));
		}
		//分类
		if(book.getCategory() != null && book.getCategory().getId() != null){
			if (book.getCategory()!=null){
				dc.add(Restrictions.eq("category.id", book.getCategory().getId()));
			}
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
		System.out.println("执行索引开始。。。"+startTime);
		bookDao.createIndex();
		long endTime=System.currentTimeMillis();   //获取结束时间
		System.out.println("执行索引结束。。。"+endTime+"---"+(endTime-startTime));
		
	}
}
