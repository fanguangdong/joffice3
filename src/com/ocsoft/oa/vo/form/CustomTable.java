package com.ocsoft.oa.vo.form;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ocsoft.core.vo.BaseVO;

public class CustomTable extends BaseVO
{
	private Long tableId;
	private String tableName;
	@JsonIgnore
	private CustomForm customForm;
	private Set<CustomField> fields = new HashSet<CustomField>();
	public Long getTableId()
	{
		return tableId;
	}
	public void setTableId(Long tableId)
	{
		this.tableId = tableId;
	}
	public String getTableName()
	{
		return tableName;
	}
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
	public CustomForm getCustomForm()
	{
		return customForm;
	}
	public void setCustomForm(CustomForm customForm)
	{
		this.customForm = customForm;
	}
	public Set<CustomField> getFields()
	{
		return fields;
	}
	public void setFields(Set<CustomField> fields)
	{
		this.fields = fields;
	}
	
	
	
}
