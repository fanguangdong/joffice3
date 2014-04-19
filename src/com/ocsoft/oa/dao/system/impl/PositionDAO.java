package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IPositionDAO;
import com.ocsoft.oa.vo.system.Position;
@Repository("positionDAO")
public class PositionDAO extends BaseDaoImpl<Position, Long> implements IPositionDAO
{

	

	@Override
	public List<Position> getChildByParentId(long parentId)
	{
		return getSession().createCriteria(entityClass).add(Restrictions.eq("parentId", parentId)).list();
	}

	@Override
	public Integer getChildCountByParentId(long parentId)
	{
		List res = getChildByParentId(parentId);
		if(res==null)
			return 0;
		return res.size();
	}

}
