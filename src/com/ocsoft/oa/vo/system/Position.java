package com.ocsoft.oa.vo.system;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ocsoft.core.vo.BaseVO;

public class Position extends BaseVO
{
	public static final long ROOT_PID = -1L;
	public static final long ROOT_ID = 0L;
	public static final int ROOT_DEPTH = 0;
	public static final String IS_PARENT_N = "false";
	public static final String IS_PARENT_Y = "true";
	public static final int IS_LEAF_N = 0;
	public static final int IS_LEAF_Y = 1;
	private Long posId;
	private String posName;
	private String posDesc;
	private Long parentId;
	private String nodePath;
	private Integer depth;
	private Short sn;
	private Short isPrimary;
	private String open = "true";
	private String isParent;
	private Integer isLeaf;
	private String posgroup;
	private List<Position> children;
	

	public Integer getIsLeaf()
	{
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf)
	{
		this.isLeaf = isLeaf;
		if ((isLeaf != null) && (isLeaf.equals(Integer.valueOf(1))))
			this.isParent = "false";
		else if ((isLeaf != null) && (isLeaf.equals(Integer.valueOf(0))))
			this.isParent = "true";
		else
			this.isParent = null;
	}

	public String getIsParent()
	{
		if (this.isLeaf == null)
			return "true";

		return this.isLeaf.intValue() == 1 ? "false" : "true";
	}

	public void setIsParent(String isParent)
	{
		this.isParent = isParent;
		if ((isParent != null) && (isParent.equals("false")))
			this.isLeaf = Integer.valueOf(1);
		else if ((isParent != null) && (isParent.equals("true")))
			this.isLeaf = Integer.valueOf(0);
		else
			this.isLeaf = null;
	}

	public String getOpen()
	{
		return this.open;
	}

	public void setOpen(String open)
	{
		this.open = open;
	}

	public void setPosId(Long posId)
	{
		this.posId = posId;
	}

	public Long getPosId()
	{
		return this.posId;
	}

	public void setPosName(String posName)
	{
		this.posName = posName;
	}

	public String getPosName()
	{
		return this.posName;
	}

	public void setPosDesc(String posDesc)
	{
		this.posDesc = posDesc;
	}

	public String getPosDesc()
	{
		return this.posDesc;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public Long getParentId()
	{
		return this.parentId;
	}

	public void setNodePath(String nodePath)
	{
		this.nodePath = nodePath;
	}

	public String getNodePath()
	{
		return this.nodePath;
	}

	public void setDepth(Integer depth)
	{
		this.depth = depth;
	}

	public Integer getDepth()
	{
		return this.depth;
	}

	public void setSn(Short sn)
	{
		this.sn = sn;
	}

	public Short getSn()
	{
		return this.sn;
	}

	public Short getIsPrimary()
	{
		return this.isPrimary;
	}

	public void setIsPrimary(Short isPrimary)
	{
		this.isPrimary = isPrimary;
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof Position)) {
			return false;
		}
		Position rhs = (Position) object;
		return new EqualsBuilder().append(this.posId, rhs.posId)
				.append(this.posName, rhs.posName)
				.append(this.posDesc, rhs.posDesc)
				.append(this.parentId, rhs.parentId)
				.append(this.nodePath, rhs.nodePath)
				.append(this.depth, rhs.depth).append(this.sn, rhs.sn)
				.append(this.isPrimary, rhs.isPrimary).isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.posId)
				.append(this.posName).append(this.posDesc)
				.append(this.parentId).append(this.nodePath)
				.append(this.depth)
				.append(this.sn)
				.append(this.isPrimary)
				.toHashCode();
	}

	public String toString()
	{
		return new ToStringBuilder(this)
				.append("posId", this.posId)
				.append("posName", this.posName)
				.append("posDesc", this.posDesc)
				.append("parentId", this.parentId)
				.append("nodePath", this.nodePath)
				.append("depth", this.depth)
				.append("sn", this.sn)
				.append("isPrimary", this.isPrimary)
				.toString();
	}

	public String getPosgroup()
	{
		return this.posgroup;
	}

	public void setPosgroup(String posgroup)
	{
		this.posgroup = posgroup;
	}

	public List<Position> getChildren()
	{
		return children;
	}

	public void setChildren(List<Position> children)
	{
		this.children = children;
	}
}
