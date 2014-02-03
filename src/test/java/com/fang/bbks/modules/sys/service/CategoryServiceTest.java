package com.fang.bbks.modules.sys.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
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

import com.fang.bbks.modules.sys.entity.Category;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-4
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
//@Transactional
public class CategoryServiceTest {

//	@Autowired
	CategoryService cService;

	// ==============准备分类
	Category ca = new Category();

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
		cService = (CategoryService) ctx.getBean("categoryService");
	}

	@Test
	public void testSave() {
		Category cat = cService.save(ca);
		Assert.assertEquals(cat.getChildList(), ca.getChildList());
		//查找
		Category c = cService.findOne(cService.save(ca).getId());
		Assert.assertNotNull(c);
		Assert.assertEquals(c.getName(), ca.getName());
	}

	/**@Test
	public void testFindOne() {
		//查找
		Category c = cService.findOne(cService.save(ca).getId());
		Assert.assertNotNull(c);
		Assert.assertEquals(c.getName(), ca.getName());
	}*/

//	@Test
//	public void testDelete() {
////		cService.delete(cService.save(ca).getId());
////		Assert.assertTrue(true);
//	}
	
	
	private void init(){
		ca.setName("aa");
		ca.setIntro("intro here!");

		Category ca1 = new Category();
		ca1.setName("aa22");
		ca1.setIntro("intro here!");

		Category ca2 = new Category();
		ca2.setName("aa33");
		ca2.setIntro("intro here!");

		List<Category> cas = new ArrayList<Category>();
		cas.add(ca1);
		cas.add(ca2);
		//设置子节点分类
		ca.setChildList(cas);
	}
}
