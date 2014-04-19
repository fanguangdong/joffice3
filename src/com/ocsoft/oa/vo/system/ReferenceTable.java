package com.ocsoft.oa.vo.system;

import com.ocsoft.core.vo.BaseVO;

public class ReferenceTable extends BaseVO
{
	private Long refId;
	private String refKey;
	private String refTable;
	private String textField;
	private String valueField;
	public Long getRefId()
	{
		return refId;
	}
	public void setRefId(Long refId)
	{
		this.refId = refId;
	}
	public String getRefKey()
	{
		return refKey;
	}
	public void setRefKey(String refKey)
	{
		this.refKey = refKey;
	}
	public String getRefTable()
	{
		return refTable;
	}
	public void setRefTable(String refTable)
	{
		this.refTable = refTable;
	}
	public String getTextField()
	{
		return textField;
	}
	public void setTextField(String textField)
	{
		this.textField = textField;
	}
	public String getValueField()
	{
		return valueField;
	}
	public void setValueField(String valueField)
	{
		this.valueField = valueField;
	}
	
	

}
