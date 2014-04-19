package com.ocsoft.oa.service.flow;

import java.util.List;

public interface IFlowInsService
{
	public List getAll();
	public List getAllRunning();
	public List getAllFinished();
	
	public List getAllForDef(String defId);
	public List getAllRunningForDef(String defId);
	public List getAllFinishedForDef(String defId);
	
	
	public void startFlow(String key,String user);
	
	public List getMyRequest(String user);
	public List getMyFinished(String user);
	public List getUserPending(String user);
	public List getUserTaken(String user);
	public List getUserFinished(String user);
	
	
	public void del(Long id);
}
