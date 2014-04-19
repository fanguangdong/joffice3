package com.ocsoft.oa.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IACLDAO;
import com.ocsoft.oa.dao.system.IRoleDAO;
import com.ocsoft.oa.dao.system.IUserDAO;
import com.ocsoft.oa.service.system.IACLService;
import com.ocsoft.oa.vo.system.ACL;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
@Service("aclService")
public class ACLServiceImpl extends BaseServiceImpl<ACL, Long> implements IACLService
{
	private IACLDAO dao;
	private IRoleDAO roleDAO;
	private IUserDAO userDAO;
	

	@Override
	public List<ACL> getACLByUid(Long uId, String resSn)
	{
		return dao.getACLByUid(uId, resSn);
	}

	@Override
	public void updatePermission(Long roleId, String resSn, int actionType, boolean status)
	{
		AppRole role = roleDAO.get(roleId);
		dao.updatePermission(role, resSn, actionType, status);
	}
	
	@Override
	public boolean hasPermission(String user, String resSn, int actionType)
	{
		boolean res = false;
		AppUser appUser = userDAO.getByUserName(user);
		int permission = 0;
		if(appUser != null)
		{
			List<AppRole> roles = roleDAO.getByUserId(appUser.getUserId());
			if(roles!=null && roles.size()>0)
			{
				for(AppRole role: roles)
				{
					List<ACL> acls = dao.getAcl(role.getRoleId(), resSn);
					for(ACL acl : acls)
					{
						if(acl.getStatus()>0)
						{
							permission = permission | acl.getActionType();
						}
					}
				}
			}
		}
		res = permission>0 && ((permission & actionType) == permission) ;
		
		
		return res;
		
	}
	public Map getACLByRoleId(Long roleId)
	{
		Map aclMap = new HashMap();
		List<ACL> acls = dao.getACLByRoleId(roleId);
		if(acls!=null && acls.size()>0)
		{
			for(ACL acl : acls)
			{
				String resSn = acl.getResSn();
				Integer actionType = acl.getActionType();
				aclMap.put(resSn, true);
			}
		}
		return aclMap;
		
	}
	public int getPermission(String user, int resSn)
	{
		int permission = 0;
		
		
		return permission;
	}
	
	
	
	//getters and setters
	
	public IRoleDAO getRoleDAO()
	{
		return roleDAO;
	}
	@Resource(name="roleDAO")
	public void setRoleDAO(IRoleDAO roleDAO)
	{
		this.roleDAO = roleDAO;
	}
	
	public IUserDAO getUserDAO()
	{
		return userDAO;
	}

	@Override
	@Resource(name="aclDAO")
	public void setDao(IBaseDao baseDao)
	{
		super.setDao(baseDao);
		this.dao = (IACLDAO) baseDao;
	}

	
	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	




	
	
	
	
	
	

}
