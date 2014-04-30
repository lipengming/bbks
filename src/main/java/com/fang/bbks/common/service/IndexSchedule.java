package com.fang.bbks.common.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fang.bbks.modules.sys.service.BookService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-4-30
 * @since 下午1:11:51	
 */
@Component("indexSchedule")
public class IndexSchedule {
	private static Logger logger = LoggerFactory.getLogger(IndexSchedule.class);

	
	@Resource
	private BookService bookService;
	
	@Scheduled(cron="0 * 10 * * ?")
	public void indexer(){
		long startTime=System.currentTimeMillis();   //获取开始时间
		logger.debug("执行索引开始。。。{}",startTime);
		System.out.println("INDEXXXXXXXXXXXXX");
		bookService.createdAndUpdateIndex();
		long endTime=System.currentTimeMillis();   //获取结束时间
		logger.debug("执行索引结束。。。{},{}",endTime,(endTime-startTime));
	}
}
