package com.cgg.pub.common.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cgg.pub.common.base.dao.BaseDao;
import com.cgg.pub.common.base.page.Criteria;
import com.cgg.pub.common.base.page.Pager;
import com.cgg.pub.common.base.service.BaseService;
import com.cgg.pub.common.util.SpringBeanUtil;


@SuppressWarnings("unchecked")
public class BaseServiceImpl<T, PK, S extends BaseDao<T, PK>> implements BaseService<T, PK> {

	Log log = LogFactory.getLog(BaseServiceImpl.class);

	private S dao;

	@SuppressWarnings("rawtypes")
	@Override
	public S getDao() {
		if (dao != null) {
			return dao;
		} else {
			Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
			dao = (S) SpringBeanUtil.getBean((Class)types[2]);
			return dao;
		}
	}

	@Override
	public T get(PK id) {
		return getDao().get(id, false);
	}

	@Override
	public T get(PK id, boolean isForUpdate) {
		return getDao().get(id, isForUpdate);
	}

	@Override
	public List<T> get(Criteria criteria) {
		return getDao().get(criteria);
	}

	@Override
	public List<T> get(List<PK> ids) {
		return getDao().get(ids, false);
	}

	@Override
	public List<T> get(List<PK> ids, boolean isForUpdate) {
		return getDao().get(ids, isForUpdate);
	}

	@Override
	public List<T> getAll() {
		return getDao().getAll();
	}

	@Override
	public int getTotalCount() {
		return getDao().getTotalCount();
	}

	@Override
	public int getTotalCountBy(Criteria criteria) {
		return getDao().getTotalCountBy(criteria);
	}

	@Override
	public int insert(T entity) {
		return getDao().insert(entity);
	}

	@Override
	public int insert(List<T> entitys) {
		return getDao().insert(entitys);
	}

	@Override
	public int update(List<T> entitys) {
		return getDao().update(entitys);
	}

	@Override
	public int updateBy(T entity, Criteria criteria) {
		return getDao().updateBy(entity, criteria);
	}

	@Override
	public int update(T entity) {
		return getDao().update(entity);
	}

	@Override
	public int delete(List<PK> ids) {
		return getDao().delete(ids);
	}

	@Override
	public int deleteBy(Criteria criteria) {
		return getDao().deleteBy(criteria);
	}

	@Override
	public int delete(PK id) {
		return getDao().delete(id);
	}

	@Override
	public Pager getByPager(Pager pager) {
		return getDao().getByPager(pager);
	}

	@Override
	public T getOneByPager(Pager pager) {
		return (T) getDao().getOneByPager(pager);
	}

	@Override
	public double getSumBy(Criteria criteria) {
		return getDao().getSumBy(criteria);
	}

	@Override
	public Pager getBy(Pager pager, String field, Object value) {
		pager.getCriteria().addWhere(field + " = %1$s", new Object[] { value });
		return getByPager(pager);
	}

	@Override
	public T getOneBy(String field, Object value) {
		Pager pager = new Pager();
		pager.getCriteria().addWhere(field + " = %1$s", new Object[] { value });
		return getOneByPager(pager);
	}

	public Pager<T> getByCustomedPager(Pager<T> pager, String findSqlMapperName, String countSqlMapperName) {
		return this.getDao().getByCustomedPager(pager, findSqlMapperName, countSqlMapperName);
	}

	@Override
	public T getOneBy(Criteria criteria) {
		// TODO Auto-generated method stub
		return this.getDao().getOneBy(criteria);
	}

	@Override
	public List<T> executeSql(Criteria criteria) {

		return this.getDao().executeSql(criteria);
	}

}
