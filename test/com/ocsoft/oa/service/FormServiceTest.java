package com.ocsoft.oa.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.FormControl;
import net.htmlparser.jericho.FormField;
import net.htmlparser.jericho.FormFields;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import org.junit.Before;
import org.junit.Test;

import com.ocsoft.oa.BaseTest;
import com.ocsoft.oa.service.flow.ICustomFormService;
import com.ocsoft.oa.vo.form.CustomForm;

public class FormServiceTest extends BaseTest
{
	private ICustomFormService customFormService;
	@Before
	public void setUp()
	{
		super.setUp();
		customFormService = (ICustomFormService)ctx.getBean("customFormService");
	}
	@Test
	public void testParse()
	{
		List<CustomForm> forms = customFormService.getAll();
		InputStream ins = null;
		Source source = null;
		try {
			for(CustomForm form : forms)
			{
				String _html = form.getFormTemplate();
				ins = new ByteArrayInputStream(_html.getBytes());
				source = new Source(ins);
				FormFields fields = source.getFormFields();
				
				for(Iterator<FormField> it =fields.iterator(); it.hasNext();)
				{
					FormField field = it.next();
					FormControl  ctrl = field.getFormControl();
					
					String name = ctrl.getName();
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
		
	}
}
