package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IRoleDAO;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
@Repository("roleDAO")
public class RoleDAO extends BaseDaoImpl<AppRole, Long> implements IRoleDAO
{
	private static final Log logger = LogFactory.getLog(RoleDAO.class);
	@Override
	public List<AppRole> getByUserId(Long userId)
	{
		StringBuffer hql = new StringBuffer("select r from ");
		hql.append(AppGroup.class.getName());
		hql.append(" g ");
		hql.append(" inner join g.appRole r ");
		hql.append(" inner join g.appUser u ");
		hql.append(" where u.userId = :userId ");
		logger.info("Query : "+ hql.toString());
		Query query = getSession().createQuery(hql.toString());
		query.setLong("userId", userId);
		return query.list();
	}
	
	


}
