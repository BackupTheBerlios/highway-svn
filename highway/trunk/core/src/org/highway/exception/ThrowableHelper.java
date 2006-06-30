/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 */
public class ThrowableHelper
{
	/**
	 * Retourne la trace d'exécution complète de l'objet de type
	 * <code>Throwable</code> passé en paramètre.<br>
	 * <br>
	 * Exemple :<br>
	 * <pre>
	 * java.lang.reflect.InvocationTargetException
	 *     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	 *     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	 *     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	 *     at java.lang.reflect.Method.invoke(Method.java:324)
	 *     ...
	 * </pre>
	 *
	 * @param throwable Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable throwable)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);

		return sw.toString();
	}

	/**
	 * Retourne la trace d'exécution sans le message de l'objet
	 * de type <code>Throwable</code> passé en paramètre.<br>
	 * <br>
	 * Exemple :<br>
	 * <pre>
	 *     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	 *     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	 *     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	 *     at java.lang.reflect.Method.invoke(Method.java:324)
	 *     ...
	 * </pre>
	 *
	 * @param throwable Throwable
	 * @return String
	 */
	public static String getStackTraceOnly(Throwable throwable)
	{
		String stackTrace = getStackTrace(throwable);

		if (stackTrace.indexOf("\tat") != -1)
		{
			return stackTrace.substring(stackTrace.indexOf("\tat"));
		}
		else
		{
			return "";
		}
	}

	/**
	 * Method <code>printCauseStackTrace</code>
	 *
	 * @param cause Throwable
	 * @param writer PrintWriter
	 */
	public static void printCauseStackTrace(
		Throwable cause, PrintWriter writer)
	{
		if (cause != null)
		{
			writer.println();
			writer.println("------------- caused by -------------");
			writer.println();
			cause.printStackTrace(writer);
		}
	}

	/**
	 * Returns the cause throwable nested into a throwable.<br>
	 * Necessary to ease the migration from JDK 1.3 to JDK 1.4
	 * because <code>Throwable.getCause()</code> only exists in JDK 1.4.
	 */
	public static Throwable getCause(Throwable throwable)
	{
		return throwable.getCause();
	}
}
