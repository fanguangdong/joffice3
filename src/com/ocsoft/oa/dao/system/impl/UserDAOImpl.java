package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IUserDAO;
import com.ocsoft.oa.vo.system.AppUser;
@Repository("userDAO")
public class UserDAOImpl extends BaseDaoImpl<AppUser, Long> implements IUserDAO
{

	@Override
	public AppUser getByUserName(String userName)
	{
		List<AppUser> list = getSession().createCriteria(entityClass)
										 .add(Restrictions.eq("userName", userName))
										 .list();
		if(list!=null && list.size()>0)
		{
			return list.iterator().next();
		}
		return null;
	}

}
