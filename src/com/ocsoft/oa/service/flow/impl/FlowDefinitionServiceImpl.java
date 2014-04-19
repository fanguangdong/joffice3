package com.ocsoft.oa.service.flow.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.flow.IFlowDefinitionDAO;
import com.ocsoft.oa.service.flow.IFlowDefinitionService;
import com.ocsoft.oa.utils.Paginate;
import com.ocsoft.oa.vo.flow.FlowDefinition;
@Service("flowDefService")
public class FlowDefinitionServiceImpl extends
		BaseServiceImpl<FlowDefinition, Long> implements IFlowDefinitionService
{
	private IFlowDefinitionDAO flowDefDAO;
	private RepositoryService repositoryService;
	@Override
	@Resource(name="flowDefDAO")
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		flowDefDAO = (IFlowDefinitionDAO) baseDao;
	}
	@Override
	public FlowDefinition save(FlowDefinition vo)
	{
		FlowDefinition po = null;
		if(vo!=null && vo.getFlowDefId()!=null)
		{
			po = this.get(vo.getDefTypeId());
		}
		else
		{
			po = new FlowDefinition();
		}
		this.copy(vo, po);
		po.setUser("test");
		return super.save(po);
	}

	
	private void copy(FlowDefinition vo, FlowDefinition po)
	{
		String[] ignoreProperties = {"flowDefId"};
		BeanUtils.copyProperties(vo, po, ignoreProperties);
	}
	
	@Override
	public Map getFlowDefList(Map<String,String> params)
	{
		//List records = this.getAll();
		String page = params.get("page");
		String rows = params.get("rows");
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
		Long count = processDefinitionQuery.count();
        List<ProcessDefinition> processDefList = processDefinitionQuery.listPage(Integer.valueOf(page), Integer.valueOf(rows));
        List pageRecs = new ArrayList();
        for(ProcessDefinition processDefinition : processDefList)
        {
        	FlowDefinition flowDef = new FlowDefinition();
        	
        	flowDef.setDeployId(processDefinition.getDeploymentId());
        	flowDef.setDefKey(processDefinition.getKey());
        	flowDef.setDefDesc(processDefinition.getDescription());
        	//flowDef.setDefTypeId(processDefinition.g)
        	flowDef.setSubject(processDefinition.getName());
        	flowDef.setVersionNo(processDefinition.getVersion());
        	flowDef.setStatus(processDefinition.isSuspended()? new Long(0) : new Long(1));
        	pageRecs.add(flowDef);
        }
        return Paginate.getPage(count.intValue(), pageRecs, Integer.valueOf(page), Integer.valueOf(rows));
	}
	public RepositoryService getRepositoryService()
	{
		return repositoryService;
	}
	public void setRepositoryService(RepositoryService repositoryService)
	{
		this.repositoryService = repositoryService;
	}
	@Override
	public void deploy(MultipartFile mFile, String defDir, String user)
	{
		String fileName = mFile.getOriginalFilename();

		String newFileName = defDir + File.separatorChar + fileName;

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(new File(newFileName)));
			bos.write(mFile.getBytes());
			bos.close();

			Deployment deployment = null;

			deployment = repositoryService.createDeployment()
					.addInputStream(fileName, mFile.getInputStream()).deploy();

			ProcessDefinitionQuery processDefQuery = repositoryService
					.createProcessDefinitionQuery();

			ProcessDefinition processDefinition = processDefQuery.deploymentId(deployment.getId()).singleResult();
			if (processDefinition != null) 
			{
				
				String key = processDefinition.getKey();
				FlowDefinition po; 
				FlowDefinition flowDef = getByKey(key);
				if (flowDef == null) 
				{
					po = new FlowDefinition();
				}
				else
				{
					po = flowDef;
				}

				po.setDeployId(processDefinition.getDeploymentId());
				po.setDefKey(processDefinition.getKey());
				po.setDefDesc(processDefinition.getDescription());
				po.setSubject(processDefinition.getName());
				po.setVersionNo(processDefinition.getVersion());
				po.setStatus(processDefinition.isSuspended() ? new Long(0) : new Long(1));
				po.setDefTypeId(new Long(0));
				po.setDefXml(mFile.getBytes().toString());
				po.setProcessDefId(processDefinition.getId());
				po.setUser(user);
				this.saveOrUpdate(po);
				flowDefDAO.flush();
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public FlowDefinition getByKey(String key)
	{
		// TODO Auto-generated method stub
		return flowDefDAO.getByKey(key);
	}
	
	public ProcessDefinitionEntity getProcessDefinitionByDefId(String defId)
	{
		ProcessDefinitionEntity ent = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.repositoryService)
				.getDeployedProcessDefinition(defId);
		return ent;
	}

}
