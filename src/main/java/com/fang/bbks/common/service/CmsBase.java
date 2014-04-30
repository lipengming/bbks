package com.fang.bbks.common.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fang.bbks.modules.sys.service.BookService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-4-30
 * @since 下午3:31:08	
 */

//@Component
public class CmsBase implements ApplicationContextAware{
	
	private static BookService bs;
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		bs = (BookService) applicationContext.getBean("bookService");
	}

}
