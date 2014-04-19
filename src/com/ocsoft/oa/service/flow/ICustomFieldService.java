package com.ocsoft.oa.service.flow;

import java.util.List;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.form.CustomField;

public interface ICustomFieldService extends IBaseService<CustomField, Long>
{
	public List<CustomField> getByTableId(Long tableId);
	public List<CustomField> getByFormId(Long formId);

}
