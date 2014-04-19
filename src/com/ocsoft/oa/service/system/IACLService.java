package com.ocsoft.oa.service.system;

import java.util.List;
import java.util.Map;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.ACL;

public interface IACLService extends IBaseService<ACL, Long>
{
	public List<ACL> getACLByUid(Long uId, String resSn);
	public void updatePermission(Long roleId, String resSn, int actionType, boolean status);
	public boolean hasPermission(String user,String resSn,int actionType);
	public Map getACLByRoleId(Long roleId);
}
