package com.zcy.util;

public class Util {
	
	//判断是否为空
	public static boolean judgeIsNull(Object obj)
	{
		if(obj instanceof String)
		{
			if(obj == null || "".equals(obj))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return true;
	}
	
	//判断是否为空
	public static boolean judgeNotNull(Object obj)
	{
		if(obj instanceof String)
		{
			if(obj == null || "".equals(obj))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		return true;
	}
}
