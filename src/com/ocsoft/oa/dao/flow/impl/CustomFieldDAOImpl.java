package com.ocsoft.oa.dao.flow.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.flow.ICustomFieldDAO;
import com.ocsoft.oa.vo.form.CustomField;
@Repository("customFieldDAO")
public class CustomFieldDAOImpl extends BaseDaoImpl<CustomField, Long> implements
		ICustomFieldDAO
{

	@Override
	public List<CustomField> getByTableId(Long tableId)
	{
		return getSession().createCriteria(this.entityClass.getName())
				.add(Restrictions.eq("customTable.tableId", tableId))
				.list();
	}

	@Override
	public List<CustomField> getByFormId(Long formId)
	{
		return getSession().createCriteria(this.entityClass.getName())
				.add(Restrictions.eq("customForm.formId", formId))
				.list();
	}

	
}
