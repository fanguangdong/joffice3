package com.ocsoft.oa.service.flow.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.flow.ICustomFormDAO;
import com.ocsoft.oa.service.flow.ICustomFormService;
import com.ocsoft.oa.vo.form.CustomForm;
@Service("customFormService")
public class CustomFormServiceImpl extends BaseServiceImpl<CustomForm, Long>
		implements ICustomFormService
{

	private ICustomFormDAO customFormDAO;
	@Resource(name="customFormDAO")
	public void setDao(IBaseDao baseDao)
	{
		super.setDao(baseDao);
		customFormDAO = (ICustomFormDAO) baseDao;
	}
	@Override
	public CustomForm save(CustomForm v)
	{
		CustomForm po = save(v);
		
		return po;
	}

	
	
}
