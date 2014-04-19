package com.ocsoft.oa.dao.system;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.Position;

public interface IPositionDAO extends IBaseDao<Position, Long>
{
	public List<Position> getChildByParentId(long parentId);
	public Integer getChildCountByParentId(long parentId);
}
