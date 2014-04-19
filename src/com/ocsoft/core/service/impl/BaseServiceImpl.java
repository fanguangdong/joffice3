package com.ocsoft.core.service.impl;

import java.util.List;
import java.util.Map;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.IBaseService;
import com.ocsoft.core.web.JQGridBean;
import com.ocsoft.oa.vo.flow.FlowDefinition;
import com.ocsoft.oa.vo.form.CustomField;
import com.ocsoft.oa.vo.form.CustomForm;
import com.ocsoft.oa.vo.system.Module;
import com.ocsoft.oa.vo.system.Position;
import com.ocsoft.oa.vo.system.ReferenceTable;

public class BaseServiceImpl<T1, T2> implements IBaseService<T1, T2>{

	private IBaseDao baseDao;
	
	public void setDao(IBaseDao baseDao) {
		// TODO Auto-generated method stub
		this.baseDao = baseDao;
	}

	@Override
	public JQGridBean getPageList(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T1 get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	@Override
	public List<T1> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T1 save(T1 v) {
		// TODO Auto-generated method stub
		baseDao.save(v);
		return null;
	}

	@Override
	public void saveOrUpdate(T1 m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Position id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Module id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Object id) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	

}
