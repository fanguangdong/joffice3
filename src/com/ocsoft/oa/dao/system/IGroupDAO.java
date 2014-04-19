package com.ocsoft.oa.dao.system;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;

public interface IGroupDAO extends IBaseDao<AppGroup, Long>
{
	public List<AppUser> getUsersByRoleId(Long roleId);
	public List<AppRole> getRolesByUserId(Long userId);
	
	public List<AppGroup> getByRoleId(Long roleId);
	public List<AppGroup> getByUserId(Long userId);
	
	public void removeByUserId(Long userId);
	public void removeByRoleId(Long roleId);
}
