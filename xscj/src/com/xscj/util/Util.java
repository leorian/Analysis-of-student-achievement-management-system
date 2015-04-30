package com.xscj.util;

public class Util {
	public static boolean isNumeric(String string)
	{
		for(int i=string.length(); --i>=0;)
		{
			if(!Character.isDigit(string.charAt(i)))
			{
				return false;
			}	
		}
		return true;
	}
}
