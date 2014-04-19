package com.ocsoft.oa.vo.system;

import com.ocsoft.core.vo.BaseVO;

public class Client extends BaseVO
{
	private Long clientId;
	private String clientName;
	private String clientTitle;
	private String icon;
	
	public Long getClientId()
	{
		return clientId;
	}
	public void setClientId(Long clientId)
	{
		this.clientId = clientId;
	}
	public String getClientName()
	{
		return clientName;
	}
	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}
	public String getClientTitle()
	{
		return clientTitle;
	}
	public void setClientTitle(String clientTitle)
	{
		this.clientTitle = clientTitle;
	}
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	

}
