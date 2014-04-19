package com.ocsoft.oa.service.system;

import org.springframework.stereotype.Service;

import com.ocsoft.core.service.IBaseService;
import com.ocsoft.oa.vo.system.AppUser;
@Service("userService")
public interface IUserService extends  IBaseService<AppUser, Long>
{
	
	public AppUser getByUserName(String userName);
	public void save(AppUser userVO, String user);
	public void assign(AppUser userVO, String user);
}
