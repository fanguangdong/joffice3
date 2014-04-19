package com.ocsoft.oa;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ocsoft.oa.service.system.IModuleService;
import com.ocsoft.oa.vo.system.Module;

public class SAXParseModuleService extends DefaultHandler
{
	public Map<String, Module> modules = null;
	public IModuleService moduleService;
	public Map parse(InputStream xmlStream, IModuleService moduleService) throws ParserConfigurationException, SAXException, IOException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();  
		SAXParser parser = factory.newSAXParser();  
		SAXParseModuleService handler = new SAXParseModuleService();
		handler.moduleService = moduleService;
		parser.parse(xmlStream, handler);
		System.out.println(handler.modules);
		return handler.modules;

	}

	@Override
	public void startDocument() throws SAXException
	{
		modules = new HashMap<String, Module>();
		super.startDocument();
	}

	

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException
	{
		if(!"module".equals(qName))
			return;
		//moduleName="用户组织管理" defaultUrl="" resSn="usrOrgMgt" showInMenu="1"
		String moduleName = attributes.getValue("moduleName");
		String defaultUrl = attributes.getValue("defaultUrl");
		String resSn = attributes.getValue("resSn");
		String showInMenu = attributes.getValue("showInMenu");
		String parent = attributes.getValue("parent");
		
		
		Module module = new Module();
		module.setModuleName(moduleName);
		module.setDefaultUrl(defaultUrl);
		module.setResSn(resSn);
		module.setShowInMenu("1".equals(showInMenu) ? 1 : 0);
		module.setUser("test");
		
		if(parent== null || parent.trim().length()==0 || modules.get(parent) == null)
		{
			module.setParentId(Long.valueOf(0));
		}
		else
		{
			module.setParentId(modules.get(parent).getModuleId());
		}
		moduleService.save(module);
		modules.put(resSn, moduleService.getByResSn(resSn));
	}

	

}
