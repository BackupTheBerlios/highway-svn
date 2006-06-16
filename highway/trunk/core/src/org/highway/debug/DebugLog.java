/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.debug;


/**
 * <p>This interface provides debug log services.
 * Different implementations are available.
 * The default DebugLog must be set with the package home class.
 * Developers must use a DebugLog to provide debug information
 * easy to read. It will help to fix bugs.
 * When the DebugLog is disabled, its methods do nothing.</p>
 *
 * Example:<br>
 * <pre>
 * public int payBill(Bill bill)
 * {
 *     DebugHome.getDebugLog().debugEnter();
 *     DebugHome.getDebugLog().debugValue("bill", bill);
 *     ...
 *     int result = ...
 *     ...
 *     DebugHome.getDebugLog().debugExit(result);
 * }
 * </pre>
 *
 * <p>To enable any DebugLog implementation, the following system property
 * must be set to true:</p>
 * <code>highway.debug.enable = true</code>
 *
 * @author David Attias
 */
public interface DebugLog
{
	/**
	 * System property to set to true to enable any DebugLog implementation.
	 */
	public String ENABLE_SYSTEM_PROPERTY = "highway.debug.enable";

	/**
	 * Checks if this DebugLog is enabled.
	 */
	public boolean isEnabled();

	/**
	 * Puts a message in the debug log.<br>
	 * The <code>Object.toString</code> method is used to get the string
	 * from the message object.
	 *
	 * @param message message to log
	 */
	public void debug(Object message);

	/**
	 * Puts a message in the debug log.<br>
	 * The message parts avec converted with <code>Object.toString()</code>.<br>
	 * The message parts are concatenated to construct the final message.
	 *
	 * @param msgPartA first part of the message to log
	 * @param msgPartB second part of the message to lo
	 */
	public void debug(Object msgPartA, Object msgPartB);

	/**
	 * Puts a message in the debug log.<br>
	 * The message parts avec converted with <code>Object.toString()</code>.<br>
	 * The message parts are concatenated to construct the final message.
	 *
	 * @param msgPartA first part of the message to log
	 * @param msgPartB second part of the message to log
	 * @param msgPartC third part of the message to log
	 */
	public void debug(Object msgPartA, Object msgPartB, Object msgPartC);

	/**
	 * Puts a message in the debug log.<br>
	 * The message parts avec converted with <code>Object.toString()</code>.<br>
	 * The message parts are concatenated to construct the final message.
	 *
	 * @param msgPartA first part of the message to log
	 * @param msgPartB second part of the message to log
	 * @param msgPartC third part of the message to log
	 * @param msgPartD fourth part of the message to log
	 */
	public void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD);

	/**
	 * Puts a message in the debug log.<br>
	 * The message parts avec converted with <code>Object.toString()</code>.<br>
	 * The message parts are concatenated to construct the final message.
	 *
	 * @param msgPartA first part of the message to log
	 * @param msgPartB second part of the message to log
	 * @param msgPartC third part of the message to log
	 * @param msgPartD fourth part of the message to log
	 * @param msgPartE fifth part of the message to log
	 */
	public void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD,
		Object msgPartE);

	/**
	 * Puts an entering method message in the debug log.<br>
	 * The calling method is the entering method.<br>
	 * The message contains the fully qualified name of the
	 * entering method.
	 */
	public void debugEnter();

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method.
	 */
	public void debugExit();

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(Object returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(int returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(long returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(short returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(byte returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(float returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(double returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(char returnedValue);

	/**
	 * Puts an exiting method message in the debug log.<br>
	 * The calling method is the exiting method.<br>
	 * The message contains the fully qualified name of the
	 * exiting method and a dump of its returned value.
	 *
	 * @param returnedValue value returned by exiting method
	 */
	public void debugExit(boolean returnedValue);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, Object value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, short value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, int value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, long value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, byte value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, boolean value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, char value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, double value);

	/**
	 * Dumps a value in the debug log.<br>
	 * The dump form is specific to the implementation.
	 *
	 * @param valueName java source name of the value to dump
	 * @param value value to dump
	 */
	public void debugValue(String valueName, float value);

	/**
	 * Logs an info message.
	 *
	 * @param message message to log
	 */
	public void info(Object message);

	/**
	 * Logs a warning message.
	 *
	 * @param message message to log
	 */
	public void warn(Object message);

	/**
	 * Logs a warning message.
	 *
	 * @param throwable error to log
	 */
	public void warn(Throwable throwable);

	/**
	 * Logs a warning message.
	 *
	 * @param message message to log
	 * @param throwable error to log
	 */
	public void warn(Object message, Throwable throwable);

	/**
	 * Logs an error message.
	 *
	 * @param message message to log
	 */
	public void error(Object message);

	/**
	 * Logs an error message.
	 *
	 * @param throwable error to log
	 */
	public void error(Throwable throwable);

	/**
	 * Logs an error message.
	 *
	 * @param message message to log
	 * @param throwable error to log
	 */
	public void error(Object message, Throwable throwable);
}
