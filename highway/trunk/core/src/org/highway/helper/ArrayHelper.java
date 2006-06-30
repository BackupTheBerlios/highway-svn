package org.highway.helper;

import java.lang.reflect.Array;

/**
 * 
 * @since 1.4.3
 */
public class ArrayHelper
{
	public static String dump(Object array)
	{
		if (array == null)
		{
			return "null";
		}
		
		if (!array.getClass().isArray())
		{
			throw new IllegalArgumentException(
				"not an array, argument = " + array);
		}
		
		int length = Array.getLength(array);
		StringBuffer buffer = new StringBuffer(length*10);
		buffer.append('[');
		for (int i = 0; i < length; i++)
		{
			buffer.append(Array.get(array, i));
			if (i + 1 < length)
			{
				buffer.append(", ");
			}
		}
		return buffer.append(']').toString();
	}

}
