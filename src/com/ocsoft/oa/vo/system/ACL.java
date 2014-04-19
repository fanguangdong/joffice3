package com.ocsoft.oa.vo.system;

import java.io.Serializable;

import com.ocsoft.core.vo.BaseVO;

public class ACL extends BaseVO implements Serializable
{
	private Long aclId;
	private AppRole appRole;
	private String resSn;
	private int actionType;
	private int status;
	public Long getAclId()
	{
		return aclId;
	}
	public void setAclId(Long aclId)
	{
		this.aclId = aclId;
	}
	
	public String getResSn()
	{
		return resSn;
	}
	public void setResSn(String resSn)
	{
		this.resSn = resSn;
	}
	public int getActionType()
	{
		return actionType;
	}
	public void setActionType(int actionType)
	{
		this.actionType = actionType;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public AppRole getAppRole()
	{
		return appRole;
	}
	public void setAppRole(AppRole appRole)
	{
		this.appRole = appRole;
	}
	
}
