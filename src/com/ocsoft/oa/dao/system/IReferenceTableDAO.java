package com.ocsoft.oa.dao.system;

import java.util.Collection;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.ReferenceTable;

public interface IReferenceTableDAO extends IBaseDao<ReferenceTable, Long>
{
	public ReferenceTable getByKey(String key, Long excludeId);
	public Collection getRefList(String key);
}
