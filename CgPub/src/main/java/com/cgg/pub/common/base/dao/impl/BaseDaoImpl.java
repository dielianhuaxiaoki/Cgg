package com.cgg.pub.common.base.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.cgg.pub.common.base.dao.BaseDao;
import com.cgg.pub.common.base.page.Criteria;
import com.cgg.pub.common.base.page.Pager;


@Repository
public class BaseDaoImpl<T, PK> implements BaseDao<T, PK> {

	protected String daoMappedId;

	@Resource(name="sqlSessionTemplate")
	protected SqlSessionTemplate sqlSessionTemplate;

	@Override
	public T get(PK id) {
		return get(id, false);
	}

	@Override
	public T get(PK id, boolean isForUpdate) {
		Assert.notNull(id, "id is required");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key_value", id);
		map.put("for_update", isForUpdate ? " for update " : "");
		return sqlSessionTemplate.selectOne(getMapperFullName(GetById), map);
	}

	@Override
	public List<T> get(Criteria criteria) {
		Map<String, Object> map = getCriteriaMap(criteria);
		return sqlSessionTemplate.selectList(getMapperFullName(GetByPager), map);
	}

	@Override
	public List<T> get(List<PK> ids) {
		return get(ids, false);
	}

	@Override
	public List<T> get(List<PK> ids, boolean isForUpdate) {
		Assert.notNull(ids, "id is required");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ids);
		map.put("for_update", isForUpdate ? " for update " : "");
		return sqlSessionTemplate.selectList(getMapperFullName(GetByIds), map);
	}

	@Override
	public List<T> getAll() {
		Map<String, Object> map = getPageMap(new Pager());
		return sqlSessionTemplate.selectList(getMapperFullName(GetByPager), map);
	}

	@Override
	public int getTotalCount() {
		Map<String, Object> map = getPageMap(new Pager());
		Integer answer = (Integer) sqlSessionTemplate.selectOne(getMapperFullName(GetTotalCountBy), map);
		if (answer == null)
			return 0;
		return answer;
	}

	@Override
	public int getTotalCountBy(Criteria criteria) {
		SqlSession session = sqlSessionTemplate;
		Map<String, Object> map = getCriteriaMap(criteria);
		Integer answer = (Integer) session.selectOne(getMapperFullName(GetTotalCountBy), map);
		if (answer == null)
			return 0;
		return answer;
	}

	@Override
	public int insert(T entity) {
		Assert.notNull(entity, "entity is required");
		return (Integer) sqlSessionTemplate.insert(getMapperFullName(INSERT), entity);
	}

	@Override
	public int insert(List<T> entitys) {
		Assert.notNull(entitys, "entity is required");
		int answer = 0;
		for (T each : entitys) {
			answer += sqlSessionTemplate.insert(getMapperFullName(INSERT), each);
		}
		return answer;
	}

	@Override
	public int update(T entity) {
		Assert.notNull(entity, "entity is required");
		return (Integer) sqlSessionTemplate.update(getMapperFullName(UPDATE), entity);
	}

	@Override
	public int update(List<T> entitys) {
		Assert.notNull(entitys, "entity is required");
		int answer = 0;
		for (T each : entitys) {
			answer += sqlSessionTemplate.update(getMapperFullName(UPDATE), each);
		}
		return answer;
	}

	@Override
	public int updateBy(T entity, Criteria criteria) {
		Assert.notNull(criteria, "entity is required");
		Map<String, Object> criteriaMap = getEntityCriteriaMap(entity, criteria);
		return sqlSessionTemplate.update(getMapperFullName(UPDATEBY), criteriaMap);
	}

	@Override
	public int delete(List<PK> ids) {
		Assert.notNull(ids, "ids is required");
		return (Integer) sqlSessionTemplate.delete(getMapperFullName(DeleteByIds), ids);
	}

	@Override
	public int delete(PK id) {
		return (Integer) sqlSessionTemplate.delete(getMapperFullName(DELETE), id);
	}

	@Override
	public int deleteBy(Criteria criteria) {
		Assert.notNull(criteria, "criteria is required");
		Map<String, Object> criteriaMap = getCriteriaMap(criteria);
		return (Integer) sqlSessionTemplate.delete(getMapperFullName(DeleteBy), criteriaMap);
	}

	@Override
	public double getSumBy(Criteria criteria) {
		SqlSession session = sqlSessionTemplate;
		Map<String, Object> map = getCriteriaMap(criteria);
		Double answer = (Double) session.selectOne(getMapperFullName(GetSumBy), map);
		if (answer == null)
			return 0.0;
		return answer;
	}

	@Override
	public Pager<T> getByPager(Pager<T> pager) {
		Assert.notNull(pager, "page is required");
		Map<String, Object> map = getPageMap(pager);
		int rowCount = (Integer) sqlSessionTemplate.selectOne(getMapperFullName(GetTotalCountBy), map);
		pager.setTotalCount(rowCount);
		map = getPageMap(pager);
		List<T> result = sqlSessionTemplate.selectList(getMapperFullName(GetByPager), map);
		pager.setList(result);
		return pager;
	}

	@Override
	public T getOneByPager(Pager<T> pager) {
		Assert.notNull(pager, "page is required");
		Map<String, Object> map = getPageMap(pager);
		List<T> result = sqlSessionTemplate.selectList(getMapperFullName(GetByPager), map);
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Pager<T> getByCustomedPager(Pager<T> pager, String findSqlMapperName, String countSqlMapperName) {
		Map<String, Object> map = getPageMap(pager);
		int rowCount = (Integer) sqlSessionTemplate.selectOne(getMapperFullName(countSqlMapperName), map);
		pager.setTotalCount(rowCount);
		map = getPageMap(pager);
		List<T> result = sqlSessionTemplate.selectList(getMapperFullName(findSqlMapperName), map);
		pager.setList(result);
		return pager;
	}

	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		Class[] interClassArray = getClass().getInterfaces();
		if (interClassArray != null && interClassArray.length > 0) {
			daoMappedId = interClassArray[0].getName();
			if (daoMappedId.equals(BaseDao.class.getName())) {
				daoMappedId = getClass().getName();
			}
		}
	}

	protected Map<String, Object> getPageMap(Pager pager) {
		Map<String, Object> map = getCriteriaMap(pager.getCriteria());
		map.put("limit", pager.getLimit());
		return map;
	}

	protected Map<String, Object> getEntityCriteriaMap(Object obj, Criteria criteria) {
		Map<String, Object> map = new HashMap<String, Object>();
		updateCriteriaMap(criteria, map);
		map.put("entity", obj);
		return map;
	}

	protected Map<String, Object> getCriteriaMap(Criteria criteria) {
		Map<String, Object> map = new HashMap<String, Object>();
		updateCriteriaMap(criteria, map);
		return map;
	}

	protected Map<String, Object> updateCriteriaMap(Criteria criteria, Map<String, Object> map) {
		if (criteria == null) {
			criteria = new Criteria();
		}
		map.putAll(criteria.getAnswer());
		return map;
	}

	public String getMapperFullName(String mapperId) {
		return new StringBuilder(daoMappedId).append(".").append(mapperId).toString();
	}

	@Override
	public T getOneBy(Criteria criteria) {
		Map<String, Object> map = getCriteriaMap(criteria);
		List<T> result = sqlSessionTemplate.selectList(getMapperFullName(GetByPager), map);
		if (result != null && result.size() > 0)
			return result.get(0);
		return null;
		// SqlSession session = sqlSessionTemplate;
		// Map<String, Object> map = getCriteriaMap(criteria);
		//
		// List<T> result = session.selectList(getMapperFullName(GetOneBy),
		// map);
		// if(result!=null&&result.size()>0)
		// return result.get(0);
		// return null;
	}

	public static final String GetById = "getById";
	public static final String GetByIds = "getByIds";
	public static final String GetByPager = "getByPager";
	public static final String GetTotalCountBy = "getTotalCountBy";
	public static final String INSERT = "insert";
	public static final String UPDATE = "update";
	public static final String UPDATEBY = "updateBy";
	public static final String DELETE = "delete";
	public static final String DeleteByIds = "deleteByIds";
	public static final String DeleteBy = "deleteBy";
	public static final String GetSumBy = "getSumBy";
	public static final String GetOneBy = "getOneBy";
	public static final String ExecuteSql = "executeSql";
	public static final String GetExecuteSqlTotalCount = "getExecuteSqlTotalCount";
	public static final String GetMergeByPager = "getMergeByPager";

	@Override
	public List<T> executeSql(Criteria criteria) {

		Map<String, Object> map = getCriteriaMap(criteria);

		return sqlSessionTemplate.selectList(getMapperFullName(ExecuteSql), map);
	}

	@Override
	public int getTotalCountByexecuteSql(Criteria criteria) {

		Map<String, Object> map = getCriteriaMap(criteria);

		return sqlSessionTemplate.selectOne(getMapperFullName(GetExecuteSqlTotalCount), map);
	}

	// @Override
	// public Object executeSql(Criteria criteria) {
	//
	// Map<String, Object> map = getCriteriaMap(criteria);
	//
	// return sqlSessionTemplate.selectList(getMapperFullName(ExecuteSql), map);
	// }

	@Override
	public Pager<T> executeSql(Pager<T> pager) {
		Assert.notNull(pager, "page is required");
		Map<String, Object> map = getPageMap(pager);
		int rowCount = (Integer) sqlSessionTemplate.selectOne(getMapperFullName(GetExecuteSqlTotalCount), map);
		pager.setTotalCount(rowCount);
		map = getPageMap(pager);
		List<T> result = sqlSessionTemplate.selectList(getMapperFullName(ExecuteSql), map);
		pager.setList(result);
		return pager;
	}

	@Override
	public Pager<T> getMergeByPager(Pager<T> pager) {
		Assert.notNull(pager, "page is required");
		Map<String, Object> map = getPageMap(pager);
		// int rowCount = (Integer)
		// sqlSessionTemplate.selectOne(getMapperFullName(GetExecuteSqlTotalCount),
		// map);
		// pager.setTotalCount(rowCount);
		map = getPageMap(pager);
		List<T> result = sqlSessionTemplate.selectList(getMapperFullName(GetMergeByPager), map);
		pager.setList(result);
		return pager;
	}

}
