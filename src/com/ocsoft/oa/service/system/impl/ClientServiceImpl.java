package com.ocsoft.oa.service.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.service.system.IClientService;
import com.ocsoft.oa.vo.system.Client;
@Service("clientService")
public class ClientServiceImpl extends BaseServiceImpl<Client, Long> implements IClientService
{
	@Override
	@Resource(name="clientDAO")
	public void setDao(IBaseDao baseDao)
	{
		super.setDao(baseDao);
	}

	

	
}
