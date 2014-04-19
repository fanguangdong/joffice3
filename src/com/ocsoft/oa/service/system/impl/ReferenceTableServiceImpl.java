package com.ocsoft.oa.service.system.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IReferenceTableDAO;
import com.ocsoft.oa.service.system.IReferenceTableService;
import com.ocsoft.oa.vo.system.ReferenceTable;
@Service("refTableService")
public class ReferenceTableServiceImpl extends
		BaseServiceImpl<ReferenceTable, Long> implements IReferenceTableService
{

	private IReferenceTableDAO refTableDAO;

	@Override
	public ReferenceTable getByKey(String key)
	{
		return refTableDAO.getByKey(key,null);
	}

	
	
	@Override
	public ReferenceTable save(ReferenceTable refTable) 
	{
		String refKey = refTable.getRefKey();
		Long refId = refTable.getRefId();
		ReferenceTable existRecord = null;
		if(refId!=null)
		{
			existRecord = refTableDAO.getByKey(refKey, refId);
		}
		else
		{
			existRecord = refTableDAO.getByKey(refKey, null);
		}
		if(existRecord!=null)
		{
			return null;
		}
		return super.save(refTable);
	}
	
	
	
	@Override
	public Collection getRefList(String key)
	{
		
		return refTableDAO.getRefList(key);
	}

	
	
	
	@Override
	@Resource(name="refTableDAO")
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		refTableDAO = (IReferenceTableDAO) baseDao;
	}

	public IReferenceTableDAO getRefTableDAO()
	{
		return refTableDAO;
	}

	public void setRefTableDAO(IReferenceTableDAO refTableDAO)
	{
		this.refTableDAO = refTableDAO;
	}



	










	

}
