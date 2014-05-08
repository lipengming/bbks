package com.fang.bbks.modules.sys.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fang.bbks.modules.sys.dao.CategoryDao;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.service.BookService;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-4-30
 * @since 下午3:31:08	
 */
@Component 
public class CategoryMapper{
	
	@Autowired
	static CategoryService categoryService;
	
	
}
