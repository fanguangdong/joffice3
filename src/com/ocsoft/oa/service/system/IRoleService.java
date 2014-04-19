package com.ocsoft.oa.service.system;

import java.util.List;
import java.util.Map;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.AppRole;

public interface IRoleService extends IBaseService<AppRole, Long>
{
	public void hasPermission(Long userId, int resSn, int actionType);
	public List<AppRole> getRolesByUId(Long uId);
	
	public void save(AppRole roleVO, String user);
	public void assign(AppRole roleVO, String user);
	public void saveAuth(Long roleId, Map nodes);
}
