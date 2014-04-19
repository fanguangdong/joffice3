package com.ocsoft.oa.web.ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

import com.ocsoft.core.util.ApplicationContextHolder;
import com.ocsoft.oa.service.system.IACLService;
import com.ocsoft.oa.utils.Constants;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.system.Module;

public class ModuleMenuTag extends HtmlEscapingAwareTag
{

	private static IACLService aclService = (IACLService) ApplicationContextHolder
			//.getApplicationContext(ApplicationContextHolder.AppCtx.OA_CONTEXT)
			.getApplicationContext(/**/)
			.getBean("aclService");
	private String userName = null;
	private List<Module> items;
	private String id;
	private String cssClass;
	private String cssStyle;
	private String prefix;
	private String target;
	

	@Override
	protected int doStartTagInternal() throws Exception
	{
		userName=this.pageContext.getSession().getAttribute(Constants.SESS_USER_KEY)+"";
		JspWriter out = pageContext.getOut();

		try {
			out.println(generateHelper());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	public String generateHelper()
	{
		StringBuffer tag = new StringBuffer("");
		if(items!=null && items.size()>0)
		{
			tag.append("<ul id=\"").append(this.getId()).append("\"")
				.append(" class=\"").append(this.getCssClass()).append("\"")
				.append(" style=\"").append(this.getCssStyle()).append("\"")
				.append(">");
			for(Module module : items)
			{
				String id = module.getModuleId().toString();
				String name = module.getModuleName();
				Object url = module.getDefaultUrl();
				List<Module> children = module.getChildren();
				String resSn = module.getResSn().toString();
				if(aclService.hasPermission(userName.toString(), resSn, 1))
				{
					logger.info("User:"+userName+" ResSn:"+resSn+" action:"+1);
					tag.append("<li id='").append(id).append("'>");
					/*
					if(url!= null && !StringUtil.isBlankOrNull(url.toString()))
					{
						tag.append("<a href='"+prefix+"/"+url+"' target='"+this.target+"'>");
					}
					else
					{
						tag.append("<a href='#'>");
					}
					*/
					tag.append("<a href='#' onclick='clickMenuItem(\""+id+"\")'>");
					tag.append(name);
					tag.append("</a>");
					if(children!=null)
					{
						tag.append(buildChildren((List<Module>)children));
					}
					tag.append("</li>");
				}
			}
			tag.append("</ul>");
		}
		logger.info(tag);
		return tag.toString();
	}
	
	private String buildChildren(List<Module> items)
	{
		StringBuffer menuItems = new StringBuffer("");
		int cnt = 0;
		for(Module module : items)
		{
			String id = module.getModuleId().toString();
			String name = module.getModuleName();
			Object url = module.getDefaultUrl();
			List<Module> children = module.getChildren();
			String resSn = module.getResSn().toString();
			
			if(aclService.hasPermission(userName.toString(), resSn, 1))
			{ 
				cnt ++;
				logger.info("User:"+userName+" ResSn:"+resSn+" action:"+1);
				menuItems.append("<li id='").append(id).append("'>");
				/*
				if(url!= null && !StringUtil.isBlankOrNull(url.toString()))
				{
					menuItems.append("<a href='"+prefix+"/"+url+"' target='"+this.target+"'>");
				}
				else
				{
					menuItems.append("<a href='#'>");
				}
				*/
				menuItems.append("<a href='#' onclick='clickMenuItem(\""+id+"\")'>");
				menuItems.append(name);
				menuItems.append("</a>");
				if(children!=null && children.size()>0)
				{
					menuItems.append(buildChildren(children));
				}
				
				menuItems.append("</li>");
			}
		}
		
		StringBuffer tag = new StringBuffer("");
		
		if(cnt>0)
		{
			tag.append("<ul>");
			tag.append(menuItems);
			tag.append("</ul>");
			
		}
		return tag.toString();
	}
	
	
	public List<Module> getItems()
	{
		return items;
	}
	public void setItems(List<Module> items)
	{
		this.items = items;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getCssClass()
	{
		return cssClass;
	}
	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}
	public String getPrefix()
	{
		return prefix;
	}
	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	public String getCssStyle()
	{
		return cssStyle;
	}
	public void setCssStyle(String cssStyle)
	{
		this.cssStyle = cssStyle;
	}

}
