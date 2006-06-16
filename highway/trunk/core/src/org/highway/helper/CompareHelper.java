/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.exception.DoNotInstantiateException;
import java.util.Comparator;

/**
 * Provides comparators and static compare methods.
 *
 * @author attias
 */
public final class CompareHelper
{
	private CompareHelper()
	{
		throw new DoNotInstantiateException();
	}

	private static final Comparator naturalNullComparator =
		new NaturalNullComparator();
	private static final Comparator naturalNullReverseComparator =
		new NaturalNullReverseComparator();

	/**
	 * Returns a comparator that accepts null values and imposes the natural
	 * ordering on a collection of objects that implement the Comparable
	 * interface. The natural ordering is the ordering imposed by the objects'
	 * own compareTo method. A null represents the smallest value.
	 */
	public static Comparator getNaturalNullComparator()
	{
		return naturalNullComparator;
	}

	/**
	 * Returns a comparator that imposes the reverse ordering than the one
	 * returned by the method <code>getNaturalNullComparator</code>.
	 */
	public static Comparator getNaturalNullReverseComparator()
	{
		return naturalNullReverseComparator;
	}

	static class NaturalNullComparator implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			if (o1 == null)
			{
				if (o2 == null)
				{
					return 0; // o1 and o2 are equals
				}

				return -1; // o1 is lesser than o2
			}

			if (o2 == null)
			{
				return 1; // o1 is greater than o2
			}

			if (o1 instanceof Comparable)
			{
				return ((Comparable) o1).compareTo(o2);
			}

			throw new ClassCastException(
				"Non comparable class: " + o1.getClass());
		}
	}

	static class NaturalNullReverseComparator extends NaturalNullComparator
	{
		public int compare(Object o1, Object o2)
		{
			return -super.compare(o1, o2);
		}
	}
}
