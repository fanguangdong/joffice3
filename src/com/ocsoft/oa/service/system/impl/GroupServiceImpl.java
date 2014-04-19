package com.ocsoft.oa.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IGroupDAO;
import com.ocsoft.oa.service.system.IGroupService;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<AppGroup, Long> implements IGroupService
{
	private IGroupDAO groupDAO;
	@Resource(name="groupDAO")
	@Override
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		groupDAO = (IGroupDAO) baseDao;
	}
	@Override
	public List<AppUser> getUsersByRoleId(Long roleId)
	{
		// TODO Auto-generated method stub
		return groupDAO.getUsersByRoleId(roleId);
	}
	@Override
	public List<AppRole> getRolesByUserId(Long userId)
	{
		// TODO Auto-generated method stub
		return groupDAO.getRolesByUserId(userId);
	}
	@Override
	public List<AppGroup> getByRoleId(Long roleId)
	{
		// TODO Auto-generated method stub
		return groupDAO.getByRoleId(roleId);
	}
	@Override
	public List<AppGroup> getByUserId(Long userId)
	{
		// TODO Auto-generated method stub
		return groupDAO.getByUserId(userId);
	}

	

	

}
