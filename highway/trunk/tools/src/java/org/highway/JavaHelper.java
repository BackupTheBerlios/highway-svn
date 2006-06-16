/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway;

public class JavaHelper
{
	public static String firstCharToUpperCase(String s)
	{
		if (null == s)
		{
			return null;
		}

		String res = String.valueOf(s.charAt(0)).toUpperCase();

		res += s.substring(1, s.length());

		return res;
	}
	public static String firstCharToLowerCase(String s)
	{
		if (null == s)
		{
			return null;
		}

		String res = String.valueOf(s.charAt(0)).toLowerCase();

		res += s.substring(1, s.length());

		return res;
	}
	
	public static String getConstantNameFromPropertyName(String propertyName)
	{
		if (null == propertyName)
		{
			return null;
		}

		StringBuffer buffer = new StringBuffer(propertyName.length() + 5); // for 5 unerscore characters

		for (int i = 0; i < propertyName.length(); i++)
		{
			char c = propertyName.charAt(i);

			if ((i > 0) && Character.isUpperCase(c))
			{
				buffer.append('_');
			}

			buffer.append(Character.toUpperCase(c));
		}

		return buffer.toString();
	}

	public static String getPropertyNameFromConstantName(String constantName)
	{
		if (null == constantName)
		{
			return null;
		}

		StringBuffer buffer = new StringBuffer(constantName.length());

		for (int i = 0; i < constantName.length(); i++)
		{
			char c = constantName.charAt(i);

			if (c == '_')
			{
				continue;
			}

			if ((i > 0) && (constantName.charAt(i - 1) == '_'))
			{
				buffer.append(Character.toUpperCase(c));
			}
			else
			{
				buffer.append(Character.toLowerCase(c));
			}
		}

		return buffer.toString();
	}

	public static String getShortClassName(String s)
	{
		if (null == s)
		{
			return null;
		}

		int index = s.lastIndexOf(".");

		if (-1 != index)
		{
			return s.substring(index + 1, s.length());
		}
		else
		{
			return s;
		}
	}

	public static String getGetterName(String propertyName)
	{
		return "get" + firstCharToUpperCase(propertyName);
	}

	public static String getSetterName(String propertyName)
	{
		return "set" + firstCharToUpperCase(propertyName);
	}

	public static boolean isPrimitiveType(String type)
	{
		return type.equals("int") || type.equals("boolean")
		|| type.equals("short") || type.equals("byte") || type.equals("long")
		|| type.equals("char") || type.equals("double") || type.equals("float");
	}

	public static boolean isNullOrEmpty(String value)
	{
		return (value == null) || (value.length() == 0);
	}
}
