package com.ocsoft.oa.service.flow.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import com.ocsoft.oa.service.flow.IFlowService;
@Service("flowService")
public class FlowServiceImpl implements IFlowService
{
	
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private static final String BPMN_DIR= System.getProperty("user.dir")+File.separatorChar+"web"+File.separatorChar+"flowDefDir"+File.separatorChar;
	
	@Override
	public String deployFlow(String processName)
	{
		String depId = null;
		try {
			Deployment deplpyment = repositoryService.createDeployment()
							 .addInputStream(processName, new FileInputStream(BPMN_DIR+processName))
							 .deploy();
			depId = deplpyment.getId();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return depId;

	}
	
	public void deleteFlow(String depId)
	{
		repositoryService.deleteDeployment(depId);
	}
	
	public void startFlow(String defkey)
	{
		ProcessInstance procInst = runtimeService.startProcessInstanceByKey(defkey);
		procInst.getActivityId();
	}

	
	@Override
	public List getAllFlow(String user)
	{
		return repositoryService.createProcessDefinitionQuery().list();
	}
	
	public ProcessDefinition getProcessDefByKey(String key)
	{
		return  repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).singleResult();
	}
	public List getActivityList(String key)
	{
		
		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).latestVersion().singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)        .getDeployedProcessDefinition(definition.getId());
		return processDefinition.getActivities();
	}

	public RepositoryService getRepositoryService()
	{
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService)
	{
		this.repositoryService = repositoryService;
	}

	public RuntimeService getRuntimeService()
	{
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService)
	{
		this.runtimeService = runtimeService;
	}
	
	
	

}
