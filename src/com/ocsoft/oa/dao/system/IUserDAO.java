package com.ocsoft.oa.dao.system;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.oa.vo.system.AppUser;

public interface IUserDAO extends IBaseDao<AppUser,Long>
{
	public AppUser getByUserName(String userName);

}
