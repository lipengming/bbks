package com.fang.bbks.modules.sys.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;

import com.fang.bbks.modules.sys.entity.BookContent;
import com.fang.bbks.modules.sys.entity.Resource;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-4
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class BookContentServiceTest {

	@Autowired
	BookContentService bcs;
	
	@Autowired
	BookService bs;
	
	BookContent bookContent = new BookContent();
	
	@Before
	public void setUp() throws Exception {
		init();
	}

	private void init() {
		Set<Resource> res = new HashSet<Resource>();
		res.add(new Resource(Resource.SOURCE_TYPE_DOC,"das","das"));
		res.add(new Resource(Resource.SOURCE_TYPE_DOC,"111das","111das"));
		
//		bookContent.setBook(bs.findOne(1));
		bookContent.setContent("XBBBBBBBBBB");
		bookContent.setPageNum(32);
		bookContent.setPics(res);
	}
	
	@Test
	public void testSave() {
		bcs.save(bookContent);
	}
	
	@Test
	public void testFindOne() {
		BookContent bc = bcs.save(bookContent);
		Assert.assertEquals(bookContent.getContent(), bcs.findOne(bc.getId()).getContent());
	}

	@Test
	public void testDelete() {
		BookContent bc = bcs.save(bookContent);
		bcs.delete(bc.getId());
	}
}