package com.ocsoft.oa.tree;

import java.util.HashMap;
import java.util.Map;

import com.ocsoft.core.vo.BaseVO;

public abstract class ZTreeNode extends BaseVO
{
	protected abstract Long getId();
	protected abstract String getName();
	protected abstract boolean isParent();
	protected abstract String getType();
	
	public Map getTreeAttrs()
	{
		Map attrs = new HashMap();
		attrs.put("id", getId());
		attrs.put("name", getName());
		attrs.put("isParent", isParent());
		attrs.put("nodeType", getType());
		return attrs;
	}

}
