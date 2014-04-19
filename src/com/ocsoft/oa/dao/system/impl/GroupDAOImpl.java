package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IGroupDAO;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
@Repository("groupDAO")
public class GroupDAOImpl extends BaseDaoImpl<AppGroup, Long> implements IGroupDAO
{
	private static final Log logger = LogFactory.getLog(GroupDAOImpl.class);
	@Override
	public List<AppUser> getUsersByRoleId(Long roleId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" select g.appUser from ")
		.append(this.entityClass.getName()).append(" g ")
		.append(" inner join g.appRole r ")
		.append(" where r.roleId=:roleId");
		logger.debug("Query : "+ hql);
		Query query = getSession().createQuery(hql.toString());
		query.setLong("roleId", roleId);
		return query.list();
	}

	@Override
	public List<AppRole> getRolesByUserId(Long userId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" select g.appRole from ")
		.append(this.entityClass.getName()).append(" g ")
		.append(" inner join g.appUser u ")
		.append(" where u.userId=:userId");
		logger.debug("Query : "+ hql);
		Query query = getSession().createQuery(hql.toString());
		query.setLong("userId",userId);
		return query.list();
	}

	@Override
	public List<AppGroup> getByRoleId(Long roleId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" select g from ")
		.append(this.entityClass.getName()).append(" g ")
		.append(" inner join g.appRole r ")
		.append(" where r.roleId=:roleId");
		logger.debug("Query : "+ hql);
		Query query = getSession().createQuery(hql.toString());
		query.setLong("roleId",roleId);
		return query.list();
	}

	@Override
	public List<AppGroup> getByUserId(Long userId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" select g from ")
		.append(this.entityClass.getName()).append(" g ")
		.append(" inner join g.appUser u ")
		.append(" where u.userId=:userId");
		logger.debug("Query : "+ hql);
		Query query = getSession().createQuery(hql.toString());
		query.setLong("userId",userId);
		return query.list();
	}

	@Override
	public void removeByUserId(Long userId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" delete ")
		.append(this.entityClass.getName()).append(" g ")
		.append(" where g.appUser.userId=:userId");
		Query query = getSession().createQuery(hql.toString());
		query.setLong("userId",userId);
		query.executeUpdate();
	}

	@Override
	public void removeByRoleId(Long roleId)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" delete ")
		.append(this.entityClass.getName()).append(" g ")
		.append(" where g.appRole.roleId=:roleId");
		Query query = getSession().createQuery(hql.toString());
		query.setLong("roleId",roleId);
		query.executeUpdate();
	}
	
	

	

	

}
