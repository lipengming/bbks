package com.fang.bbks.modules.social.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.entity.Message;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-4-28
 * @since 下午4:49:00	
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class IMessageServiceTest {

	@Autowired
	IMessageService messageService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSendMessage() {
		Integer from = 100000001;//参数
		Integer to = 100000002;//参数

		messageService.sendMessage("hello", from, to);
	}

	@Test
	public void testISend() {
		Integer from = 100000001;//参数
		
		Message expected0 = new Message();
		expected0.setContent("hello");
		
		List<Message> expectedResult = new ArrayList<Message>();
		expectedResult.add(expected0);
		
		List<Message> sendList = messageService.ISend(from);//查询
		
		Assert.assertNotNull(sendList);//非空
		Assert.assertEquals(1, sendList.size());//结果条数
		Assert.assertEquals(expected0.getContent(), sendList.get(0).getContent());
		
	}

	/**
	 * Test method for {@link com.fang.bbks.modules.social.service.IMessageService#IRecived(java.lang.Integer)}.
	 */
	@Test
	public void testIRecived() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.fang.bbks.modules.social.service.IMessageService#unRead(java.lang.Integer)}.
	 */
	@Test
	public void testUnRead() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.fang.bbks.modules.social.service.IMessageService#findOne(java.lang.Integer)}.
	 */
	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.fang.bbks.modules.social.service.IMessageService#delete(java.lang.Integer)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
