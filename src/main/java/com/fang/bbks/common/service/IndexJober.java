package com.fang.bbks.common.service;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Reference;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.modules.sys.service.BookService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-4-30
 * @since 上午11:10:33	
 */
@Component("indexJober")
public class IndexJober extends QuartzJobBean{
	private static Logger logger = LoggerFactory.getLogger(IndexJober.class);
	
	
	private int timeout;
//	@Resource
//	private BookService bookService;
//	
//	
	//调度工厂实例化后，经过timeout时间开始执行调度  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		long startTime=System.currentTimeMillis();   //获取开始时间
		logger.debug("执行索引开始。。。{}",startTime);
		System.out.println("INDEXXXXXXXXXXXXX");
//		bookService.createdAndUpdateIndex();
		long endTime=System.currentTimeMillis();   //获取结束时间
		logger.debug("执行索引结束。。。{},{}",endTime,(endTime-startTime));
	}

}
