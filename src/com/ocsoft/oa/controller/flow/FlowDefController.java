package com.ocsoft.oa.controller.flow;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.core.web.JQGridBean;
import com.ocsoft.oa.service.flow.IFlowDefinitionService;
import com.ocsoft.oa.vo.flow.FlowDefinition;
import com.ocsoft.oa.web.AjaxRtn;

@Controller

public class FlowDefController 
{
	@Resource(name="flowDefService")
	private IFlowDefinitionService flowDefService;
	private RepositoryService repositoryService;
	
	@RequestMapping("/flowDef/show")
	public String showFlowDefList()
	{
		return "flowDefList";
	}
	@RequestMapping("/flowDef/getDefList")
	@ResponseBody
	public JQGridBean getDefList(HttpServletRequest request)
	{
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements())
		{
			String name = names.nextElement();
			String value = request.getParameter(name);
			params.put(name, value);
		}
		return flowDefService.getPageList(params);
	}
	@RequestMapping("/flowDef/showDetail")
	public ModelAndView showFlowDefDetail(@RequestParam Long flowDefId)
	{
		ModelAndView mv = new ModelAndView("flowDefDetail");
		FlowDefinition flowDef = null;
		if(flowDefId>0)
		{
			flowDef = flowDefService.get(flowDefId);
		}
		else
		{
			flowDef = new FlowDefinition();
		}
		mv.addObject("flowDef", flowDef);
		return mv;
	}
	
	@RequestMapping("/flowDef/add")
	public ModelAndView add(@RequestParam Long flowDefId)
	{
		ModelAndView mv = new ModelAndView("flowDefDetail");
		FlowDefinition flowDef = null;
		if(flowDefId>0)
		{
			flowDef = flowDefService.get(flowDefId);
		}
		else
		{
			flowDef = new FlowDefinition();
		}
		mv.addObject("flowDef", flowDef);
		return mv;
	}
	
	@RequestMapping("/flowDef/edit")
	public ModelAndView edit(@RequestParam Long flowDefId)
	{
		ModelAndView mv = new ModelAndView("flowDefDetail");
		FlowDefinition flowDef = null;
		if(flowDefId>0)
		{
			flowDef = flowDefService.get(flowDefId);
		}
		else
		{
			flowDef = new FlowDefinition();
		}
		mv.addObject("flowDef", flowDef);
		return mv;
	}
	
	@RequestMapping("/flowDef/save")
	@ResponseBody
	public AjaxRtn save(FlowDefinition flowDef)
	{
		AjaxRtn rtn = new AjaxRtn();
		flowDefService.save(flowDef);
		rtn.setSuccess();
		//rtn.setObject("", value)
		return rtn;
	}
	@RequestMapping("/flowDef/showUpload")
	public ModelAndView showUpload(@RequestParam Long flowDefId)
	{
		ModelAndView mv = new ModelAndView("uploadFlowDef");
		mv.addObject("flowDefId", flowDefId);
		return mv;
	}
	@RequestMapping("/flowDef/uploadDef")
	public void uploadDef(MultipartHttpServletRequest request, HttpServletResponse response) 
	{
		try {
			MultipartFile mFile = request.getFile("file");
			String defDir = request.getRealPath("/flowDefDir/");
			flowDefService.deploy(mFile, defDir,"test");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>parent.afterSave();</script>");
			out.flush();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping("/flowDef/showProcess")
	public void showProcess(@RequestParam String resourceType, @RequestParam String processId, HttpServletResponse response)
	{
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processId).singleResult();
			String resourceName = "";
			if (resourceType.equals("image")) {
			    resourceName = processDefinition.getDiagramResourceName();
			} else if (resourceType.equals("xml")) {
			    resourceName = processDefinition.getResourceName();
			}
			InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			    response.getOutputStream().write(b, 0, len);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public RepositoryService getRepositoryService()
	{
		return repositoryService;
	}
	public void setRepositoryService(RepositoryService repositoryService)
	{
		this.repositoryService = repositoryService;
	}
}
