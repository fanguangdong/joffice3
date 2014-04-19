package com.ocsoft.oa.controller.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.oa.service.system.IGroupService;
import com.ocsoft.oa.service.system.IRoleService;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;

@Controller
public class GroupController
{/*
	private IGroupService groupService;
	private IRoleService roleService;
	private IUserService userService;
	@RequestMapping("/group/showList")
	public ModelAndView showList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("groupList");
		return mv;
	}
	
	@RequestMapping("/group/list")
	@ResponseBody
	public List list(HttpServletRequest request, HttpServletResponse response)
	{
		return groupService.getAll();
	}
	@RequestMapping("/group/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("groupDetail");
		List<AppUser> availableUsers = userService.getAll();
		List<AppRole> availableRoles = roleService.getAll();
		
		List<AppUser> selectedUsers = null;
		List<AppRole> selectedRoles = null;
		
		String groupId = request.getParameter("groupId");
		AppGroup group = null;
		if(StringUtil.isBlankOrNull(groupId))
		{
			group = new AppGroup();
			selectedUsers = new ArrayList();
			selectedRoles = new ArrayList();
		}
		else
		{
			group = groupService.get(Long.valueOf(groupId));
			//selectedUsers = groupService.getUsers(Long.valueOf(groupId));
			//selectedRoles = groupService.getRoles(Long.valueOf(groupId));
		}
		availableUsers.removeAll(selectedUsers);
		availableRoles.removeAll(selectedRoles);
		
		mv.addObject("availableUsers", availableUsers);
		mv.addObject("availableRoles", availableRoles);
		mv.addObject("selectedUsers", selectedUsers);
		mv.addObject("selectedRoles", selectedRoles);
		mv.addObject("groupDetail", group);
		return mv;
	}
	
	@RequestMapping("/group/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		return edit(request, response);
	}
	@RequestMapping("/group/save")
	public String save(HttpServletRequest request, HttpServletResponse response, AppGroup groupDetail)
	{
		Long groupId = groupDetail.getGroupId();
		AppGroup groupPO = null;
		if( groupId !=null)
		{
			groupPO = groupService.get(groupId);
		}
		else
		{
			groupPO = new AppGroup();
		}
		this.copy(groupDetail, groupPO);
		String userIds = groupDetail.getUserIds();
		String roleIds = groupDetail.getRoleIds();
		Set<AppUser> users = new HashSet<AppUser>();;
		Set<AppRole> roles = new HashSet<AppRole>();
		//set users
		if(!StringUtil.isBlankOrNull(userIds))
		{
			String[] uIds = userIds.split(",");
			if(uIds.length>0)
			{
				for(String userId : uIds)
				{
					if(NumberUtil.isNumber(userId))
					{
						AppUser user = userService.get(Long.valueOf(userId));
						users.add(user);
					}
				}
			}
		}
		//set roles
		if(!StringUtil.isBlankOrNull(roleIds))
		{
			String[] rIds = roleIds.split(",");
			if(rIds.length>0)
			{
				for(String roleId : rIds)
				{
					if(NumberUtil.isNumber(roleId))
					{
						AppRole role = roleService.get(Long.valueOf(roleId));
						roles.add(role);
					}
				}
			}
		}
		groupPO.setUsers(users);
		groupPO.setRoles(roles);
		groupPO.setUser("test");
		groupService.save(groupPO);
		return "groupList";
	}

	
	
	private void copy(AppGroup vo, AppGroup po)
	{
		String[] ignoreProperties = {"groupId","addUser","addDate","editUser","editDate"};
		BeanUtils.copyProperties(vo, po, ignoreProperties);
		
	}

	public IGroupService getGroupService()
	{
		return groupService;
	}

	public void setGroupService(IGroupService groupService)
	{
		this.groupService = groupService;
	}

	public IRoleService getRoleService()
	{
		return roleService;
	}

	public void setRoleService(IRoleService roleService)
	{
		this.roleService = roleService;
	}

	public IUserService getUserService()
	{
		return userService;
	}

	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	
*/
}
