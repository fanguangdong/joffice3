package com.ocsoft.oa.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IActionDAO;
import com.ocsoft.oa.service.system.IActionService;
import com.ocsoft.oa.vo.system.Action;
@Service("actionService")
public class ActionServiceImpl extends BaseServiceImpl<Action, Long> implements IActionService
{

	private IActionDAO actionDAO;

	@Override
	public List<Action> getByModuleId(Long moduleId)
	{
		return actionDAO.getByModuleId(moduleId);
	}

	@Override
	public void removeByModuleId(Long moduleId)
	{
		actionDAO.removeByModuleId(moduleId);

	}

	@Override
	@Resource(name="actionDAO")
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		actionDAO = (IActionDAO)baseDao;
	}

}
