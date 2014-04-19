package com.ocsoft.oa.dao.system;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.Action;

public interface IActionDAO extends IBaseDao<Action, Long>
{
	public List<Action> getByModuleId(Long moduleId);
	public void removeByModuleId(Long moduleId);

}
