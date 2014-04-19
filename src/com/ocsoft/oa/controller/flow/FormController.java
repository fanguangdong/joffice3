package com.ocsoft.oa.controller.flow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.oa.service.flow.ICustomFieldService;
import com.ocsoft.oa.service.flow.ICustomFormService;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.vo.form.CustomField;
import com.ocsoft.oa.vo.form.CustomForm;

@Controller
public class FormController 
{
	private static final Log logger = LogFactory.getLog(FormController.class);
	private ICustomFormService customFormService;
	private ICustomFieldService customFieldService;
	
	
	@RequestMapping("/form/show")
	public ModelAndView show()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forms");
		mv.addObject("name", "swang");
		return mv;
	}
	@RequestMapping("/form/getForms")
	@ResponseBody
	public List<CustomForm> list()
	{
		List<CustomForm> forms = customFormService.getAll();
		return forms;
	}
	@RequestMapping("/form/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		return this.edit(request, response);
	}
	@RequestMapping("/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("formDetail");
		String formId = request.getParameter("formId");
		CustomForm formDetail = null;
		if(NumberUtil.isNumber(formId))
		{
			formDetail = customFormService.get(Long.valueOf(formId));
		}
		else
		{
			formDetail = new CustomForm();
		}
		
		mv.addObject("formDetail", formDetail);
		return mv;
	}
	
	@RequestMapping("/form/preview")
	public ModelAndView preview(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("formPreview");
		String formId = request.getParameter("formId");
		
		mv.addObject("formId", formId);
		//mv.addObject("formDetail", formDetail);
		return mv;
	}
	
	public String del()
	{
		return "edit";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/save")
	@ResponseBody
	public Map save(HttpServletRequest request, HttpServletResponse response)
	{
		Map rtn = new HashMap();
		String formName = request.getParameter("formName");
		String formTemplate = request.getParameter("formTemplate");
		String formId = request.getParameter("formId");
		CustomForm formPO = null;
		if(NumberUtil.isNumber(formId))
		{
			formPO = customFormService.get(Long.valueOf(formId));
		}
		else
		{
			formPO = new CustomForm();
		}
		formPO.setFormName(formName);
		formPO.setFormTemplate(formTemplate);
		formPO.setUser("test");
		customFormService.save(formPO);
		rtn.put("status", 1);
		return rtn;
	}
	
	@RequestMapping("/form/saveFiled")
	@ResponseBody
	public CustomField saveFiled(HttpServletRequest request, HttpServletResponse response)
	{
		String fieldId = request.getParameter("fieldId");
		String formId = request.getParameter("formId");
		String fieldName = request.getParameter("fieldName");
		String fieldLabel = request.getParameter("fieldLabel");
		String fieldType = request.getParameter("fieldType");
		String fieldSize = request.getParameter("fieldSize");
		String maxSize = request.getParameter("maxSize");
		String isRequired = request.getParameter("isRequired");
		
		CustomField po = null;
		if(NumberUtil.isNumber(fieldId))
		{
			po = customFieldService.get(Long.valueOf(fieldId));
		}
		else
		{
			po = new CustomField();
			CustomForm customForm = customFormService.get(Long.valueOf(formId));
			po.setCustomForm(customForm);
		}
		po.setFieldName(fieldName);
		po.setFieldLabel(fieldLabel);
		po.setFieldType(Integer.valueOf(fieldType));
		po.setFieldSize(Integer.valueOf(fieldSize));
		po.setMaxSize(Integer.valueOf(maxSize));
		//po.set
		po.setIsRequired(Boolean.valueOf(isRequired));
		po.setUser("test");
		customFieldService.save(po);
		
		return po;
	}
	//Field related
	@RequestMapping("/form/getFields")
	@ResponseBody
	public List<CustomField> getFields(@RequestParam Long formId)
	{
		List<CustomField> fields = customFieldService.getByFormId(formId);
		return fields;
	}
	
	
	
	//getters and setters
	public ICustomFieldService getCustomFieldService()
	{
		return customFieldService;
	}
	public void setCustomFieldService(ICustomFieldService customFieldService)
	{
		this.customFieldService = customFieldService;
	}
	
	public ICustomFormService getCustomFormService()
	{
		return customFormService;
	}
	public void setCustomFormService(ICustomFormService customFormService)
	{
		this.customFormService = customFormService;
	}
}
