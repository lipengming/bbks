package com.fang.bbks.common.persistence.jdbc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Intro 基本操作类型
 * @author Lee
 * @Date 2013-8-13
 */
public interface BaseDao<T,ID extends Serializable> {
	/**
	 * 基础接口之一</br>
	 * 批量添加接口
	 * @param sql
	 * @param values
	 * @return
	 */
	public int[] batchAdd(String sql, Object[][] values);
	/**
	 * 基础接口之一</br>
	 * 批量删除记录接口
	 * @param sql
	 * @param values
	 * @return
	 */
	public int del(String sql, Object[] values);
	
	/**
	 * 基础接口之一</br>
	 * 更新接口
	 * @param sql
	 * @param values
	 * @return
	 */
	public int update(String sql, Object[] values);
	
	/**
	 * 基础接口之一</br>
	 * 查询一个long值
	 * @param sql
	 * @param values
	 * @return
	 */
	public long getLong(String sql, Object[] values);
	
	/**
	 * 基础接口之一</br>
	 * 查询一个int值
	 * @param sql
	 * @param values
	 * @return
	 */
	public int getInt(String sql, Object[] values);
	
	/**
	 * 基础接口之一</br>
	 * sql查询通用接口
	 * @param sql
	 * @param values
	 * @return
	 */
	public List<T> search(String sql, Object[] values);
	
	/**
	 * 基础接口之一</br>
	 * sql查询通用接口
	 * @param sql
	 * @param values
	 * @param e
	 * @return
	 */
	public <T> List<T> search(String sql, Object[] values,Class<T> e);
	
	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param values
	 * @param Paging
	 * @return
	 */
	public Paging<T> search(String sql, Object[] values, Paging<T> Paging);
	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param values
	 * @param Paging
	 * @return
	 */
	public <T> Paging<T> search(String sql, Object[] values, Paging<T> Paging,Class<T> e);
	
	/**
	 * 根据主键修改数据
	 * 
	 * @param t
	 * @return
	 */
	public T update(T t);
	
	/**
	 * 批量添加接口
	 * @param list
	 * @return
	 */
	public int[] batchSave(List<T> list);
	
	/**
	 * 获取一条sql执行后返回的记录数
	 * @param sql
	 * @param values
	 * @return
	 */
	public int getCount(String sql, Object[] values);
	
	/**
	 * 根据id值删除多条数据
	 * @param ids
	 * @return
	 */
	public int delByIds(List<ID> ids);
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 * @return
	 */
	public int del(ID id);

	/**
	 * 根据id值查询多条数据
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(List<ID> ids);
	/**
	 * 根据id值查询
	 * @param id
	 * @return
	 */
	public T get(ID id);
	/**
	 * 获取本表所有记录
	 * @return
	 */
	public List<T> getAll();
	/**
	 * 获取主键列名
	 * @return
	 */
	public String getPk();
	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName();
	/**
	 * 添加记录,并返回新增记录的主键<br/>
	 * 注意:<br/>
	 * 当主键生成策略为IDENTITY（数据库自增）时，SQL执行成功返回新数据主键，执行不成功返回0<br/>
	 * 主键生成策略为非IDENTITY（数据库自增）时，SQL执行返回值无法确定<br/>
	 * @param sql
	 * @param values
	 * @return
	 */
	public ID addReturnId(String sql, List<Object> values);
	/**
	 * 保存一个对象
	 * @param t
	 * @return
	 */
	public T save(T t);

	/**
	 * sql查询通用接口
	 * @param t
	 * @return
	 */
	public List<T> search(T t);
	/**
	 * sql查询通用接口,忽略主键,仅返回查询结果集的第一个对象
	 * @param t
	 * @return
	 */
	public T searchOne(T t);
	/**
	 * 
	 * @param t
	 * @param Paging
	 * @return
	 */
	public Paging<T> search(T t, Paging<T> Paging);
	/**
	 * sql查询通用接口
	 * @param sql
	 * @param values
	 * @param e
	 * @return
	 */
	public List<Map<String,Object>> searchForMap(String sql, List<Object> values);
}
