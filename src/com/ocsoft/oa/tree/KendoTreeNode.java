package com.ocsoft.oa.tree;

import java.util.HashMap;
import java.util.Map;

import com.ocsoft.core.vo.BaseVO;

public abstract class KendoTreeNode extends BaseVO
{
	protected abstract Long getId();
	protected abstract String getName();
	protected abstract boolean hasChildren();
	protected abstract String getType();
	
	public Map getTreeAttrs()
	{
		Map attrs = new HashMap();
		attrs.put("id", getId());
		attrs.put("name", getName());
		attrs.put("hasChildren", hasChildren());
		attrs.put("nodeType", getType());
		return attrs;
	}
}
