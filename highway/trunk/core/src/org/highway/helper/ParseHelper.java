/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.bean.Decimal;

/**
 * ...
 *
 * 
 */
public class ParseHelper
{
	/**
	 * Method parseLong
	 * @param number String
	 * @return Long
	 */
	public static Long parseLong(String number)
	{
		return (number == null) ? null : Long.valueOf(number);
	}

	/**
	 * Method parseInteger
	 * @param number String
	 * @return Integer
	 */
	public static Integer parseInteger(String number)
	{
		return (number == null) ? null : Integer.valueOf(number);
	}

	/**
	 * Method parseSmallDecimal
	 * @param number String
	 * @return Decimal
	 */
	public static Decimal parseSmallDecimal(String number)
	{
		return (number == null) ? null : new Decimal(number);
	}
}
