package com.ocsoft.oa.utils;

public class Constants
{
	//User optrions
	public static int ACTION_TYPE_SHOW=1;
	public static int ACTION_TYPE_ADD=2;
	public static int ACTION_TYPE_DELETE=3;
	public static int ACTION_TYPE_UPATE=4;
	public static int ACTION_TYPE_IMPORT=5;
	public static int ACTION_TYPE_EXPORT=6;
	
	//Custom form
	public static int FIELD_TYPE_INPUT = 1;
	public static int FIELD_TYPE_SELECT = 2;
	public static int FIELD_TYPE_TEXTAREA = 3;
	public static int FIELD_TYPE_RADIO = 4;
	public static int FIELD_TYPE_CHECKBOX = 5;
	public static int FIELD_TYPE_FILE = 6;
	public static int FIELD_TYPE_HIDDEN = 7;
	
	public static String SESS_USER_KEY = "_user_login";
	
	
	public static void main(String[] args)
	{
		int a = (0<<5);
		int b = (1<<4);
		System.out.println( Integer.toBinaryString(a));
		System.out.println( Integer.toBinaryString(b));
		System.out.println( "---------------------------");
		System.out.println( Integer.toBinaryString(a+b));
		
		
		int p = 0;
		for(int i=1;i<32;i++)
		{
			p = p+i;
			System.out.println(p +"="+Integer.toBinaryString(p));
		}
	}

}
