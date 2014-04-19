package com.ocsoft.oa.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IModuleDAO;
import com.ocsoft.oa.vo.system.Module;
@Repository("moduleDAO")
public class ModuleDAOImpl extends BaseDaoImpl<Module, Long> implements IModuleDAO
{

	@Override
	public List<Module> getByPId(Long pId)
	{
		System.out.println("==============================================");
		System.out.println("query= " + HQL_LIST_ALL + " m where m.parentId=:pId");
		System.out.println("pId= " + pId);
		//Query query = getSession().createQuery(HQL_LIST_ALL+" m where m.parentId=:pId");
		
		@SuppressWarnings("unchecked")
		List<Module> modules = getSession().createSQLQuery("select * from module").addEntity(Module.class).list();
		
		//query.setLong("pId", pId);
		//List<Module> modules = new ArrayList<Module>();
		//Module m = new Module();
		//modules.add(m);
		
		return modules;
		//return query.list();
	}
	
	@Override
	public List<Module> getByClientId(Long clientId)
	{
		return getSession().createCriteria(entityClass)
					.add(Restrictions.eq("clientId", clientId))
					.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getByCidAndPid(Long clientId, Long parentId)
	{
		return getSession().createCriteria(entityClass)
		.add(Restrictions.eq("clientId", clientId))
		.add(Restrictions.eq("parentId", parentId))
		.list();
	}

	@Override
	public Module getByResSn(String resSn)
	{
		return (Module)getSession().createCriteria(entityClass)
				.add(Restrictions.eq("resSn", resSn))
				.uniqueResult();
	}
	
	

}
