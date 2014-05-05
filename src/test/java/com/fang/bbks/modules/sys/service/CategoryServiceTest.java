package com.fang.bbks.modules.sys.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.fang.bbks.modules.sys.dao.CategoryDao;
import com.fang.bbks.modules.sys.entity.Category;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-4
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class CategoryServiceTest {

	//@Autowired
	CategoryService cService;
	@Autowired
	CategoryDao categoryDao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
		cService = (CategoryService) ctx.getBean("categoryService");
	}
	
	@Test
	public void testFind1(){
		Category c = cService.findOne(new Long(4));
		Assert.assertNotNull(c);
		System.out.println(c.getName());
	}
	
	@Test
	public void testFind(){
//		List<Category> all = cService.getCaList();
		Iterator<Category> iterator = categoryDao.findAll().iterator();
		List<Category> all = Lists.newArrayList();
		while(iterator.hasNext()){
			all.add(iterator.next());
		}
		System.out.println(all.size());
		Assert.assertNotNull(all);
		Assert.assertTrue(all.size() > 0);
	}
}
