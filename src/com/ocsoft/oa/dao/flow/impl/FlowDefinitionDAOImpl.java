package com.ocsoft.oa.dao.flow.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.flow.IFlowDefinitionDAO;
import com.ocsoft.oa.vo.flow.FlowDefinition;
@Repository("flowDefDAO")
public class FlowDefinitionDAOImpl extends BaseDaoImpl<FlowDefinition, Long>
		implements IFlowDefinitionDAO
{

	@Override
	public FlowDefinition getByKey(String key)
	{
		return (FlowDefinition) getSession().createCriteria(this.entityClass.getName()).add(Restrictions.eq("defKey", key)).uniqueResult();
	}

	

}
