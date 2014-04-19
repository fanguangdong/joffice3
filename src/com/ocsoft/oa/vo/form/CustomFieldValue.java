package com.ocsoft.oa.vo.form;

import com.ocsoft.core.vo.BaseVO;

public class CustomFieldValue extends BaseVO
{
	private Long fieldValueId;
	private String fieldValue;
	private CustomField customField;
	public Long getFieldValueId()
	{
		return fieldValueId;
	}
	public void setFieldValueId(Long fieldValueId)
	{
		this.fieldValueId = fieldValueId;
	}
	
	public String getFieldValue()
	{
		return fieldValue;
	}
	public void setFieldValue(String fieldValue)
	{
		this.fieldValue = fieldValue;
	}
	public CustomField getCustomField()
	{
		return customField;
	}
	public void setCustomField(CustomField customField)
	{
		this.customField = customField;
	}
	
	
	
	
	
}
