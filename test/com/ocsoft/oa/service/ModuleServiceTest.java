package com.ocsoft.oa.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.ocsoft.oa.BaseTest;
import com.ocsoft.oa.service.system.IClientService;
import com.ocsoft.oa.service.system.IModuleService;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.Module;

public class ModuleServiceTest extends BaseTest
{
	private IClientService clientService;
	private IModuleService moduleService;
	@Before
	public void setUp()
	{
		super.setUp();
		clientService = (IClientService)ctx.getBean("clientService");
		moduleService = (IModuleService)ctx.getBean("moduleService");
		
	}
	@Test
	
	public void addMoudel()
	{
		/**
		 * 协同办公
		 * -信息管理
		 * -人事管理
		 * --个人考勤
		 * ---上下班签到
		 * ---请假登记
		 * ---外出登记
		 * --考勤管理
		 * ---考勤设置
		 * ----假期设置
		 * ----班次定义
		 * ----班制定义
		 * ----排班管理
		 * ---考勤信息
		 * --请假管理
		 * 
		 * -行政管理
		 * -报表管理
		 * 
		 * 
		 * 
		 */
		
		Module office = new Module();
		office.setModuleName("协同办公");
		office.setParentId(new Long(0));
		office.setUser("swang");
		moduleService.save(office);
		
		Module workflow = new Module();
		workflow.setModuleName("我的流程");
		workflow.setParentId(new Long(0));
		workflow.setUser("swang");
		moduleService.save(workflow);
		
		Module ach = new Module();
		ach.setModuleName("档案管理");
		ach.setParentId(new Long(0));
		ach.setUser("swang");
		moduleService.save(ach);
		
		Module doc = new Module();
		doc.setModuleName("公文管理");
		doc.setParentId(new Long(0));
		doc.setUser("swang");
		moduleService.save(doc);
		
		Module hrs = new Module();
		hrs.setModuleName("人力管理");
		hrs.setParentId(new Long(0));
		hrs.setUser("swang");
		moduleService.save(hrs);
		
		Module system = new Module();
		system.setModuleName("系统管理");
		system.setParentId(new Long(0));
		system.setUser("swang");
		moduleService.save(system);
		
		
		Module config = new Module();
		config.setModuleName("系统设置");
		config.setParentId(system.getModuleId());
		config.setUser("swang");
		moduleService.save(config);
		
		Module modMgt = new Module();
		modMgt.setModuleName("模块管理");
		modMgt.setParentId(config.getModuleId());
		modMgt.setUser("swang");
		moduleService.save(modMgt);
		
		Module operMgt = new Module();
		operMgt.setModuleName("操作管理");
		operMgt.setParentId(config.getModuleId());
		operMgt.setUser("swang");
		moduleService.save(operMgt);
		
		
		
		
		
		
		
		
	}
	
	public void testUpdate()
	{
		String mId= "24";
		Module m = null;
		try {
			if(StringUtil.isBlankOrNull(mId))
			{
				m = new Module();
			}
			else
			{
				m = moduleService.get(Long.valueOf(mId));
			}
			
			/*
			m.setClientId(Long.valueOf(cId));
			m.setParentId(Long.valueOf(pId));
			m.setModuleName(moduleName);
			m.setTitle(title);
			m.setIcon(icon);
			m.setUrl(url);
			m.setResSn(NumberUtil.toInt(resSn));
			*/
			m.setUser("swang");
			moduleService.save(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
