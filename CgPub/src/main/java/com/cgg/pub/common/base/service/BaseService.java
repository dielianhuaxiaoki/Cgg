package com.cgg.pub.common.base.service;

import java.util.List;

import com.cgg.pub.common.base.dao.BaseDao;
import com.cgg.pub.common.base.page.Criteria;
import com.cgg.pub.common.base.page.Pager;


public interface BaseService<T, PK> {
	
	
	public List<T> executeSql(Criteria criteria);
	
	public BaseDao<T, PK> getDao();

	public T getOneBy(String field, Object value);

	public T get(PK id, boolean isForUpdate);

	public T get(PK id);
	
	public List<T> get(Criteria criteria);
	
	public T getOneBy(Criteria criteria);

	public List<T> get(List<PK> ids);

	public List<T> get(List<PK> ids, boolean isForUpdate);

	public List<T> getAll();

	public int getTotalCount();

	public int getTotalCountBy(Criteria criteria);

	public int insert(T entity);

	public int insert(List<T> entitys);

	public int updateBy(T entity, Criteria criteria);

	public int update(T entity);

	public int update(List<T> entitys);

	public int delete(List<PK> ids);

	public int deleteBy(Criteria criteria);

	public int delete(PK id);

	public Pager getByPager(Pager pager);

	public T getOneByPager(Pager pager);

	public double getSumBy(Criteria criteria);

	public Pager getBy(Pager pager, String field, Object value);
	
	public Pager<T> getByCustomedPager(Pager<T> pager, String findSqlMapperName, String countSqlMapperName);
}
