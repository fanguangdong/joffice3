package com.ocsoft.oa.service.system;

import java.util.List;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.Position;

public interface IPositionService extends IBaseService<Position, Long>
{
	public List<Position> getChildByParentId(long parentId);
	public Integer getChildCountByParentId(long parentId);
	public Position getParentPositionByParentId(long parentId);
	public List<Position> getChildByParentId(long parentId, boolean includeChidlren);
}
