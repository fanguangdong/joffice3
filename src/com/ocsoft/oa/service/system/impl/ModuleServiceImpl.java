package com.ocsoft.oa.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IActionDAO;
import com.ocsoft.oa.dao.system.IModuleDAO;
import com.ocsoft.oa.service.system.IACLService;
import com.ocsoft.oa.service.system.IModuleService;
import com.ocsoft.oa.vo.system.Module;
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module, Long> implements IModuleService
{

	
	private IModuleDAO moduleDAO;
	private IActionDAO actionDAO;
	private IACLService aclService;
	public List<Module> getByParentId(Long pId)
	{
		return moduleDAO.getByPId(pId);
	}
	@Override
	public List<Module> getByParentId(Long pId, boolean includeChildren)
	{
		List<Module> children = moduleDAO.getByPId(pId);
		if(includeChildren)
		{
			if(children!=null && children.size()>0)
			{
				for(Module module : children)
				{
					setChildren(module,null);
				}
			}
			
		}
		return children;
	}
	
	private void setChildren(Module module, String user)
	{
		List<Module> children = null;
		
		if(user != null)
		{
			List<Criterion> crs = new ArrayList<Criterion>();
			crs.add(Restrictions.eq("parentId", module.getModuleId()));
			crs.add(Restrictions.eq("showInMenu", 1));
			List<Module> originalChildren = moduleDAO.getByCriteria(crs);
			
			
			for(Module m : originalChildren)
			{
				if(aclService.hasPermission(user, m.getResSn(), 1))
				{
					if(children == null)
					{
						children = new ArrayList<Module>();
					}
					children.add(m);
				}
			}
		}
		else
		{
			children = moduleDAO.getByPId(module.getModuleId());
		}
		
		
		if(children!=null && children.size()>0)
		{
			module.setChildren(children);
			module.setIsParent("true");
			for(Module child : children)
			{
				
				setChildren(child,user);
			}
		}
		else
		{
			module.setIsParent("false");
		}
	}
	
	
	public Module getParentModuleByParentId(long parentId)
	{
		Module parent = moduleDAO.get(parentId);
		if(parentId == 0L || parent == null)
		{
			parent = new Module();
			parent.setModuleName("资源");
			parent.setModuleId(0L);
			parent.setParentId(-1L);
			
		}
		
		return parent;
	}
	
	public List<Module> getAdminMenu(Long pId,String user)
	{
		List<Criterion> crs = new ArrayList<Criterion>();
		crs.add(Restrictions.eq("parentId", pId));
		crs.add(Restrictions.eq("showInMenu", 1));
		
		List<Module> children = moduleDAO.getByCriteria(crs);
		List availChildren = new ArrayList();
		for(Module m : children)
		{
			if(aclService.hasPermission(user, m.getResSn(), 1))
			{
				availChildren.add(m);
			}
		}
		
		if(availChildren!=null && availChildren.size()>0)
		{
			for(Module module : children)
			{
				setChildren(module,user);
			}
		}
		
		return availChildren;
	}
	
	public void updateIsParent(Module module)
	{
		module.setIgnoreUserAudit(true);
		if(module == null)
			return;
		long modId = module.getModuleId();
		long parentId = module.getParentId();
		
		module = moduleDAO.get(modId);
		Module parent = moduleDAO.get(parentId);
		if(module != null)
		{
			module.setIgnoreUserAudit(true);
			List children = moduleDAO.getByPId(modId);
			if(children!=null && children.size()>0)
			{
				module.setIsParent("true");
			}
			else
			{
				module.setIsParent("false");
			}
			moduleDAO.save(module);
		}
		
		if(parent!=null)
		{
			parent.setIgnoreUserAudit(true);
			List children = moduleDAO.getByPId(parentId);
			if(children!=null && children.size()>0)
			{
				parent.setIsParent("true");
			}
			else
			{
				parent.setIsParent("false");
			}
			moduleDAO.save(parent);
			
		}
	}
	
	
	

	@Resource(name="moduleDAO")
	public void setDao(IBaseDao baseDao)
	{
		super.setDao(baseDao);
		moduleDAO = (IModuleDAO) baseDao;
	}
	public IActionDAO getActionDAO()
	{
		return actionDAO;
	}
	public void setActionDAO(IActionDAO actionDAO)
	{
		this.actionDAO = actionDAO;
	}
	@Override
	public Module save(Module v)
	{
		// TODO Auto-generated method stub
		super.save(v);
		this.updateIsParent(v);
		return v;
	}
	@Override
	public void saveOrUpdate(Module v)
	{
		// TODO Auto-generated method stub
		super.saveOrUpdate(v);
		this.updateIsParent(v);
	}
	@Override
	public void remove(Long paramPK)
	{
		// TODO Auto-generated method stub
		super.remove(paramPK);
		this.updateIsParent(get(paramPK));
	}
	@Override
	public void remove(Module v)
	{
		// TODO Auto-generated method stub
		super.remove(v);
		this.updateIsParent(v);
	}
	public IACLService getAclService()
	{
		return aclService;
	}
	public void setAclService(IACLService aclService)
	{
		this.aclService = aclService;
	}
	@Override
	public Module getByResSn(String resSn)
	{
		// TODO Auto-generated method stub
		return moduleDAO.getByResSn(resSn);
	}


	
	
	



	
	
	
	
	
	
	

	

}
