package com.ocsoft.oa.utils;

import java.math.BigDecimal;

public class NumberUtil
{
	public static boolean isNumber(String numStr)
	{
		try {
			if (numStr == null || numStr.trim().equals(""))
				return false;
			new BigDecimal(numStr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static int toInt(String aString)
	{

		int value = 0;
		if (aString == null || aString.trim().equals(""))
			return 0;
		value = Integer.parseInt(aString.trim());
		return value;
	}
	
	
	public static Long toLong(String numStr, Long defaultValue)
	{
		Long rtn = null;
		if(!StringUtil.isBlank(numStr))
		{
			 rtn = Long.parseLong(numStr);
		}
		if(rtn == null && defaultValue != null)
		{
			rtn = defaultValue;
		}
		
		return rtn;
	}
}
