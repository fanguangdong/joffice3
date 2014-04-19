package com.ocsoft.oa.controller.system;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.core.web.JQGridBean;
import com.ocsoft.oa.service.system.IACLService;
import com.ocsoft.oa.service.system.IActionService;
import com.ocsoft.oa.service.system.IGroupService;
import com.ocsoft.oa.service.system.IModuleService;
import com.ocsoft.oa.service.system.IRoleService;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.utils.TreeUtil;
import com.ocsoft.oa.vo.system.Action;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
import com.ocsoft.oa.vo.system.Module;

@Controller
public class RoleController
{
	private static final Log logger = LogFactory.getLog(RoleController.class);
	private IRoleService roleService;
	private IGroupService groupService;
	private IUserService userService;
	private IModuleService moduleService;
	private IActionService actionService;
	private IACLService aclService;
	@RequestMapping("/role/showList")
	public ModelAndView showList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roleList");
		return mv;
	}

	@RequestMapping("/role/list")
	@ResponseBody
	public JQGridBean list(HttpServletRequest request)
	{
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements())
		{
			String name = names.nextElement();
			String value = request.getParameter(name);
			params.put(name, value);
		}
		return roleService.getPageList(params);
	}
	@RequestMapping("/role/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		String roleId = request.getParameter("roleId");
		AppRole role = null;
		if(!StringUtil.isBlankOrNull(roleId))
		{
			role = roleService.get(Long.valueOf(roleId));
		}
		else
		{
			role = new AppRole();
		}
		
		mv.addObject("roleDetail", role);
		mv.setViewName("roleDetail");
		
		return mv;
	}
	
	@RequestMapping("/role/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		return edit(request, response);
	}
	@RequestMapping("/role/save")
	@ResponseBody
	public Map save(HttpServletRequest request, HttpServletResponse response, AppRole roleDetail)
	{
		Map rtn = new HashMap();
		roleService.save(roleDetail, "test");
		rtn.put("result", roleDetail);
		rtn.put("status", 1);
		return rtn;
	}
	
	@RequestMapping("/role/showAssignedUsers")
	public ModelAndView showAssignedUsers(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		String roleId = request.getParameter("roleId");
		List<AppUser> availableUsers = userService.getAll();
		List<AppUser> selecteUsers = null;
		AppRole role = null;
		if(!StringUtil.isBlankOrNull(roleId))
		{
			role = roleService.get(Long.valueOf(roleId));
			selecteUsers = groupService.getUsersByRoleId(Long.valueOf(roleId));
		}
		else
		{
			role = new AppRole();
			selecteUsers = new ArrayList<AppUser>();
		}
		availableUsers.removeAll(selecteUsers);
		
		mv.addObject("availableUsers", availableUsers);
		mv.addObject("selectedUsers", selecteUsers);
		mv.addObject("roleDetail", role);
		mv.setViewName("assignedUsers");
		
		return mv;
	}
	
	@RequestMapping("/role/assign")
	public String assign(HttpServletRequest request, HttpServletResponse response, AppRole roleDetail)
	{
		roleService.assign(roleDetail, "test");
		return "roleList";
	}
	@RequestMapping("/role/del")
	public String del(HttpServletRequest request, HttpServletResponse response)
	{
		String  roleId = request.getParameter("roleId");
		if( !StringUtil.isBlankOrNull(roleId))
		{
			roleService.remove(Long.valueOf(roleId));
		}
		return "roleList";
	}
	
	
	@RequestMapping("/role/showAuth")
	public ModelAndView showAuth(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth");
		mv.addObject("roleId", request.getParameter("roleId"));
		return mv;
	}
	
	@RequestMapping("/role/getAuth")
	@ResponseBody
	public List getAuthTree(HttpServletRequest request, HttpServletResponse response)
	{
		List nodes = new ArrayList();
		String moduleId = request.getParameter("id");
		if(StringUtil.isBlankOrNull(moduleId))
		{
			moduleId = "0";
		}
		Module m = moduleService.get(Long.valueOf(moduleId));
		List<Module> modules = moduleService.getByParentId(Long.valueOf(moduleId),true);
		
		return modules;
	}
	@RequestMapping("/role/getPermission")
	@ResponseBody
	public Map getPermission(HttpServletRequest request, HttpServletResponse response)
	{
		String roleId = request.getParameter("roleId");
		return aclService.getACLByRoleId(Long.valueOf(roleId));
		
	}
	@RequestMapping("/role/saveAuth")
	@ResponseBody
	public Map saveAuth(HttpServletRequest request, HttpServletResponse response)
	{
		Map rtn = new HashMap();
		String checks = request.getParameter("checks");
		String roleId = request.getParameter("roleId");
		Map nodes = new HashMap();
		
		
		if(checks != null)
		{
			String[] params = checks.split(",");
			if(params.length>0)
			{
				int len = params.length;
				for(int i=0;i<len;i++)
				{
					/*
					String[] param = params[i].split(":");
					if(params.length>1)
					{
						String name = params[i].split(":")[0];
						String value = params[i].split(":")[1];
						nodes.put(name, value);
						logger.info(name+"="+value);
					}
					*/
					String param = params[i];
					nodes.put(param, true);
					
				}
			}
		}
		roleService.saveAuth(Long.valueOf(roleId), nodes);
		rtn.put("status", 1);
		return rtn;
	}
	
	

	public IRoleService getRoleService()
	{
		return roleService;
	}

	public void setRoleService(IRoleService roleService)
	{
		this.roleService = roleService;
	}
	
	

	public IGroupService getGroupService()
	{
		return groupService;
	}

	public void setGroupService(IGroupService groupService)
	{
		this.groupService = groupService;
	}

	public IUserService getUserService()
	{
		return userService;
	}

	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}

	public IModuleService getModuleService()
	{
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService)
	{
		this.moduleService = moduleService;
	}

	public IActionService getActionService()
	{
		return actionService;
	}

	public void setActionService(IActionService actionService)
	{
		this.actionService = actionService;
	}

	public IACLService getAclService()
	{
		return aclService;
	}

	public void setAclService(IACLService aclService)
	{
		this.aclService = aclService;
	}

}
