package com.ocsoft.oa.service.flow.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.FormControl;
import net.htmlparser.jericho.FormField;
import net.htmlparser.jericho.FormFields;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import org.springframework.stereotype.Service;

import com.ocsoft.core.dao.IBaseDao;
import com.ocsoft.core.service.impl.BaseServiceImpl;
import com.ocsoft.oa.dao.flow.ICustomFieldDAO;
import com.ocsoft.oa.service.flow.ICustomFieldService;
import com.ocsoft.oa.utils.StringUtil;
import com.ocsoft.oa.vo.form.CustomField;
import com.ocsoft.oa.vo.form.CustomForm;
@Service("customFieldService")
public class CustomFieldServiceImpl extends BaseServiceImpl<CustomField, Long>
		implements ICustomFieldService
{

	private ICustomFieldDAO customFieldDAO;
	
	
	@Resource(name="customFieldDAO")
	public void setDao(IBaseDao baseDao)
	{
		// TODO Auto-generated method stub
		super.setDao(baseDao);
		customFieldDAO = (ICustomFieldDAO) baseDao;
	}


	@Override
	public List<CustomField> getByTableId(Long tableId)
	{
		return customFieldDAO.getByTableId(tableId);
	}


	@Override
	public List<CustomField> getByFormId(Long formId)
	{
		// TODO Auto-generated method stub
		return customFieldDAO.getByFormId(formId);
	}
	
	public List<CustomField> parseAndSaveFields(CustomForm customForm)
	{
		List<CustomField> customFields = new ArrayList<CustomField>();
		InputStream ins = null;
		Source source = null;
		try {
			String formContent = customForm.getFormTemplate();
			if(StringUtil.isNotBlank(formContent))
			{
				ins = new ByteArrayInputStream(formContent.getBytes());
				source = new Source(ins);
				FormFields fields = source.getFormFields();
				
				for(Iterator<FormField> it =fields.iterator(); it.hasNext();)
				{
					FormField field = it.next();
					FormControl  ctrl = field.getFormControl();
					
					String name = ctrl.getName();
					CustomField customfield = getByFieldName(customForm,name);
					System.out.println("Name : "+name);
					
					StartTag tag = ctrl.getElement().getStartTag();
					Attributes attrs = tag.getAttributes();
					for(Iterator<Attribute> ait = attrs.iterator();ait.hasNext();)
					{
						Attribute attr = ait.next();
						String attName = attr.getName();
						String attValue = attr.getValue();
						System.out.println(attName +" = "+attValue);
					}
				}
			}
				
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customFields;
	}

	private CustomField getByFieldName(CustomForm customForm, String fieldName)
	{
		CustomField customField = null;
		if(customForm.getFormId()!=null)
		{
			List<CustomField> customFields = getByFormId(customForm.getFormId());
			for(CustomField field : customFields)
			{
				if(StringUtil.isNotBlank(field.getFieldName()) && field.getFieldName().equalsIgnoreCase(fieldName))
				{
					customField = field;
					break;
				}
			}
		}
		if(customField == null)
		{
			customField = new CustomField();
			customField.setCustomForm(customForm);
			customField.setFieldName(fieldName);
		}
		
		return customField;
	}
}
