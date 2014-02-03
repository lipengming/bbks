package com.fang.bbks.common.persistence.jdbc;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;

/**
 * @Intro mysql实现
 * @author Lee
 * @Date 2013-8-13
 */
public abstract class BaseDaoMySqlImpl<T, ID extends Serializable> extends JdbcDaoSupport implements BaseDao<T, ID>{
	
	public final Log log = LogFactory.getLog(this.getClass());
	private Class<T> persistentClass;
	private String tableName = "";
	private String pk = "";
	private GenerationType strategy;
	protected List<String> transientPropertys=new ArrayList<String>();
	
	protected BaseDaoMySqlImpl(Class<T> persistentClass){
		this.persistentClass=persistentClass;
		Table table = AnnotationUtils.findAnnotation(persistentClass, Table.class);
		if(table == null){
			throw new RuntimeException(persistentClass + "没有定义的@table");
		}
		this.tableName = table.name();
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(persistentClass);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds){
			Id id =  AnnotationUtils.findAnnotation(pd.getReadMethod(), Id.class);
			if(pk.equals("") && id != null){
				pk = pd.getName();
				GeneratedValue gv = AnnotationUtils.findAnnotation(pd.getReadMethod(), GeneratedValue.class);
				if(gv == null){
					strategy = GenerationType.IDENTITY;
				}else{
					strategy = gv.strategy();
				}
			}
			Transient transient_ = AnnotationUtils.findAnnotation(pd.getReadMethod(), Transient.class);
			if(transient_ == null){
				transientPropertys.add(pd.getName());
			}
		}
		if("".equals(this.getPk())){
			throw new RuntimeException(persistentClass+"类型没有在get方法上定义@Id");
		}
	}
	
	protected BaseDaoMySqlImpl(){}
	
	@Resource(name="jdbcTemplate")
	public void setJDBC(JdbcTemplate jdbc){
		super.setJdbcTemplate(jdbc);
	}
	
	@Override
	public String getPk() {
		return pk;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public List<T> getAll() {
		StringBuilder sb=new StringBuilder("select * from ");
		sb.append(this.getTableName());
		return this.search(sb.toString(), null);
	}

	
	@Override
	public ID addReturnId(String sql, List<Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] batchAdd(String sql, Object[][] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int del(String sql, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String sql, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String sql, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String sql, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> search(String sql, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> search(String sql,Object[] values, Class<T> e) {
		if(StringUtils.isEmpty(sql))
			return new ArrayList<T>();
		if(values==null || values.length < 1){
			values = new Object[]{};
		}
		if(log.isDebugEnabled()){
			log.debug("sql : "+sql+" values:"+ values );
		}
		@SuppressWarnings("unchecked")
		List<T> list = this.getJdbcTemplate().query(sql, values,new BeanPropertyRowMapper<T>((Class<T>) this.persistentClass));
		return list == null ? new ArrayList<T>() : list;
	}
	
	@Override
	public List<T> search(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T searchOne(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paging<T> search(T t, Paging<T> Paging) {
		Map<String, Object> map;
		try {
			map = BeanUtils.getInstance().describe(t);
			for(String property : transientPropertys){
				map.remove(property);
			}
		} catch (Exception e) {
			throw new DaoException("模型解析失败！",e);
		}
		return this.search(map, Paging);
	}

	@Override
	public List<Map<String, Object>> searchForMap(String sql,
			List<Object> values) {
		return null;
	}

	@Override
	public Paging<T> search(String sql, Object[] values, Paging<T> Paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Paging<T> search(String sql, Object[] values, Paging<T> Paging,
			Class<T> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] batchSave(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(String sql, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delByIds(List<ID> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(ID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> getByIds(List<ID> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(ID id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected Paging<T> search(Map<String, Object> map, Paging<T> pageBean) {
		ID id=(ID) map.get(this.getPk());
		if(map!=null&&id!=null){
			map.remove(id);
		}
		List<String> removekeys=new ArrayList<String>();
		for(Entry<String,Object> entry:map.entrySet()){
			if(entry.getValue()==null){
				removekeys.add(entry.getKey());
			}
		}
		for(String key:removekeys){
			map.remove(key);
		}
		Object[] values = new Object[map.size()];
		int i = 0;
		StringBuilder sb=new StringBuilder("select * from ");
		sb.append(this.getTableName());
		if(map.size()!=0){
			sb.append(" where  ");
			for(Entry<String,Object> entry:map.entrySet()){
				sb.append(entry.getKey());
				sb.append("=? ");
				values[i++] = entry.getValue();
				sb.append(" and ");
			}
		}
		this.deleteLastStr(sb, "and");
		this.search(sb.toString(), values, pageBean);
		return pageBean;
	}
	private  void deleteLastStr(StringBuilder sb,String str){
		int index = sb.lastIndexOf(str);
		if(index!=-1){
			sb.delete(index, index+str.length());
		}
	}
}
