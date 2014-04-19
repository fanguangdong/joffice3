package com.ocsoft.oa.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ocsoft.oa.tree.KendoTreeNode;

public class TreeUtil
{
	public static List getTree(List vos)
	{
		List res = new ArrayList();
		for(Iterator it = vos.iterator();it.hasNext();)
		{
			KendoTreeNode node = (KendoTreeNode)it.next();
			res.add(node.getTreeAttrs());
		}
		return res;
	}

}
