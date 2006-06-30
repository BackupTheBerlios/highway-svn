/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.debug;

import java.lang.reflect.Method;

/**
 * The default debug log object must implement this interface if you want to
 * the log messages from the DebugInterceptor. If the default debug log does
 * not, no message from the debug interceptor is logged.<br>
 * <br>
 * The methods are specific debug log methods that take a method parameter
 * that specifies the service called.
 *
 * 
 * @see org.highway.DebugInterceptor
 */
public interface ServiceDebugLog
{
	/**
	 * Logs an entering service debug message.<br>
	 * The specified method represents the service about to be entered.
	 *
	 * @param method the service about to be entered
	 */
	public void debugServiceEnter(Method method);

	/**
	 * Logs a service parameter dump in the debug log.<br>
	 * The specified method represents the service about to be entered.
	 *
	 * @param method the service about to be entered
	 * @param parameterName the service parameter name
	 * @param parameterValue the service parameter value
	 */
	public void debugServiceParameter(
		Method method, String parameterName, Object parameterValue);

	/**
	 * Logs an exiting debug message.<br>
	 * The specified method represents the service the app just exited.
	 *
	 * @param method the service the app just exited
	 */
	public void debugServiceExit(Method method);

	/**
	 * Logs an exiting debug message with the dump of the returned value.<br>
	 * The specified method represents the service the app just exited.
	 *
	 * @param method the service the app just exited
	 * @param returnedValue the service returned value
	 */
	public void debugServiceExit(Method method, Object returnedValue);

	/**
	 * Logs an error message in the debug log.<br>
	 * The specified method represents the service the app just exited.
	 *
	 * @param method the service the app just exited
	 * @param throwable the error thrown by the service call
	 */
	public void debugServiceError(Method method, Throwable throwable);
}
