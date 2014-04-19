package com.ocsoft.oa.dao.flow;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.form.CustomField;

public interface ICustomFieldDAO extends IBaseDao<CustomField, Long>
{
	public List<CustomField> getByTableId(Long tableId);
	public List<CustomField> getByFormId(Long formId);
}
