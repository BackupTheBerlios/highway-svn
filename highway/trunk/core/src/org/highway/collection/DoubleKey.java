/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import org.highway.helper.ValueHelper;

/**
 * Use instances of this class as keys in a map when you need to associate two
 * keys with a value in the map. Use this class with regular Map classes instead
 * of the MapOfMapImpl class if you want a light way to index your values with two
 * indexes.
 *
 * 
 */
public class DoubleKey
{
	/**
	 * First key.
	 */
	private Object firstKey;

	/**
	 * Second key.
	 */
	private Object secondKey;

	/**
	 * Constructs a DoubleKey object with the specified first and second keys.
	 */
	public DoubleKey(Object firstKey, Object secondKey)
	{
		this.firstKey = firstKey;
		this.secondKey = secondKey;
	}

	/**
	 * Two DoubleKey objects are equals if their first and second key are equal.
	 */
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (obj == this)
		{
			return true;
		}

		if (secondKey instanceof DoubleKey)
		{
			DoubleKey dkey = (DoubleKey) obj;

			return ValueHelper.equals(firstKey, dkey.firstKey)
			&& ValueHelper.equals(secondKey, dkey.secondKey);
		}

		return false;
	}

	/**
	 * The hashcode is calculated by adding the first and second key hashcodes.
	 *
	 * @todo (attias) find a better hashcode algorythme to combine
	 * multiple hashcode values. Put this algorythm in a reuseable place.
	 */
	public int hashCode()
	{
		return ValueHelper.hashCode(firstKey) + ValueHelper.hashCode(secondKey);
	}

	/**
	 * Returns "firstKey/secondKey".
	 */
	public String toString()
	{
		return firstKey + "/" + secondKey;
	}
}
