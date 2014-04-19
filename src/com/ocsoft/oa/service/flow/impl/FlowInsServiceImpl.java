package com.ocsoft.oa.service.flow.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;

import com.ocsoft.oa.service.flow.IFlowInsService;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.vo.flow.FlowInstance;
import com.ocsoft.oa.vo.system.AppUser;

public class FlowInsServiceImpl implements IFlowInsService
{
	@Resource(name="runtimeService")
	private RuntimeService runtimeService;
	@Resource(name="userService")
	private IUserService userService;
	
	@Override
	public void startFlow(String key, String user)
	{
		ProcessInstance ins = runtimeService.startProcessInstanceByKey(key);
		String actInstId = ins.getProcessDefinitionId();
		String actDefId = ins.getProcessDefinitionId();
		
		AppUser appUser =  userService.getByUserName(key);
		
		FlowInstance flowIns = new FlowInstance();
		flowIns.setActDefId(actDefId);
		flowIns.setActInstId(actInstId);
		flowIns.setCreator(key);
		flowIns.setCreatorId(appUser.getUserId());
		
		
		

	}

	@Override
	public List getMyRequest(String user)
	{
		
		return null;
	}

	@Override
	public List getMyFinished(String user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getUserPending(String user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getUserTaken(String user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getUserFinished(String user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void del(Long id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List getAllRunning()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllFinished()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllForDef(String defId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllRunningForDef(String defId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllFinishedForDef(String defId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
