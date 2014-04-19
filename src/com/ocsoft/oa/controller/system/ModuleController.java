package com.ocsoft.oa.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.oa.service.system.IActionService;
import com.ocsoft.oa.service.system.IClientService;
import com.ocsoft.oa.service.system.IModuleService;
import com.ocsoft.oa.utils.Constants;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.utils.TreeUtil;
import com.ocsoft.oa.vo.system.Action;
import com.ocsoft.oa.vo.system.Module;
import com.ocsoft.oa.web.AjaxRtn;

@Controller
public class ModuleController
{
	private IModuleService moduleService;
	private IClientService clientService;
	private IActionService actionService;
	
	
	@RequestMapping("/module/show")
	public ModelAndView showMgt(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("moduleMgt");
		return mv;
	}
	@RequestMapping("/module/getTreeData")
	@ResponseBody
	public List<Module> getTreeData(HttpServletRequest request, HttpServletResponse response)
	{
		List res = new ArrayList();
		Long moduleId = NumberUtil.toLong(request.getParameter("moduleId"), 0L);
		Long parentId = NumberUtil.toLong(request.getParameter("parentId"), 0L);
	//	if(moduleId == 0L && parentId == 0L)
		//{
			Module parent = moduleService.getParentModuleByParentId(parentId);
			parent.setChildren(moduleService.getByParentId(moduleId,true));
			res.add(parent);
	//	}
	//	else
	//	{
	//		res = moduleService.getByParentId(moduleId,true);
	//	}
		
		return res;
	}
	
	@RequestMapping("/module/getNavTreeData")
	@ResponseBody
	public List<Module> getNavTreeData(HttpServletRequest request, HttpServletResponse response)
	{
		List res = new ArrayList();
		String user = request.getSession().getAttribute(Constants.SESS_USER_KEY)+"";
		Long moduleId = NumberUtil.toLong(request.getParameter("moduleId"), 0L);
		res = moduleService.getAdminMenu(moduleId,user);
		return res;
	}
	
	@RequestMapping("/module/getModules")
	@ResponseBody
	public List getMoudels(HttpServletRequest request, HttpServletResponse response)
	{
		List list = null;
		String id = request.getParameter("id");
		String pid = null;
		if(StringUtil.isBlankOrNull(id))
		{
			pid="0";
		}
		else
		{
			pid = id;
		}
		List<Module> moudels = moduleService.getByParentId(Long.valueOf(pid),true);
		List<Action> actions = actionService.getByModuleId(Long.valueOf(pid));
		if(StringUtil.isBlankOrNull(id))
		{
			list = new ArrayList();
			Map node = new HashMap();
			node.put("id",0);
			node.put("name", "Root");
			node.put("nodeType", "module");
			if(moudels!= null && moudels.size()>0)
			{
				node.put("hasChildren", true);
			}
			list.add(node);
		}
		else
		{
			list =TreeUtil.getTree(moudels);
			list.addAll(TreeUtil.getTree(actions));
		}
		return list;
	}
	
	
	/**
	 * Get module tree, populate children
	 * @param module
	 * @return
	 */
	public void getAllModules(Module module)
	{
		List<Module> modules = null;
		if(module!=null)
		{
			modules = moduleService.getByParentId(module.getModuleId());
			if(modules!=null && modules.size()>0)
			{
				//module.setHasChildren(true);
				//module.setChildren(TreeUtil.getTree(modules));
				for(Module m : modules)
				{
					getAllModules(m);
				}
			}
		}
	}
	
	@RequestMapping("/module/showDetail")
	public ModelAndView showPosDetail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("moduleDetail");
		String mId = request.getParameter("moduleId");
		String pId = request.getParameter("parentId");
		Map model = new HashMap();
		Module moduleDetail = null;
		String parent = "";
		if(!StringUtil.isBlankOrNull(mId))
		{
			moduleDetail = moduleService.get(Long.valueOf(mId));
		}
		else
		{
			moduleDetail = new Module();
			moduleDetail.setParentId(Long.valueOf(pId));
		}
		
		mv.addObject("moduleDetail", moduleDetail);
		return mv;
	}
	
	@RequestMapping("/module/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("moduleDetail");
		String mId = request.getParameter("moduleId");
		String pId = request.getParameter("parentId");
		Module moduleDetail = null;
		if(!StringUtil.isBlankOrNull(mId))
		{
			moduleDetail = moduleService.get(Long.valueOf(mId));
		}
		else
		{
			moduleDetail = new Module();
			moduleDetail.setParentId(Long.valueOf(pId));
		}
		
		mv.addObject("moduleDetail", moduleDetail);
		return mv;
	}
	
	@RequestMapping("/module/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("moduleDetail");
		String mId = request.getParameter("moduleId");
		String pId = request.getParameter("parentId");
		Module moduleDetail = null;
		if(!StringUtil.isBlankOrNull(mId))
		{
			moduleDetail = moduleService.get(Long.valueOf(mId));
		}
		else
		{
			moduleDetail = new Module();
			moduleDetail.setParentId(Long.valueOf(pId));
		}
		
		mv.addObject("moduleDetail", moduleDetail);
		return mv;
	}
	
	@RequestMapping("/module/save")
	@ResponseBody
	public AjaxRtn saveMoudel(HttpServletRequest request, HttpServletResponse response, Module vo)
	{
		AjaxRtn rtn = new AjaxRtn();
		Module m = null;
		try {
			if(vo.getModuleId()==null)
			{
				m = new Module();
			}
			else
			{
				m = moduleService.get(vo.getModuleId());
			}
			
			m.setParentId(vo.getParentId());
			m.setModuleName(vo.getModuleName());
			m.setTitle(vo.getTitle());
			m.setIcon(vo.getIcon());
			m.setDefaultUrl(vo.getDefaultUrl());
			m.setResSn(vo.getResSn());
			m.setShowInMenu(vo.getShowInMenu());
			m.setUser("test");
			moduleService.saveOrUpdate(m);
			rtn.setObject("moduleDetail", m);
			rtn.setSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			rtn.setFail();
		}
		return rtn;
	}
	
	@RequestMapping("/module/del")
	@ResponseBody
	public Map delMoudel(HttpServletRequest request, HttpServletResponse response)
	{
		Map rtn = new HashMap();
		String mId 				= request.getParameter("moduleId");
		moduleService.remove(Long.valueOf(mId));
		rtn.put("status",	1);
		
		return rtn;
	}
	
	
	
	
	
	
	//getter and setter
	
	public IClientService getClientService()
	{
		return clientService;
	}
	public IModuleService getModuleService()
	{
		return moduleService;
	}
	public void setModuleService(IModuleService moduleService)
	{
		this.moduleService = moduleService;
	}
	public void setClientService(IClientService clientService)
	{
		this.clientService = clientService;
	}
	public IActionService getActionService()
	{
		return actionService;
	}
	public void setActionService(IActionService actionService)
	{
		this.actionService = actionService;
	}
	
	private void copy(Action vo, Action po)
	{
		String[] ignoreProperties = {"actionId"};
		BeanUtils.copyProperties(vo, po, ignoreProperties);
	}
	

}
