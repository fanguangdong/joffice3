package com.ocsoft.oa.dao.system;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.AppRole;

public interface IRoleDAO extends IBaseDao<AppRole, Long>
{
	public List<AppRole> getByUserId(Long userId);

}
