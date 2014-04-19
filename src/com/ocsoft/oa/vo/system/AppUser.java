package com.ocsoft.oa.vo.system;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ocsoft.core.vo.BaseVO;

public class AppUser extends BaseVO
{
	//account
	private Long userId;
	private String userName;
	private String userPwd;
	private boolean isLogin;
	private Date lastLoginTime;
	private boolean active;
	private Set<AppGroup> groups;
	
	private String firstName;
	private String lastName;
	private String fullName;
	private Date joinDate;
	
	private Long jobId;
	private Long deptId;
	
	private String sex;
	private String email;
	private String phone;
	private String address;
	private String province;
	private String city;
	private String zip;
	
	private String roleIds;
	
	
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	@JsonIgnore
	public Set<AppGroup> getGroups()
	{
		return groups;
	}
	public void setGroups(Set<AppGroup> groups)
	{
		this.groups = groups;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public boolean isLogin()
	{
		return isLogin;
	}
	public void setLogin(boolean isLogin)
	{
		this.isLogin = isLogin;
	}
	public Date getLastLoginTime()
	{
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	public Long getJobId()
	{
		return jobId;
	}
	public void setJobId(Long jobId)
	{
		this.jobId = jobId;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getProvince()
	{
		return province;
	}
	public void setProvince(String province)
	{
		this.province = province;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getZip()
	{
		return zip;
	}
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	public Long getDeptId()
	{
		return deptId;
	}
	public void setDeptId(Long deptId)
	{
		this.deptId = deptId;
	}
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public Date getJoinDate()
	{
		return joinDate;
	}
	public void setJoinDate(Date joinDate)
	{
		this.joinDate = joinDate;
	}
	public String getRoleIds()
	{
		return roleIds;
	}
	public void setRoleIds(String roleIds)
	{
		this.roleIds = roleIds;
	}
	public String getUserPwd()
	{
		return userPwd;
	}
	public void setUserPwd(String userPwd)
	{
		this.userPwd = userPwd;
	}
	
	

}
