package com.cgg.pub.common.base.dao;

import java.util.List;

import com.cgg.pub.common.base.page.Criteria;
import com.cgg.pub.common.base.page.Pager;


public interface BaseDao<T, PK> {

	public T get(PK id);

	public List<T> get(List<PK> ids);

	public List<T> get(Criteria criteria);
	
	public T getOneBy(Criteria criteria);
	
	public T get(PK id, boolean isForUpdate);

	public List<T> get(List<PK> ids, boolean isForUpdate);
	
	public List<T> getAll();
	
	public int getTotalCount();
	
	public int getTotalCountBy(Criteria criteria);
	
	public int insert(T entity);

	public int insert(List<T> entitys);

	public int update(T entity);

	public int updateBy(T entity, Criteria criteria); 
	
	public int update(List<T> entitys);
	
	public int delete(List<PK> ids);
	
	public int deleteBy(Criteria criteria);
	
	public int delete(PK id); 
	
	public Pager<T> getByPager(Pager<T> pager);
	
	public T getOneByPager(Pager<T> pager);

	public double getSumBy(Criteria criteria);

	public Pager<T> getByCustomedPager(Pager<T> pager, String findSqlMapperName, String countSqlMapperName);
	
	public List<T> executeSql(Criteria criteria);
	
	public Pager<T> executeSql(Pager<T> pager);
	
	public Pager<T> getMergeByPager(Pager<T> pager);

	int getTotalCountByexecuteSql(Criteria criteria);
	
}
