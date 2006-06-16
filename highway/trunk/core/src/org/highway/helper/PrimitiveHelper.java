/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides static helper methods on primitive types.
 *
 * @author attias
 */
public class PrimitiveHelper
{
	private static Map primitiveClasses;

	static
	{
		primitiveClasses = new HashMap(9);
		primitiveClasses.put(byte.class.getName(), byte.class);
		primitiveClasses.put(short.class.getName(), short.class);
		primitiveClasses.put(int.class.getName(), int.class);
		primitiveClasses.put(long.class.getName(), long.class);
		primitiveClasses.put(char.class.getName(), char.class);
		primitiveClasses.put(boolean.class.getName(), boolean.class);
		primitiveClasses.put(float.class.getName(), float.class);
		primitiveClasses.put(double.class.getName(), double.class);
	}

	/**
	 * Returns the primitive class object that matches the specified type name.<br>
	 * Returns null if the name does not match a primitive type.
	 *
	 * @param name the type name
	 * @return the primimitive class object or null
	 */
	public static Class getPrimitiveClass(String name)
	{
		return (Class) primitiveClasses.get(name);
	}

	/**
	 * Returns the wrapper class that matches the specified primitive class object.<br>
	 * Returns the specified class object if it does not match a primitive class.<br>
	 * <br>
	 * Examples :<br>
	 * <pre>
	 *     getPrimitiveWrapperClass(int.class) returns Integer.class
	 *     getPrimitiveWrapperClass(float.class) returns Float.class
	 *     getPrimitiveWrapperClass(Integer.class) returns Integer.class
	 *     getPrimitiveWrapperClass(String.class) returns String.class
	 * </pre>
	 *
	 * @param clazz a primitive class object
	 * @return the corresponding wrapper class
	 */
	public static Class getPrimitiveWrapperClass(Class clazz)
	{
		if (clazz == boolean.class)
		{
			return Boolean.class;
		}
		else if (clazz == int.class)
		{
			return Integer.class;
		}
		else if (clazz == double.class)
		{
			return Double.class;
		}
		else if (clazz == long.class)
		{
			return Long.class;
		}
		else if (clazz == short.class)
		{
			return Short.class;
		}
		else if (clazz == byte.class)
		{
			return Byte.class;
		}
		else if (clazz == float.class)
		{
			return Float.class;
		}
		else if (clazz == char.class)
		{
			return Character.class;
		}

		return clazz;
	}
}
