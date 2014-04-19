package com.ocsoft.oa.controller.system;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.core.web.JQGridBean;
import com.ocsoft.oa.service.system.IGroupService;
import com.ocsoft.oa.service.system.IRoleService;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
import com.ocsoft.oa.web.AjaxRtn;

@Controller
public class UserController
{
	private IUserService userService;
	private IRoleService roleService;
	private IGroupService groupService;
	@RequestMapping("/user/showList")
	public ModelAndView showList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userList");
		return mv;
	}
	
	@RequestMapping("/user/list")
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
		return userService.getPageList(params);
	}
	@RequestMapping("/user/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		String userId = request.getParameter("userId");
		AppUser user = null;
		if(StringUtil.isBlankOrNull(userId))
		{
			user = new AppUser();
		}
		else
		{
			user = userService.get(Long.valueOf(userId));
		}
		
		mv.addObject("userDetail", user);
		mv.setViewName("userDetail");
		return mv;
	}
	
	@RequestMapping("/user/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		return edit(request, response);
	}
	@RequestMapping("/user/showAssignRoles")
	public ModelAndView showAssignRoles(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		List<AppRole> availableRoles = roleService.getAll();
		List<AppRole> selectedRoles = null;
		String userId = request.getParameter("userId");
		AppUser user = null;
		if(StringUtil.isBlankOrNull(userId))
		{
			user = new AppUser();
			selectedRoles = new ArrayList<AppRole>();
		}
		else
		{
			user = userService.get(Long.valueOf(userId));
			selectedRoles = groupService.getRolesByUserId(Long.valueOf(userId));
		}
		availableRoles.removeAll(selectedRoles);
		mv.addObject("availableRoles", availableRoles);
		mv.addObject("selectedRoles",selectedRoles);
		mv.addObject("userDetail", user);
		mv.setViewName("assignRoles");
		return mv;
	}
	@RequestMapping("/user/save")
	@ResponseBody
	public AjaxRtn save(HttpServletRequest request, HttpServletResponse response, AppUser userDetail)
	{
		AjaxRtn rtn = new AjaxRtn();
		userService.save(userDetail, "test");
		rtn.setSuccess();
		rtn.setObject("userDetail", userDetail);
		return rtn;
	}
	@RequestMapping("/user/assign")
	public String assign(HttpServletRequest request, HttpServletResponse response, AppUser userDetail)
	{
		
		userService.assign(userDetail, "test");
		return "userList";
	}

	public IUserService getUserService()
	{
		return userService;
	}

	public void setUserService(IUserService userService)
	{
		this.userService = userService;
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
	

}
