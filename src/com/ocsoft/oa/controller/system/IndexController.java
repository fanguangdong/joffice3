package com.ocsoft.oa.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.oa.service.system.IClientService;
import com.ocsoft.oa.service.system.IModuleService;
import com.ocsoft.oa.service.system.IUserService;
import com.ocsoft.oa.utils.Constants;
import com.ocsoft.oa.utils.EncryptionUtil;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.utils.TreeUtil;
import com.ocsoft.oa.vo.system.AppUser;
import com.ocsoft.oa.vo.system.Client;
import com.ocsoft.oa.vo.system.Module;
@Controller
public class IndexController
{
	private IUserService userService;
	private IModuleService moduleService;
	@RequestMapping("/index")
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		//List<Module> modules = moduleService.getAdminMenu(Long.valueOf(0));
		List<Module> modules = moduleService.getByParentId(Long.valueOf(0));
		mv.addObject("modules", modules);
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping("/content")
	public ModelAndView showContent(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		Long moduleId = NumberUtil.toLong(request.getParameter("moduleId"), null);
		mv.addObject("moduleId",request.getParameter("moduleId"));
		mv.addObject("module", moduleService.get(moduleId));
		mv.setViewName("content1");
		return mv;
	}
	
	@RequestMapping("/showLogin")
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	
	@RequestMapping("/login")
	@ResponseBody
	public Map login(HttpServletRequest request, HttpServletResponse response)
	{
		Map rtn = new HashMap();
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			if(!StringUtil.isBlankOrNull(userName) && !StringUtil.isBlankOrNull(password))
			{
				AppUser usr = getUserService().getByUserName(userName);
				if(usr != null)
				{
					String pwd = usr.getUserPwd();
					String _pwd = EncryptionUtil.encrypt(password, userName);
					if(_pwd.equals(pwd))
					{
						rtn.put("status", 1);
						request.getSession().setAttribute(Constants.SESS_USER_KEY, userName);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	
	
	
	
	

	
	//getters and setters
	public IUserService getUserService()
	{
		return userService;
	}

	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}

	
	public IModuleService getModuleService()
	{
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService)
	{
		this.moduleService = moduleService;
	}
	
	
	
	
	

}
