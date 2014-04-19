package com.ocsoft.oa.web.ui;

import java.io.IOException;
import java.util.Set;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

import com.ocsoft.core.util.ApplicationContextHolder;
import com.ocsoft.oa.service.flow.ICustomFormService;
import com.ocsoft.oa.vo.form.CustomField;
import com.ocsoft.oa.vo.form.CustomForm;
import com.ocsoft.oa.vo.form.CustomTable;

public class CustomFormTag extends HtmlEscapingAwareTag
{
	private Long formId;
	
	private static ICustomFormService customFormService = 
			//(ICustomFormService)ApplicationContextHolder.getApplicationContext(ApplicationContextHolder.AppCtx/*.OA_CONTEXT*/).getBean("customFormService");
			(ICustomFormService)ApplicationContextHolder.getApplicationContext().getBean("customFormService");
	@Override
	protected int doStartTagInternal() throws Exception
	{
		
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
		String template = "";
		if(formId!= null)
		{
			CustomForm customForm = customFormService.get(formId);
			if(customForm!=null)
			{
				template = customForm.getFormTemplate();
			}
		}
		return template;
	}

	public Long getFormId()
	{
		return formId;
	}

	public void setFormId(Long formId)
	{
		this.formId = formId;
	}

}
