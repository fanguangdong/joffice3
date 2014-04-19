package com.ocsoft.oa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Before;
import org.junit.Test;

import com.ocsoft.oa.BaseTest;
import com.ocsoft.oa.service.flow.IFlowService;

public class FlowServiceTest extends BaseTest
{
	private IFlowService flowService;

	@Before
	public void setUp()
	{
		super.setUp();
		flowService = (IFlowService) ctx.getBean("flowService");

	}

	public void testDeploy()
	{
		// String dir = System.getProperty("user.dir");
		// System.out.println(dir);
		flowService.deployFlow("MyProcess1.bpmn");
		System.out.println("Number of process definitions: "
				+ flowService.getAllFlow(null).size());
	}

	public void testDel()
	{
		flowService.deleteFlow("1");
	}

	public void testStart()
	{
		List list = flowService.getAllFlow(null);
		for (Iterator it = list.iterator(); it.hasNext();) {
			ProcessDefinition pd = (ProcessDefinition) it.next();
			String key = pd.getKey();

			flowService.startFlow(key);
		}
	}
@Test
	public void testActiviti()
	{
		List list = flowService.getActivityList("myProcess");
		for (Iterator it = list.iterator(); it.hasNext();) {
			ActivityImpl activity = (ActivityImpl) it.next();
			Map<String, Object> properties = activity.getProperties();
			String type = properties.get("type").toString();
			if (type.equals("exclusiveGateway")) 
			{
				List<PvmTransition> outgoingTransitions = activity.getOutgoingTransitions();
				for (PvmTransition outgoingTransition : outgoingTransitions) 
				{
					TransitionImpl ti = (TransitionImpl) outgoingTransition;
					System.out.println(ti.getProperties());
				}
			}
			else 
			{
				System.out.println(properties);
			}
			System.out.println("==================");
		}
	}

}
