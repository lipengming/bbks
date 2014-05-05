package com.fang.bbks.modules.sys.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.sys.dao.CategoryDao;
import com.fang.bbks.modules.sys.entity.Category;
import com.google.common.collect.Lists;

/**
 * @Intro Book service Component
 * @author Lee
 * @Date 2013-8-1
 */
@Service("categoryService")
@Transactional(readOnly = true)
public class CategoryService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	private static List<Category> result = Lists.newArrayList();

	/**
	 * 获取书籍信息
	 * @param id
	 * @return
	 */
	public Category findOne(Long id){
		return categoryDao.findOne(id);
	}
	
	/**
	 * delete
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		categoryDao.deleteById(id);
		//TODO 从列表中也出这本书及的信息
	}
	/**
	 * save
	 * @param category
	 * @return category entity
	 */
	@Transactional(readOnly = false)
	public Category save(Category category){
		categoryDao.clear();
		return categoryDao.save(category);
	}
	
	/**
	 * 缓存所有的Category
	 * @return
	 */
	public List<Category> getCaList(){
		if(result.isEmpty()){
			Iterator<Category> iterator = categoryDao.findAll().iterator();
			while(iterator.hasNext()){
				result.add(iterator.next());
			}
		}
		return result;
	}
}
