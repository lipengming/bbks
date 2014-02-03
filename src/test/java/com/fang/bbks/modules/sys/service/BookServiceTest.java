package com.fang.bbks.modules.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.common.utils.mapper.JsonMapper;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.BookContent;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.entity.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class BookServiceTest {

	@Autowired
	BookService bs;
	
	
	Book book;
	Set<Resource> resources = new HashSet<Resource>();
	Category ca;
	BookContent bc;
	
	@Before
	public void setUp() throws Exception {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
//		bs = (BookService) ctx.getBean("bookService");
	
//		init();
	}
	
	@Test
	public void testPage(){
		Book b = new Book();
//		b.setBookName("谁的青春不迷茫");
		Page<Book> page = bs.findBook(new Page<Book>(0, 20), b);
		for(Book bk : page.getList()){
			System.out.println(bk.getBookName());
		}
	}

//	@Test
//	public void testFindOne() {
//		Book b = bs.findOne(1);
//		Assert.assertEquals(book.getBookName(), b.getBookName());
//	}
//
//	@Test
//	public void testSave() {
//		bs.save(book);
//	}
//
//	@Test
//	public void testDelete() {
//		Assert.assertNotNull(book);
//		//JsonMapper.getInstance().toJson(book)
//		System.out.println(book.getBookName());
//		
//		//存
//		Book bb1 = bs.findOne(bs.save(book).getId());//搜
//		
//		Assert.assertEquals(book.getBookName(), bb1.getBookName());
//		
//		bs.delete(book.getId());//删
//		Book bb2 = bs.findOne(book.getId());
//		Assert.assertEquals(bb1, bb2);
//	}
//	
//	private void init(){
//		//==============准备资源
//		//List<Resource> rss = new ArrayList<Resource>();
//		resources.add(new Resource(Resource.SOURCE_TYPE_DOC, "c://asa/a.docx", "c://asa/a.docx"));
//		resources.add(new Resource(Resource.SOURCE_TYPE_DOC, "c://asa/b.docx", "c://asa/b.docx"));
//		resources.add(new Resource(Resource.SOURCE_TYPE_DOC, "c://asa/a.docx", "c://asa/a.docx"));
//		resources.add(new Resource(Resource.SOURCE_TYPE_DOC, "c://asa/b.docx", "c://asa/b.docx"));
//		
//		
//		//==============准备分类
//		ca = new Category();
//		ca.setName("aa");
//		ca.setIntro("intro here!");
//		
//		Category ca1 = new Category();
//		ca1.setName("aa22");
//		ca1.setIntro("intro here!");
//		
//		Category ca2 = new Category();
//		ca2.setName("aa33");
//		ca2.setIntro("intro here!");
//		
//		List<Category> cas = new ArrayList<Category>();
//		cas.add(ca1);
//		cas.add(ca2);
//		
//		ca.setChildList(cas);
//		
//		
//		//==============准备书籍内容
//		Set<BookContent> bcs = new HashSet<BookContent>();
//		BookContent bc1 = new BookContent();
//		bc1.setPics(resources);
//		BookContent bc2 = new BookContent();
//		bc1.setPics(resources);
//		
//		bcs.add(bc1);
//		bcs.add(bc2);
//		
//		//准备书籍
//		book = new Book("##@@BAIDU","LEE","#23131");
//		book.setCategory(ca);
//		book.setContents(bcs);
//	}
}