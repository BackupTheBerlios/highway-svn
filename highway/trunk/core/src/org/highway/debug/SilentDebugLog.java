/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.debug;

import java.lang.reflect.Method;

/**
 * Silent implementation of the DebugLog interface.<br>
 * No debug message will be logged if this class is used.<br>
 * Use this implementation when you do not need any debug log.<br>
 * You can for example use this implementation in the production system.
 *
 * @author attias
 */
public class SilentDebugLog implements DebugLog, ServiceDebugLog
{
	/**
	 * Constructs a SilentDebugLog object.
	 */
	public SilentDebugLog()
	{
	}

	/**
	 * Returns false.
	 */
	public boolean isEnabled()
	{
		return false;
	}

	/**
	 * Does nothing.
	 */
	public void debug(Object message)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debug(Object msgPartA, Object msgPartB)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debug(Object msgPartA, Object msgPartB, Object msgPartC)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD,
		Object msgPartE)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugEnter()
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit()
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(Object returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(int returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(long returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(short returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(byte returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(float returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(double returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(char returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugExit(boolean returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, Object value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, short value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, int value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, long value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, byte value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, boolean value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, char value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, double value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugValue(String valueName, float value)
	{
	}

	/**
	 * Does nothing.
	 */
	public void info(Object message)
	{
	}

	/**
	 * Does nothing.
	 */
	public void warn(Object message)
	{
	}

	/**
	 * Does nothing.
	 */
	public void warn(Throwable throwable)
	{
	}

	/**
	 * Does nothing.
	 */
	public void warn(Object message, Throwable throwable)
	{
	}

	/**
	 * Does nothing.
	 */
	public void error(Object message)
	{
	}

	/**
	 * Does nothing.
	 */
	public void error(Throwable throwable)
	{
	}

	/**
	 * Does nothing.
	 */
	public void error(Object message, Throwable throwable)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugServiceEnter(Method method)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugServiceParameter(
		Method method, String parameterName, Object parameterValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugServiceExit(Method method)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugServiceExit(Method method, Object returnedValue)
	{
	}

	/**
	 * Does nothing.
	 */
	public void debugServiceError(Method method, Throwable throwable)
	{
	}
}
