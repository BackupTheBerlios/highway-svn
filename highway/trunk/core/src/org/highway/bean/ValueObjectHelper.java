/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import org.highway.helper.CollectionHelper;
import java.util.Collection;
import java.util.Iterator;

/**
 * Provides static help methods on value objects.
 *
 * 
 */
public class ValueObjectHelper
{
	/**
	 * Sets the state of the specified object to saved (new = false and
	 * dirty = false) if only the specified object is of type ValueObject.
	 *
	 * @param object the object to set
	 */
	public static void setSaved(Object object)
	{
		if (object == null)
		{
			return;
		}

		if (object instanceof ValueObject)
		{
			((ValueObject) object).setSaved();
		}
	}

	/**
	 * Sets the state of the value objects contained in the specified
	 * collection to saved (new = false and dirty = false). Only objects
	 * of type ValueObject are set.
	 *
	 * @param objects the collection of objects to set
	 */
	public static void setSaved(Collection objects)
	{
		if (CollectionHelper.isNullOrEmpty(objects))
		{
			return;
		}

		for (Iterator iter = objects.iterator(); iter.hasNext();)
		{
			setSaved(iter.next());
		}
	}

	/**
	 * Sets the state of the value objects contained in the specified
	 * array to saved (new = false and dirty = false). Only objects
	 * of type ValueObject are set.
	 *
	 * @param objects the collection of objects to set
	 */
	public static void setSaved(Object[] objects)
	{
		if (objects == null)
		{
			return;
		}

		for (int i = 0; i < objects.length; i++)
		{
			setSaved(objects[i]);
		}
	}
}
