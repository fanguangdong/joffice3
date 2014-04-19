package com.ocsoft.oa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.ocsoft.oa.service.system.IModuleService;

public class DataInit extends BaseTest
{
	private IModuleService moduleService;
	@Test
	public void init()
	{
		
		try {
			SAXParseModuleService handler = new SAXParseModuleService();
			InputStream input = this.getClass().getClassLoader().getResourceAsStream("Data.xml");  
			Map modules = handler.parse(input,moduleService);
			
		}
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Before
	public void setUp()
	{
		super.setUp();
		moduleService = (IModuleService)ctx.getBean("moduleService");
	}
}
