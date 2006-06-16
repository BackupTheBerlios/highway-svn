/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;


/**
 * @author attias
 */
public class Assert
{
	/**
	 * Check if the condition is true.<br>
	 * If not, throws an <code>IllegalArgumentException</code>
	 * with a "Assert check failed" message.
	 *
	 * @param condition to be checked
	 */
	public static void check(boolean condition)
	{
		if (! condition)
		{
			throw new IllegalArgumentException("Assert check failed");
		}
	}

	/**
	 * Check if the condition is true.<br>
	 * If not, throws an <code>IllegalArgumentException</code>
	 * with the exception message argument.
	 *
	 * @param condition to be checked
	 * @param exceptionMessage message to use if condition false
	 */
	public static void check(boolean condition, String exceptionMessage)
	{
		if (! condition)
		{
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

	/**
	 * Check if the object argument is not null.<br>
	 * If null, throws an <code>IllegalArgumentException</code>
	 * with a "Assert check failed" message.
	 *
	 * @param object to be checked if not null
	 */
	public static void checkNotNull(Object object)
	{
		if (object == null)
		{
			throw new IllegalArgumentException("Assert check failed");
		}
	}

	/**
	 * Check if the object argument is not null.<br>
	 * If null, throws an <code>IllegalArgumentException</code>
	 * with the exception message argument.
	 *
	 * @param object to be checked if not null
	 * @param exceptionMessage message to use if condition false
	 */
	public static void checkNotNull(Object object, String exceptionMessage)
	{
		if (object == null)
		{
			throw new IllegalArgumentException(exceptionMessage);
		}
	}
}
