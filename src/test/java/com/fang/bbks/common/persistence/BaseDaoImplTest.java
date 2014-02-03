package com.fang.bbks.common.persistence;

import static org.junit.Assert.*;

import javax.persistence.Basic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.service.BookService;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class BaseDaoImplTest {
	
	@Autowired
	BookService bs;
	
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testFindPageOfTStringObjectArray() {
		Book b = new Book();
		b.setBookName("c");
		Page<Book> pages = bs.findBook(new Page<Book>(0, 5),b);
		System.out.println(pages.getCount());
		System.out.println("size："+pages.getList().size()+"first:"+pages.getFirstResult()+"max:"+pages.getMaxResults());
	}

}
