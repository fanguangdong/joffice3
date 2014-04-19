package com.ocsoft.oa.vo.flow;

import java.util.Date;

import com.ocsoft.core.vo.BaseVO;

public class FlowDefinition extends BaseVO
{
	private Long flowDefId;
	private String defKey;
	private String defDesc;
	private String subject;
	private Integer versionNo;
	private String defXml;
	private Long defTypeId;
	private Long status;
	private String deployId;
	private String processDefId;
	
	private Date editDate;
	private Date addDate;
	private String addUser;
	private String editUser;
	
	
	public Long getFlowDefId()
	{
		return flowDefId;
	}
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
	public void setFlowDefId(Long flowDefId)
	{
		this.flowDefId = flowDefId;
	}
	public String getDefKey()
	{
		return defKey;
	}
	public void setDefKey(String defKey)
	{
		this.defKey = defKey;
	}
	public String getDefDesc()
	{
		return defDesc;
	}
	public void setDefDesc(String defDesc)
	{
		this.defDesc = defDesc;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public Integer getVersionNo()
	{
		return versionNo;
	}
	public void setVersionNo(Integer versionNo)
	{
		this.versionNo = versionNo;
	}
	public String getDefXml()
	{
		return defXml;
	}
	public void setDefXml(String defXml)
	{
		this.defXml = defXml;
	}
	public Long getDefTypeId()
	{
		return defTypeId;
	}
	public void setDefTypeId(Long defTypeId)
	{
		this.defTypeId = defTypeId;
	}
	public Long getStatus()
	{
		return status;
	}
	public void setStatus(Long status)
	{
		this.status = status;
	}
	public String getDeployId()
	{
		return deployId;
	}
	public void setDeployId(String deployId)
	{
		this.deployId = deployId;
	}
	public String getProcessDefId()
	{
		return processDefId;
	}
	public void setProcessDefId(String processDefId)
	{
		this.processDefId = processDefId;
	}
	
	
	
	

}
