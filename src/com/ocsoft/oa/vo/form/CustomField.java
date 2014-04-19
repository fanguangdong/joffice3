package com.ocsoft.oa.vo.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ocsoft.core.vo.BaseVO;

public class CustomField extends BaseVO
{
	private Long fieldId;
	private String fieldName;
	private String fieldLabel;
	private Integer fieldType;
	private Integer fieldSize;
	private Integer maxSize;
	private Boolean isRequired;
	private String defaultValue;
	private Boolean isPK;
	private CustomFieldValue fieldValue;
	
	@JsonIgnore
	private CustomForm customForm;
	//private CustomTable customTable;

	public Long getFieldId()
	{
		return fieldId;
	}

	public void setFieldId(Long fieldId)
	{
		this.fieldId = fieldId;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFieldLabel()
	{
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel)
	{
		this.fieldLabel = fieldLabel;
	}

	public Integer getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(Integer fieldType)
	{
		this.fieldType = fieldType;
	}

	public Integer getFieldSize()
	{
		return fieldSize;
	}

	public void setFieldSize(Integer fieldSize)
	{
		this.fieldSize = fieldSize;
	}

	public Integer getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(Integer maxSize)
	{
		this.maxSize = maxSize;
	}

	public Boolean getIsRequired()
	{
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired)
	{
		this.isRequired = isRequired;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public Boolean getIsPK()
	{
		return isPK;
	}

	public void setIsPK(Boolean isPK)
	{
		this.isPK = isPK;
	}
/*
	public CustomTable getCustomTable()
	{
		return customTable;
	}

	public void setCustomTable(CustomTable customTable)
	{
		this.customTable = customTable;
	}
	
	*/

	public CustomForm getCustomForm()
	{
		return customForm;
	}

	public void setCustomForm(CustomForm customForm)
	{
		this.customForm = customForm;
	}
	
}
