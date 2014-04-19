package com.ocsoft.oa.dao.flow;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.flow.FlowDefinition;

public interface IFlowDefinitionDAO extends IBaseDao<FlowDefinition, Long>
{
	public FlowDefinition getByKey(String key);
}
