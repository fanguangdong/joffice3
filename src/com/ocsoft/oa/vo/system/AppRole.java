package com.ocsoft.oa.vo.system;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ocsoft.core.vo.BaseVO;

public class AppRole extends BaseVO
{
	private Long roleId;
	private String roleName;
	private String userIds;
	@JsonIgnore
	private Set<AppGroup> groups;
	
	private Date editDate;
	private Date addDate;
	private String addUser;
	private String editUser;
	
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public Long getRoleId()
	{
		return roleId;
	}
	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}
	public String getRoleName()
	{
		return roleName;
	}
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	@JsonIgnore
	public Set<AppGroup> getGroups()
	{
		return groups;
	}
	
	private void setGroups(Set<AppGroup> groups)
	{
		this.groups = groups;
	}
	public void addGroup(AppGroup group)
	{
		this.getGroups().add(group);
	}
	
	public String getUserIds()
	{
		return userIds;
	}
	public void setUserIds(String userIds)
	{
		this.userIds = userIds;
	}
	
	
}
