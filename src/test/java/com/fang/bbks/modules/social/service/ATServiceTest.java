package com.fang.bbks.modules.social.service;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.entity.AT;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-25
 * @since 下午6:13:38
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class ATServiceTest {

	@Autowired
	ATService atService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		Integer uid = 22212131;
		atService.atSomeTh("HELLO", "OTHER content",uid);
		atService.atSomeOne("HELLO", "USER content",uid);
		atService.atSomeBook("HELLO", "BOOK content",uid+1);
		
		
	}

	/**
	 * Test method for
	 * {@link com.fang.bbks.modules.social.service.ATService#atSomeOne(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAtSomeOne() {
		boolean a = atService.atSomeOne("HELLO", "USER content",231);
		assertTrue(a);
	}

	/**
	 * Test method for
	 * {@link com.fang.bbks.modules.social.service.ATService#atSomeBook(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAtSomeBook() {
		boolean a = atService.atSomeBook("HELLO", "BOOK content",312321);
		assertTrue(a);
	}

	/**
	 * Test method for
	 * {@link com.fang.bbks.modules.social.service.ATService#atSomeTh(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAtSomeTh() {
		boolean a = atService.atSomeTh("HELLO", "OTHER content",312);
		assertTrue(a);
	}

	/**
	 * Test method for
	 * {@link com.fang.bbks.modules.social.service.ATService#getAts(java.lang.String, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetAtsStringInteger() {
		String taget = "HELLO";
		List<AT> ATS = atService.getAts(taget, AT.AT_USER);

		assertNotNull(ATS);
		
		Iterator<AT> it = ATS.iterator(); 
		while (it.hasNext()) {
			AT a = it.next();
			System.err.println(a.getContent());
		}
	}

	/**
	 * Test method for
	 * {@link com.fang.bbks.modules.social.service.ATService#getAt(com.fang.bbks.common.persistence.Page, java.lang.String, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetAt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fang.bbks.modules.social.service.ATService#getAts(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetAtsString() {
		fail("Not yet implemented");
	}
	
}
