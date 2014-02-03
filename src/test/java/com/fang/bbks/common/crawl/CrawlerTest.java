package com.fang.bbks.common.crawl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
public class CrawlerTest {
	
	@Autowired
	Crawler crawler;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcess() {
		try {
			crawler.process();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
