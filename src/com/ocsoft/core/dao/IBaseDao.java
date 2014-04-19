package com.ocsoft.core.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.ocsoft.oa.vo.system.Module;

public interface IBaseDao<T1, T2>{
	
	public T1 get(T2 id);
	
	public void save(T1 m);
	
	public void flush();
	
	public List<Module> getByCriteria(List<Criterion> cl);
}
