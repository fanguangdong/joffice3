package com.ocsoft.oa.spring.view;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.view.JstlView;

public class CustomJstlView extends JstlView
{

	@Override
	public boolean checkResource(Locale locale) throws Exception
	{
		File file = new File(this.getServletContext().getRealPath("/")+getUrl());  
		return file.exists();

	}

}
