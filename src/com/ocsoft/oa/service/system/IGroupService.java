package com.ocsoft.oa.service.system;

import java.util.List;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;

public interface IGroupService extends IBaseService<AppGroup,Long>
{
	public List<AppUser> getUsersByRoleId(Long roleId);
	public List<AppRole> getRolesByUserId(Long userId);
	
	public List<AppGroup> getByRoleId(Long roleId);
	public List<AppGroup> getByUserId(Long userId);
}
