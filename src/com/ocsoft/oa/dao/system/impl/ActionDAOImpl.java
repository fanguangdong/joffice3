package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IActionDAO;
import com.ocsoft.oa.vo.system.Action;
@Repository("actionDAO")
public class ActionDAOImpl extends BaseDaoImpl<Action, Long> implements IActionDAO
{
	@Override
	public List<Action> getByModuleId(Long moduleId)
	{
		List<Action> list = getSession().createCriteria(entityClass)
						   .add(Restrictions.eq("moduleId", moduleId))
						   .list();
		return list;
	}

	@Override
	public void removeByModuleId(Long moduleId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append("delete ");
		hql.append(entityClass.getName() );
		hql.append("where moduleId = :moduleId");
		
		getSession().createQuery(hql.toString())
					.setLong("moduleId", moduleId)
					.executeUpdate();
	}

}
