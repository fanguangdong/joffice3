package com.ocsoft.oa.web;

import java.util.HashMap;

public class AjaxRtn extends HashMap
{
	
	public void setSuccess()
	{
		put("status", "1");
	}
	public void setFail()
	{
		put("status", 0);
	}
	
	public void setObject(String name, Object value)
	{
		put(name, value);
	}

}
