package com.ocsoft.oa.dao.system;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.Client;
import com.ocsoft.oa.vo.system.Module;

public interface IModuleDAO extends IBaseDao<Module,Long>
{
	public List<Module> getByPId(Long pId);
	public List<Module> getByClientId(Long clientId);
	public List<Module> getByCidAndPid(Long clientId, Long parentId);
	public Module getByResSn(String resSn);

}
