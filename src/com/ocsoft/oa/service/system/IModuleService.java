package com.ocsoft.oa.service.system;

import java.util.List;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.Client;
import com.ocsoft.oa.vo.system.Module;

public interface IModuleService extends IBaseService<Module, Long>
{
	public List<Module> getByParentId(Long pId);
	public List<Module> getByParentId(Long pId, boolean includeChildren);
	public Module getParentModuleByParentId(long parentId);
	public List<Module> getAdminMenu(Long pId,String user);
	public Module getByResSn(String resSn);
	//public List<Module> getByClientId(Long clientId);
	//public List<Module> getByCidAndPid(Long clientId, Long parentId);

}
