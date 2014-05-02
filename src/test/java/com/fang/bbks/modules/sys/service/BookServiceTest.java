package com.fang.bbks.modules.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.apache.noggit.JSONUtil;
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
import com.fang.bbks.modules.sys.entity.Comment;
import com.fang.bbks.modules.sys.entity.CommentType;
import com.fang.bbks.modules.sys.entity.Resource;
import com.fang.bbks.modules.sys.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

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
	
	@Autowired
	CategoryService cs;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	
	@Before
	public void setUp() throws Exception {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
//		bs = (BookService) ctx.getBean("bookService");
	
//		init();
	}
	
	@Test
	public void testPage(){
		Book b = new Book();
		b.setBookName("谁的青春不迷茫");
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
	@Test
	public void testSave() {
		
		User user = new User();
		user = userService.signUp("name", "name@name.com", "name");
		
		
		Category c1 = new Category("小说","小说");
		Category c2 = new Category("军事","散文");
		Category c3 = new Category("散文","散文");
		
		c1 = cs.save(c1);
		c2 = cs.save(c2);
		c3 = cs.save(c3);
		
		for(int a = 0 ;a<100;a++){
			Book book = new Book("小说"+a, "2000001"+a, "translator:"+a, "author:"+a);
			book.setCoverPic("http--");
			
			book.setContents(get(book));
			
			if(a % 5 == 0){
				book.setCategory(c1);
			}else if(a % 3 == 0){
				book.setCategory(c2);
			}else{
				book.setCategory(c3);
			}
			book = bs.save(book);
			
			for(int j=0;j<3;j++){
				Comment c = new Comment();
				c.setContentId(book.getId());
				c.setTitle(book.getBookName());
				c.setContent("content:::"+j);
				commentService.addComment(CommentType.BOOK, c, user);
			}	
		}
	}
	
	
	@Test
	public void testFindBook(){
		Page<Book> page = new Page<Book>(1, 20);
		Category c = cs.findOne(new Long(15));
		Book b = new Book(c);
		Page<Book> pages = bs.findBook(page, b);
		
		System.out.println(pages.getCount() + "----"+pages.getList());
		System.out.println(JSONUtil.toJSON(pages.getList()));
	}
	
	private List<BookContent> get(Book book){
		List<BookContent> bcs = Lists.newArrayList();
		for(int i =0;i<10;i++){
			BookContent bc = new BookContent(i, "content:::"+i);
			bc.setBook(book);
			bcs.add(bc);
		}
		return bcs;
	}
	
	private List<Comment> comments(Book book,User user){
		List<Comment> list = Lists.newArrayList();
		for(int a=0;a<5;a++){
			Comment comment  = new Comment();
			comment.setContent("content "+a);
//			comment.setAvatar(user.getAvatar());
//			comment.setName(user.getUsername());
//			comment.set
			list.add(comment);
		}
		return list;
	}
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
