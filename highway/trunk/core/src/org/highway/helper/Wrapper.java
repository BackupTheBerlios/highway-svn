/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public abstract class Wrapper
{
	/**
	 * Method toList
	 * @param obj1 Object
	 * @return List
	 */
	public static List toList(Object obj1)
	{
		List params = new ArrayList(1);
		params.add(obj1);

		return params;
	}

	/**
	 * Method toList
	 * @param obj1 Object
	 * @param obj2 Object
	 * @return List
	 */
	public static List toList(Object obj1, Object obj2)
	{
		List params = new ArrayList(2);
		params.add(obj1);
		params.add(obj2);

		return params;
	}

	/**
	 * Method toList
	 * @param obj1 Object
	 * @param obj2 Object
	 * @param obj3 Object
	 * @return List
	 */
	public static List toList(Object obj1, Object obj2, Object obj3)
	{
		List params = new ArrayList(3);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);

		return params;
	}

	/**
	 * Method toList
	 * @param obj1 Object
	 * @param obj2 Object
	 * @param obj3 Object
	 * @param obj4 Object
	 * @return List
	 */
	public static List toList(
		Object obj1, Object obj2, Object obj3, Object obj4)
	{
		List params = new ArrayList(4);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);

		return params;
	}

	/**
	 * Method toList
	 * @param obj1 Object
	 * @param obj2 Object
	 * @param obj3 Object
	 * @param obj4 Object
	 * @param obj5 Object
	 * @return List
	 */
	public static List toList(
		Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
	{
		List params = new ArrayList(5);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);
		params.add(obj5);

		return params;
	}

	/**
	 * Method toList
	 * @param obj1 Object
	 * @param obj2 Object
	 * @param obj3 Object
	 * @param obj4 Object
	 * @param obj5 Object
	 * @param obj6 Object
	 * @return List
	 */
	public static List toList(
		Object obj1, Object obj2, Object obj3, Object obj4, Object obj5,
		Object obj6)
	{
		List params = new ArrayList(6);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);
		params.add(obj5);
		params.add(obj6);

		return params;
	}

	/**
	 * Method toList
	 * @param objectArray Object[]
	 * @return List
	 */
	public static List toList(Object[] objectArray)
	{
		return Arrays.asList(objectArray);
	}
}
