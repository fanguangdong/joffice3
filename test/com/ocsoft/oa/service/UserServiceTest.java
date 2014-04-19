package com.ocsoft.oa.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.ocsoft.oa.BaseTest;
import com.ocsoft.oa.service.system.IACLService;
import com.ocsoft.oa.service.system.IGroupService;
import com.ocsoft.oa.service.system.IRoleService;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.utils.EncryptionUtil;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.AppGroup;
import com.ocsoft.oa.vo.system.AppRole;
import com.ocsoft.oa.vo.system.AppUser;

public class UserServiceTest extends BaseTest
{
	private IUserService service;
	private IACLService aclService;
	private IRoleService roleService;
	private IGroupService groupService;
	@Before
	public void setUp()
	{
		super.setUp();
		service = (IUserService)ctx.getBean("userService");
		aclService= (IACLService)ctx.getBean("aclService");
		roleService = (IRoleService)ctx.getBean("roleService");
		groupService = (IGroupService)ctx.getBean("groupService");
	}
	
	@Test
	public void testAdd()
	{
		
		
		try {
			for(int i = 6; i<7; i++)
			{
				String usrName = "swang"+i;
				AppUser user = new AppUser();
				user.setUserName("swang"+i);
				user.setUserPwd(EncryptionUtil.encrypt(usrName,usrName));
				user.setFirstName("first "+i);
				user.setLastName("last"+i);
				user.setEmail(i+"@dev.com");
				user.setUser("test");
				service.save(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testAcl()
	{
		aclService.getACLByUid(new Long(1),"1");
	}
	
	public void testGetRole()
	{
		List<AppRole> roles = roleService.getRolesByUId(Long.valueOf(1));
		for(AppRole role : roles)
		{
			System.out.println(role.getRoleName());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception
	{
		String uId = "4";
		AppUser u = null;
		if(StringUtil.isBlankOrNull(uId))
		{
			u = new AppUser();
		}
		else
		{
			u = service.get(Long.valueOf(uId));
		}
		u.setUser("test");
		u.setUserPwd(EncryptionUtil.encrypt("swang","swang"));
		service.saveOrUpdate(u);
	}
}
