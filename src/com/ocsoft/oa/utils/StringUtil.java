package com.ocsoft.oa.utils;

public class StringUtil
{
	public static String isNull(String aValue1, String aValue2)
	{
		return aValue1 == null ? aValue2 : aValue1;
	}

	public static boolean isBlank(final String value)
	{
		return value == null || value.trim().length() == 0;
	}
	
	public static boolean isNotBlank(final String value)
	{
		return !isBlank(value);
	}

	public static boolean isBlankOrNull(final String value)
	{
		return (isBlank(value) || "null".equalsIgnoreCase(value));
	}

	public static boolean isAllBlank(final String[] values)
	{
		for(int i = 0; i < values.length; i++)
		{
			if(!isBlank(values[i]))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isAnyBlank(final String[] values)
	{
		for(int i = 0; i < values.length; i++)
		{
			if(isBlank(values[i]))
			{
				return true;
			}
		}
		return false;
	}
}
