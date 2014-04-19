package com.ocsoft.oa.service.flow;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;

import com.ocsoft.core.service.IBaseService;

public interface IFlowService 
{
	public String deployFlow(String fileName);
	public List getAllFlow(String user);
	public ProcessDefinition getProcessDefByKey(String key);
	public List getActivityList(String key);
	public void deleteFlow(String depId);
	public void startFlow(String defkey);
	
	

}
