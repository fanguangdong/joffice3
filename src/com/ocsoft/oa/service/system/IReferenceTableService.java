package com.ocsoft.oa.service.system;

import java.util.Collection;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.ReferenceTable;

public interface IReferenceTableService extends
		IBaseService<ReferenceTable, Long>
{
	public ReferenceTable getByKey(String key);
	
	public Collection getRefList(String key);
}
