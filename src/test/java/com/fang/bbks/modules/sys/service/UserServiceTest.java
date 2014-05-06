package com.fang.bbks.modules.sys.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.sys.dao.UserDao;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class UserServiceTest {
	
	@Autowired
	UserService us;
	
	@Autowired
	UserDao ud;
	
	User user;
	
	@Before
	public void setUp() throws Exception {
		init();
	}

	private void init() {
		user = new User();
		user.setEmail("sho@g.com");
		user.setCreateDate(new Date());
		user.setHasRead(1);
		user.setUsername("aaaaaaaaaa");
		user.setIsCompany(User.YES);
	}

	@Test
	public void testSave(){
		
	}
	
	@Test
	public void testFindOne() {
		assertFalse(us.isExit("aaa"));
	}

	@Test
	public void testDelete() {
		assertNotNull(ud.findByUserName("aaa"));
	}

	@Test
	public void testGetByUserName() {
		fail("Not yet implemented");
	}
	
}
