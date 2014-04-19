package com.ocsoft.oa.service.system.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IACLDAO;
import com.ocsoft.oa.dao.system.IActionDAO;
import com.ocsoft.oa.dao.system.IGroupDAO;
import com.ocsoft.oa.dao.system.IModuleDAO;
import com.ocsoft.oa.dao.system.IRoleDAO;
import com.ocsoft.oa.dao.system.IUserDAO;
import com.ocsoft.oa.service.system.IRoleService;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.ACL;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
@Service("roleService")
public class RoleService extends BaseServiceImpl<AppRole, Long> implements IRoleService
{
	private IRoleDAO roleDAO;
	private IGroupDAO groupDAO;
	private IUserDAO userDAO;
	private IModuleDAO moduleDAO;
	private IACLDAO aclDAO;
	private IActionDAO actionDAO;
	@Override
	@Resource(name="roleDAO")
	public void setDao(IBaseDao baseDao)
	{
		super.setDao(baseDao);
		roleDAO = (IRoleDAO) baseDao;
	}

	@Override
	public void hasPermission(Long userId, int resSn, int actionType)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AppRole> getRolesByUId(Long uId)
	{
		return roleDAO.getByUserId(uId);
	}
	
	public void save(AppRole roleVO, String user)
	{
		Long roleId = roleVO.getRoleId();
		AppRole rolePO = null;
		if(roleId != null)
		{
			rolePO = get(roleId);
		}
		else
		{
			rolePO = new AppRole();
		}
		this.copy(roleVO, rolePO);
		rolePO.setUser(user);
		save(rolePO);
	}
	public void assign(AppRole roleVO, String user)
	{
		AppRole rolePO = get(roleVO.getRoleId());
		groupDAO.removeByRoleId(rolePO.getRoleId());
		groupDAO.flush();
			String userIds = roleVO.getUserIds();
			if(!StringUtil.isBlankOrNull(userIds))
			{
				String[] uIds = userIds.split(",");
				if(uIds.length>0)
				{
					AppGroup group = null;
					AppUser appUser = null;
					for(String userId : uIds)
					{
						if(NumberUtil.isNumber(userId))
						{
							appUser = userDAO.get(Long.valueOf(userId));
							group = new AppGroup();
							group.setAppRole(rolePO);
							group.setAppUser(appUser);
							groupDAO.save(group);
						}
					}
				}
			}
	}
	@Override
	public void saveAuth(Long roleId, Map nodes)
	{
		aclDAO.removeByRoleId(roleId);
		if(nodes!=null && nodes.size()>0)
		{
			AppRole role = this.get(roleId);
			Set nodeEntrys = nodes.entrySet();
			for(Iterator it = nodeEntrys.iterator();it.hasNext();)
			{
				Entry node = (Entry)it.next();
				String nodeName = node.getKey().toString();
				//String nodeValue = node.getValue().toString();
				//if(StringUtil.isNotBlank(nodeName) && nodeName.indexOf("_")>0)
				if(StringUtil.isNotBlank(nodeName) )
				{
					//String resSn = nodeName.split("_")[1];
					//String actionTypeCode = nodeName.split("_")[2];
					//show
					ACL acl = new ACL();
					acl.setAppRole(role);
					acl.setActionType(1);
					//acl.setResSn(Integer.valueOf(resSn));
					acl.setResSn(nodeName);
					acl.setStatus(1);
					aclDAO.save(acl);
				}
			}
			
		}
		
	}
	
	
	private void copy(AppRole vo, AppRole po)
	{
		String[] ignoreProperties = {"roleId","addUser","addDate","editUser","editDate"};
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

	public IUserDAO getUserDAO()
	{
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public IModuleDAO getModuleDAO()
	{
		return moduleDAO;
	}

	public void setModuleDAO(IModuleDAO moduleDAO)
	{
		this.moduleDAO = moduleDAO;
	}

	public IACLDAO getAclDAO()
	{
		return aclDAO;
	}

	public void setAclDAO(IACLDAO aclDAO)
	{
		this.aclDAO = aclDAO;
	}

	public IActionDAO getActionDAO()
	{
		return actionDAO;
	}

	public void setActionDAO(IActionDAO actionDAO)
	{
		this.actionDAO = actionDAO;
	}

	

	

}
