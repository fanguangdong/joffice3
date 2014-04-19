package com.ocsoft.oa.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.system.IPositionDAO;
import com.ocsoft.oa.service.system.IPositionService;
import com.ocsoft.oa.vo.system.Position;

@Service("positionService")
public class PositionServiceImpl extends BaseServiceImpl<Position, Long>
		implements IPositionService
{
	private IPositionDAO positionDAO;

	@Override
	public List<Position> getChildByParentId(long parentId)
	{
		return positionDAO.getChildByParentId(parentId);
	}
	
	public List<Position> getChildByParentId(long parentId, boolean includeChidlren)
	{
		List<Position> chidlren = this.getChildByParentId(parentId);
		if(includeChidlren)
		{
			for(Position p : chidlren)
			{
				this.setChildren(p);
			}
		}
		return chidlren;
	}
	
	private void setChildren(Position position)
	{
		List<Position> children = this.getChildByParentId(position.getPosId());
		if(children!=null && children.size()>0)
		{
			position.setChildren(children);
			position.setIsParent("true");
			for(Position p : children)
			{
				this.setChildren(p);
			}
		}
		else
		{
			position.setIsParent("false");
		}
	}

	@Override
	public Integer getChildCountByParentId(long parentId)
	{
		return positionDAO.getChildCountByParentId(parentId);
	}

	public Position getParentPositionByParentId(long parentId)
	{
		Position parent = (Position) this.positionDAO.get(Long
				.valueOf(parentId));
		if ((parentId == 0L) || (parent == null)) {
			parent = new Position();

			parent.setPosId(Long.valueOf(0L));
			parent.setDepth(Integer.valueOf(0));
			parent.setNodePath("0.");
			parent.setParentId(Long.valueOf(-1L));
			parent.setSn(Short.valueOf("0"));

			parent.setPosName("岗位");
			parent.setPosDesc("岗位");

			return parent;
		}
		return parent;
	}

	@Resource(name = "positionDAO")
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		positionDAO = (IPositionDAO) baseDao;
	}

	@Override
	public Position save(Position v)
	{
		super.save(v);
		positionDAO.flush();
		updateIsParent(v);
		return v;

	}

	@Override
	public void saveOrUpdate(Position v)
	{
		super.saveOrUpdate(v);
		updateIsParent(v);
	}

	public void updateIsParent(Position position)
	{
		position.setIgnoreUserAudit(true);
		if (position == null)
			return;
		long typeId = position.getPosId().longValue();
		long parentId = position.getParentId().longValue();
		position = (Position) this.positionDAO.get(Long.valueOf(typeId));
		Position parent = (Position) this.positionDAO.get(Long
				.valueOf(parentId));

		if (position != null) {
			int childCount = this.positionDAO.getChildCountByParentId(
					position.getPosId().longValue()).intValue();
			if (childCount > 0) {
				position.setIsParent("true");
			}
			else {
				position.setIsParent("false");
			}
			this.positionDAO.save(position);
		}

		if (parent != null) {
			int childCount = this.positionDAO.getChildCountByParentId(
					parent.getPosId().longValue()).intValue();
			if (childCount > 0)
				parent.setIsParent("true");
			else {
				parent.setIsParent("false");
			}
			parent.setIgnoreUserAudit(true);
			this.positionDAO.save(parent);
		}
	}

	@Override
	public void remove(Long paramPK)
	{
		// TODO Auto-generated method stub
		super.remove(paramPK);
		Position p = get(paramPK);
		updateIsParent(p);
	}

	@Override
	public void remove(Position v)
	{
		// TODO Auto-generated method stub
		super.remove(v);
		updateIsParent(v);
	}
}
