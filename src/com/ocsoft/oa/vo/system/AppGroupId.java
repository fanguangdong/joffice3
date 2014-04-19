package com.ocsoft.oa.vo.system;

import java.io.Serializable;

public class AppGroupId implements Serializable
{
	private AppUser appUser;
	private AppRole appRole;
	public AppUser getAppUser()
	{
		return appUser;
	}
	public void setAppUser(AppUser appUser)
	{
		this.appUser = appUser;
	}
	public AppRole getAppRole()
	{
		return appRole;
	}
	public void setAppRole(AppRole appRole)
	{
		this.appRole = appRole;
	}
	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return this.appRole.hashCode() ^ this.appUser.hashCode();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof AppGroup)
		{
			return this.getAppUser().equals(((AppGroupId) obj).getAppUser()) 
				&& this.getAppRole().equals(((AppGroupId) obj).getAppRole());
					
		}
		return false;
	}
}
