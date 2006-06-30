/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.debug;

import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

/**
 * Home class of the debug package.
 * It provides static methods to set and access the default DebugLog.
 * Do not instantiate this class.<br><br>
 *
 * Applications should create, configure and set the default DebugLog at
 * init time. Use the <code>setDebugLog</code> to set the default DebugLog.
 * The default default DebugLog is a SilentDebugLog instance.
 *
 * 
 * 
 */
public class DebugHome
{
	/**
	 * Default DebugLog object.
	 */
	private static DebugLog log = new SilentDebugLog();

	/**
	 * Do not instantiate this class.
	 */
	private DebugHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default DebugLog.
	 */
	public static DebugLog getDebugLog()
	{
		return log;
	}

	/**
	 * Returns the default DebugLog.
	 * @throws InitException if no default DebugLog is set.
	 */
	private static DebugLog getSafeDebugLog()
	{
		if (log == null)
		{
			throw new InitException("no default DebugLog set");
		}

		return log;
	}

	/**
	 * Sets the default DebugLog.
	 */
	public static synchronized void setDebugLog(DebugLog newDebugLog)
	{
		log = newDebugLog;

		getDebugLog().info("Default DebugLog is set to " + newDebugLog);
	}
	
	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#isEnabled()
	 * 
	 */
	public static boolean isEnabled()
	{
		return getSafeDebugLog().isEnabled();
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debug(Object)
	 * 
	 */
	public static void debug(Object message)
	{
		getSafeDebugLog().debug(message);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debug(Object, Object)
	 * 
	 */
	public static void debug(Object msgPartA, Object msgPartB)
	{
		getSafeDebugLog().debug(msgPartA, msgPartB);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debug(Object, Object, Object)
	 * 
	 */
	public static void debug(Object msgPartA, Object msgPartB, Object msgPartC)
	{
		getSafeDebugLog().debug(msgPartA, msgPartB, msgPartC);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debug(Object, Object, Object, Object)
	 * 
	 */
	public static void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD)
	{
		getSafeDebugLog().debug(msgPartA, msgPartB, msgPartC, msgPartD);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debug(Object, Object, Object, Object, Object)
	 * 
	 */
	public static void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD,
		Object msgPartE)
	{
		getSafeDebugLog().debug(msgPartA, msgPartB, msgPartC, msgPartD, msgPartE);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugEnter()
	 * 
	 */
	public static void debugEnter()
	{
		getSafeDebugLog().debugEnter();
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit()
	 * 
	 */
	public static void debugExit()
	{
		getSafeDebugLog().debugExit();
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(Object)
	 * 
	 */
	public static void debugExit(Object returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(boolean)
	 * 
	 */
	public static void debugExit(int returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(long)
	 * 
	 */
	public static void debugExit(long returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(short)
	 * 
	 */
	public static void debugExit(short returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(byte)
	 * 
	 */
	public static void debugExit(byte returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(float)
	 * 
	 */
	public static void debugExit(float returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(double)
	 * 
	 */
	public static void debugExit(double returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(char)
	 * 
	 */
	public static void debugExit(char returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugExit(boolean)
	 * 
	 */
	public static void debugExit(boolean returnedValue)
	{
		getSafeDebugLog().debugExit(returnedValue);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, Object)
	 * 
	 */
	public static void debugValue(String valueName, Object value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, short)
	 * 
	 */
	public static void debugValue(String valueName, short value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, int)
	 * 
	 */
	public static void debugValue(String valueName, int value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, long)
	 * 
	 */
	public static void debugValue(String valueName, long value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, byte)
	 * 
	 */
	public static void debugValue(String valueName, byte value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, boolean)
	 * 
	 */
	public static void debugValue(String valueName, boolean value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, char)
	 * 
	 */
	public static void debugValue(String valueName, char value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, double)
	 * 
	 */
	public static void debugValue(String valueName, double value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#debugValue(String, float)
	 * 
	 */
	public static void debugValue(String valueName, float value)
	{
		getSafeDebugLog().debugValue(valueName, value);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#info(Object)
	 * 
	 */
	public static void info(Object message)
	{
		getSafeDebugLog().info(message);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#warn(Object)
	 * 
	 */
	public static void warn(Object message)
	{
		getSafeDebugLog().warn(message);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#warn(Throwable)
	 * 
	 */
	public static void warn(Throwable throwable)
	{
		getSafeDebugLog().warn(throwable);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#warn(Object, Throwable)
	 * 
	 */
	public static void warn(Object message, Throwable throwable)
	{
		getSafeDebugLog().warn(message, throwable);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#error(Object)
	 * 
	 */
	public static void error(Object message)
	{
		getSafeDebugLog().error(message);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#error(Throwable)
	 * 
	 */
	public static void error(Throwable throwable)
	{
		getSafeDebugLog().error(throwable);
	}

	/**
	 * Delegates to the default DebugLog.
	 *
	 * @throws InitException if no default DebugLog is set.
	 * @see DebugLog#error(Object, Throwable)
	 * 
	 */
	public static void error(Object message, Throwable throwable)
	{
		getSafeDebugLog().error(message, throwable);
	}
}
