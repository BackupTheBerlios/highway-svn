/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;


/**
 * 
 */
public class MathHelper
{
	/**
	 * Method compare
	 * @param l1 long
	 * @param l2 long
	 * @return int
	 */
	public static int compare(long l1, long l2)
	{
		if (l1 > l2)
		{
			return 1;
		}
		else if (l1 < l2)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * Remplace Math.pow(double,double) pour éviter les imprécisions
	 * du double. Attention, les exposant ngatifs sont interdits.
	 *
	 * @param value
	 * @param expo
	 * @return long
	 */
	public static long pow(long value, long expo)
	{
		if (expo < 0)
		{
			throw new IllegalArgumentException("Exposant négatif : " + expo);
		}

		if (expo == 0)
		{
			return 1;
		}

		long result = value;
		long absValue = Math.abs(value);

		while (--expo > 0)
		{
			result = result * absValue;
		}

		return result;
	}
}
