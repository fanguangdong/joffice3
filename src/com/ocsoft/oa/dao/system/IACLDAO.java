package com.ocsoft.oa.dao.system;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.ACL;
import com.ocsoft.oa.vo.system.AppRole;

public interface IACLDAO extends IBaseDao<ACL, Long>
{
	public void updatePermission(AppRole appRole, String resSn,int actionType, boolean status);
	public ACL getAcl(Long roleId, String resSn, int actionType);
	public List<ACL> getAcl(Long roleId, String resSn);
	public List<ACL> getACLByUid(Long uId, String resSn);
	public List<ACL> getACLByRoleId(Long roleId);
	public void removeByRoleId(Long roleId);
	
}
