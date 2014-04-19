package com.ocsoft.oa.vo.system;

import java.util.List;

import com.ocsoft.core.vo.BaseVO;

public class Module extends BaseVO
{
	private Long moduleId;
	private String moduleName;
	private String title;
	private String icon;
	private Long parentId;
	private String resSn;
	//private Long clientId;
	private String defaultUrl;
	private Integer actions;
	private Integer isLeaf;
	private Integer showInMenu;
	private List<Module> children;
	
	public Integer getActions()
	{
		return actions;
	}
	public void setActions(Integer actions)
	{
		this.actions = actions;
	}
	public Long getModuleId()
	{
		return moduleId;
	}
	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}
	public String getModuleName()
	{
		return moduleName;
	}
	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}
	
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	public String getResSn()
	{
		return resSn;
	}
	public void setResSn(String resSn)
	{
		this.resSn = resSn;
	}
	
	
	public Integer getShowInMenu()
	{
		return showInMenu;
	}
	public void setShowInMenu(Integer showInMenu)
	{
		this.showInMenu = showInMenu;
	}
	
	public Integer getIsLeaf()
	{
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf)
	{
		this.isLeaf = isLeaf;
		if ((isLeaf != null) && (isLeaf.equals(Integer.valueOf(1)))) {
		} else if ((isLeaf != null) && (isLeaf.equals(Integer.valueOf(0)))) {
		} else {
		}
	}

	public String getIsParent()
	{
		if (this.isLeaf == null)
			return "true";

		return this.isLeaf.intValue() == 1 ? "false" : "true";
	}

	public void setIsParent(String isParent)
	{
		if ((isParent != null) && (isParent.equals("false")))
			this.isLeaf = Integer.valueOf(1);
		else if ((isParent != null) && (isParent.equals("true")))
			this.isLeaf = Integer.valueOf(0);
		else
			this.isLeaf = null;
	}
	public List<Module> getChildren()
	{
		return children;
	}
	public void setChildren(List<Module> children)
	{
		this.children = children;
	}
	public String getDefaultUrl()
	{
		return defaultUrl;
	}
	public void setDefaultUrl(String defaultUrl)
	{
		this.defaultUrl = defaultUrl;
	}
	
	
}
