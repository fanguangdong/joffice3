package com.ocsoft.oa.dao.flow.impl;

import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.flow.ICustomFormDAO;
import com.ocsoft.oa.vo.form.CustomForm;
@Repository("customFormDAO")
public class CustomFormDAOImpl extends BaseDaoImpl<CustomForm, Long> implements
		ICustomFormDAO
{

	

}
