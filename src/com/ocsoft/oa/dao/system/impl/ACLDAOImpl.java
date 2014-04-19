package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IACLDAO;
import com.ocsoft.oa.vo.system.ACL;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;
@Repository("aclDAO")
public class ACLDAOImpl extends BaseDaoImpl<ACL, Long> implements IACLDAO
{
	private static final Log logger = LogFactory.getLog(ACLDAOImpl.class);
	@Override
	public void updatePermission(AppRole appRole, String resSn, int actionType, boolean status)
	{
		ACL acl = this.getAcl(appRole.getRoleId(), resSn, actionType);
		if(acl!= null )
		{
			acl = new ACL();
			acl.setAppRole(appRole);
			acl.setResSn(resSn);
			acl.setActionType(actionType);
		}
		acl.setStatus(status == true ? 1 : 0);
		getSession().save(acl);
	}

	@Override
	public ACL getAcl(Long roleId, String resSn, int actionType)
	{
		String hql = this.HQL_LIST_ALL+" acl where acl.roleId=:roleId and acl.resSn=:resSn and acl.actionType=:actionType";
		Query query = getSession().createQuery(hql);
		query.setLong("roleId", roleId);
		query.setString("resSn", resSn);
		query.setInteger("actionType", actionType);
		List<ACL> acls = query.list();
		if(acls!= null && acls.size()>0)
			return acls.iterator().next();
		return null;
	}

	@Override
	public List<ACL> getAcl(Long roleId, String resSn)
	{
		String hql = HQL_LIST_ALL+" acl where acl.appRole.roleId=:roleId and resSn=:resSn";
		Query query = getSession().createQuery(hql);
		query.setLong("roleId", roleId);
		query.setString("resSn", resSn);
		return query.list();
	}

	@Override
	public List<ACL> getACLByUid(Long uId, String resSn)
	{
		StringBuffer hql = new StringBuffer("select acl from ");
		hql.append(ACL.class.getName());
		hql.append(" acl ");
		hql.append(" inner join acl.appRole r  ");
		hql.append(" inner join r.groups g");
		hql.append(" inner join g.users u");
		hql.append(" where acl.resSn = :resSn and u.userId = :uId");
		logger.debug("Query : "+ hql.toString());
		Query query = getSession().createQuery(hql.toString());
		query.setString("resSn", resSn);
		query.setLong("uId", uId);
		return query.list();
	}
	
	public List<ACL> getACLByRoleId(Long roleId)
	{
		StringBuffer hql = new StringBuffer("select acl from ");
		hql.append(ACL.class.getName());
		hql.append(" acl ");
		hql.append(" where acl.appRole.id =:roleId");
		logger.debug("Query : "+ hql.toString());
		Query query = getSession().createQuery(hql.toString());
		query.setLong("roleId", roleId);
		return query.list();
	}

	@Override
	public void removeByRoleId(Long roleId)
	{
		StringBuffer hql = new StringBuffer("delete  ");
		hql.append(ACL.class.getName());
		hql.append(" acl ");
		hql.append(" where acl.appRole.id =:roleId");
		logger.debug("Query : "+ hql.toString());
		Query query = getSession().createQuery(hql.toString());
		query.setLong("roleId", roleId);
		query.executeUpdate();
		
	}
	
	
	
	
	
	
	
	
	

}
