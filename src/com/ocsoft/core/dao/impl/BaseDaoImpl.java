package com.ocsoft.core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.Module;

public class BaseDaoImpl<T1, T2> implements IBaseDao<T1, T2> {
	
	protected String HQL_LIST_ALL;
	
	public BaseDaoImpl() {
		ParameterizedType entityClassType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.entityClass = (Class<T1>) entityClassType.getActualTypeArguments()[0];
		
		HQL_LIST_ALL = "from " + this.entityClass.getSimpleName();
	}
	
	protected Class<T1> entityClass = null;
	
	@Override
	public void flush() {
		getSession().flush();
	}
	
	@Resource
	private SessionFactory sessionFactory = null;
	
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public T1 get(T2 id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(T1 m) {
		// TODO Auto-generated method stub
		getSession().save(m);
	}

	@Override
	public List<Module> getByCriteria(List<Criterion> cl) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
