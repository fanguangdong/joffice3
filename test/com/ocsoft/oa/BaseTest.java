package com.ocsoft.oa;

import java.net.URL;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest
{
	protected SessionFactory sessionFactory;  
	protected ApplicationContext ctx; 
	public void setUp()
	{
		URL url = this.getClass().getResource(".");
		String fileName = url.getFile();
		fileName = fileName.substring(0, fileName.indexOf("WEB-INF"));
		String oa = "file:" + fileName + "WEB-INF/SpringOA-servlet.xml";
		String[] configLocations = new String[] {"classpath:conf/applicationContext.xml",oa};  
		ctx = new ClassPathXmlApplicationContext(configLocations);  
		sessionFactory = ctx.getBean("sessionFactory", SessionFactory.class); 
	}

}
