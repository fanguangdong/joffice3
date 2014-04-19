package com.ocsoft.core.service;

import java.util.List;
import java.util.Map;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.web.JQGridBean;
import com.ocsoft.oa.vo.flow.FlowDefinition;
import com.ocsoft.oa.vo.form.CustomField;
import com.ocsoft.oa.vo.form.CustomForm;
import com.ocsoft.oa.vo.system.Module;
import com.ocsoft.oa.vo.system.Position;

public interface IBaseService<T1, T2> {
	
	public JQGridBean getPageList(Map<String, String> params);

	public void setDao(IBaseDao baseDao);

	public T1 save(T1 v);
	
	public List<T1> getAll();
	
	public T1 get(Long id);
	
	public void saveOrUpdate(T1 m);
	
	public void remove(Object id);

	void remove(Position id);

	void remove(Long id);

	void remove(Module id);
	
	
}
