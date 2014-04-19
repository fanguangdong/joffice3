package com.ocsoft.oa.dao;

import org.junit.Before;
import org.junit.Test;

import com.ocsoft.oa.BaseTest;
import com.ocsoft.oa.dao.system.IUserDAO;
import com.ocsoft.oa.vo.system.AppUser;

public class UserDAOTest extends BaseTest
{
	private IUserDAO dao;
	@Before
	public void setUp()
	{
		super.setUp();
		dao = (IUserDAO)ctx.getBean("userDAO");
	}
	@Test
	public void addTest()
	{
		AppUser user = new AppUser();
		user.setUserName("swang");
		user.setUserPwd("password");
		dao.save(user);
	}

}
