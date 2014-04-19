package com.ocsoft.oa.tree;

import java.util.ArrayList;
import java.util.List;

import com.ocsoft.core.vo.BaseVO;

public class OceTree
{
	private Object data;
	private TreeNodeMapper mapper;
	public Object toTree(TreeNodeMapper mapper)
	{
		this.mapper = mapper;
		Object tree = null;
		if(data instanceof BaseVO)
		{
			tree = processSignle((BaseVO)data);
		}
		if(data instanceof List)
		{
			tree = processList((List)data);
		}
		return tree;
	}
	
	public KendoTreeNode processSignle(BaseVO vo)
	{
		return mapper.mapNode(vo);
	}
	
	public List processList(List data)
	{
		List nodes = new ArrayList();
		
		for(Object d : (List)data)
		{
			nodes.add(this.processSignle((BaseVO)d));
		}
		return nodes;
	}
	
	
	public Object getData()
	{
		return data;
	}
	public void setData(Object data)
	{
		this.data = data;
	}
	
	
	

}
