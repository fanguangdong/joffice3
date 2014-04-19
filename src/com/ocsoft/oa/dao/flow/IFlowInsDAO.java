package com.ocsoft.oa.dao.flow;

import java.util.List;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.flow.FlowInstance;

public interface IFlowInsDAO extends IBaseDao<FlowInstance, Long>
{

public void startFlow(String key,String user);
	
	public List getMyRequest(String user);
	public List getMyFinished(String user);
	
	public List getUserPending(String user);
	public List getUserTaken(String user);
	public List getUserFinished(String user);
	
	public List getAll();
	
	public void del(Long id);
}
