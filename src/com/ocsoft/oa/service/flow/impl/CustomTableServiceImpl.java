package com.ocsoft.oa.service.flow.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.flow.ICustomTableDAO;
import com.ocsoft.oa.service.flow.ICustomTableService;
import com.ocsoft.oa.vo.form.CustomTable;
@Service("customTableService")
public class CustomTableServiceImpl extends BaseServiceImpl<CustomTable, Long>
		implements ICustomTableService
{

	private ICustomTableDAO customTableDAO;

	@Resource(name="customTableDAO")
	public void setDao(IBaseDao baseDao)
	{
		super.setDao(baseDao);
		customTableDAO = (ICustomTableDAO) baseDao;
	}
	
	

}
