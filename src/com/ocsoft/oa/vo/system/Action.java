package com.ocsoft.oa.vo.system;

import java.util.Map;

import com.ocsoft.oa.tree.KendoTreeNode;

public class Action extends KendoTreeNode
{
	private Long actionId;
	private Integer actionTypeCode;
	private String actionTypeDesc;
	private Long moduleId;
	
	private Integer resSn;
	
	private boolean isCheck;
	
	
	public Integer getActionTypeCode()
	{
		return actionTypeCode;
	}
	public void setActionTypeCode(Integer actionTypeCode)
	{
		this.actionTypeCode = actionTypeCode;
	}
	public String getActionTypeDesc()
	{
		return actionTypeDesc;
	}
	public void setActionTypeDesc(String actionTypeDesc)
	{
		this.actionTypeDesc = actionTypeDesc;
	}
	public Long getModuleId()
	{
		return moduleId;
	}
	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}
	public Long getActionId()
	{
		return actionId;
	}
	public void setActionId(Long actionId)
	{
		this.actionId = actionId;
	}
	@Override
	protected Long getId()
	{
		// TODO Auto-generated method stub
		return getActionId();
	}
	@Override
	protected String getName()
	{
		// TODO Auto-generated method stub
		return actionTypeDesc;
	}
	@Override
	protected boolean hasChildren()
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected String getType()
	{
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public Map getTreeAttrs()
	{
		Map attrs = super.getTreeAttrs();
		attrs.put("mid", getModuleId());
		attrs.put("actionTypeCode", actionTypeCode);
		attrs.put("actionTypeDesc", actionTypeDesc);
		attrs.put("resSn", getResSn());
		return attrs;
	}
	public boolean isCheck()
	{
		return isCheck;
	}
	public void setCheck(boolean isCheck)
	{
		this.isCheck = isCheck;
	}
	public Integer getResSn()
	{
		return resSn;
	}
	public void setResSn(Integer resSn)
	{
		this.resSn = resSn;
	}
	
	

}
