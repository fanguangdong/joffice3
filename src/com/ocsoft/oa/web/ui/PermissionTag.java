package com.ocsoft.oa.web.ui;

import com.ocsoft.core.util.ApplicationContextHolder;
import com.ocsoft.oa.service.system.IACLService;

public class PermissionTag
{
	private static IACLService aclService = 
		//(IACLService)ApplicationContextHolder.getApplicationContext(ApplicationContextHolder.AppCtx.OA_CONTEXT).getBean("aclService");
		(IACLService)ApplicationContextHolder.getApplicationContext(/**/).getBean("aclService");
	
	public static boolean hasPermission(String user,String resSn, int actionType)
	{
		return aclService.hasPermission(user, resSn, actionType);
	}

}
