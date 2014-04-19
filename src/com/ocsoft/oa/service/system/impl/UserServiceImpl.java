package com.ocsoft.oa.service.system.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IGroupDAO;
import com.ocsoft.oa.dao.system.IRoleDAO;
import com.ocsoft.oa.dao.system.IUserDAO;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.utils.EncryptionUtil;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
@Service("userService")
public class UserServiceImpl  
extends BaseServiceImpl<AppUser, Long> implements IUserService
{
	private IUserDAO userDAO;
	private IGroupDAO groupDAO;
	private IRoleDAO roleDAO;
	@Resource(name="userDAO")
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		userDAO = (IUserDAO) baseDao;
	}

	@Override
	public AppUser getByUserName(String userName)
	{
		
		return userDAO.getByUserName(userName);
	}

	@Override
	public void save(AppUser userVO, String user)
	{
		Long userId = userVO.getUserId();
		AppUser userPO = null;
		if(userId != null)
		{
			userPO = get(userId);
		}
		else
		{
			userPO = new AppUser();
		}
		this.copy(userVO, userPO);
		try {
			userPO.setUserPwd(EncryptionUtil.encrypt(userVO.getUserPwd(), userVO.getUserName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPO.setUser(user);
		save(userPO);
		
	}
	
	public void assign(AppUser userVO, String user)
	{
		Long userId = userVO.getUserId();
		groupDAO.removeByUserId(userId);
		String roleIds = userVO.getRoleIds();
		if(!StringUtil.isBlankOrNull(roleIds))
		{
			String[] rIds = roleIds.split(",");
			if(rIds.length>0)
			{
				AppGroup group = null;
				AppRole appRole = null;
				AppUser appUser = null;
				for(String roleId : rIds)
				{
					if(NumberUtil.isNumber(roleId))
					{
						appRole = roleDAO.get(Long.valueOf(roleId));
						appUser = get(userId);
						group = new AppGroup();
						group.setAppRole(appRole);
						group.setAppUser(appUser);
						groupDAO.save(group);
					}
				}
			}
		}
	}
	
	private void copy(AppUser vo, AppUser po)
	{
		String[] ignoreProperties = {"userId","addUser","addDate","editUser","editDate"};
		BeanUtils.copyProperties(vo, po, ignoreProperties);
		
	}
	public IGroupDAO getGroupDAO()
	{
		return groupDAO;
	}

	public void setGroupDAO(IGroupDAO groupDAO)
	{
		this.groupDAO = groupDAO;
	}

	public IRoleDAO getRoleDAO()
	{
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO)
	{
		this.roleDAO = roleDAO;
	}
	
	

	
	
	

	
	
	
	
	
	

}
