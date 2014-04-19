package com.ocsoft.oa.service.system;

import java.util.List;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.Action;

public interface IActionService extends IBaseService<Action, Long>
{
	public List<Action> getByModuleId(Long moduleId);
	public void removeByModuleId(Long moduleId);
}
