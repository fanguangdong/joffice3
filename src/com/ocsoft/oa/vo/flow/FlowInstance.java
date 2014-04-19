package com.ocsoft.oa.vo.flow;

import java.util.Date;

import com.ocsoft.core.vo.BaseVO;

public class FlowInstance extends BaseVO
{
	private Long flowInsId;
	private Long flowDefId;
	private String actInstId;
	private String actDefId;
	private String subject;
	private Long creatorId;
	private String creator;
	private String busDescp;
	private Short status;
	private String businessKey;
	
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
	public Long getFlowInsId()
	{
		return flowInsId;
	}
	public void setFlowInsId(Long flowInsId)
	{
		this.flowInsId = flowInsId;
	}
	public Long getFlowDefId()
	{
		return flowDefId;
	}
	public void setFlowDefId(Long flowDefId)
	{
		this.flowDefId = flowDefId;
	}
	
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public Long getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(Long creatorId)
	{
		this.creatorId = creatorId;
	}
	public String getCreator()
	{
		return creator;
	}
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	public String getBusDescp()
	{
		return busDescp;
	}
	public void setBusDescp(String busDescp)
	{
		this.busDescp = busDescp;
	}
	public Short getStatus()
	{
		return status;
	}
	public void setStatus(Short status)
	{
		this.status = status;
	}
	public String getActInstId()
	{
		return actInstId;
	}
	public void setActInstId(String actInstId)
	{
		this.actInstId = actInstId;
	}
	public String getActDefId()
	{
		return actDefId;
	}
	public void setActDefId(String actDefId)
	{
		this.actDefId = actDefId;
	}
	public String getBusinessKey()
	{
		return businessKey;
	}
	public void setBusinessKey(String businessKey)
	{
		this.businessKey = businessKey;
	}
	
	
	
	

}
