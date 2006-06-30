/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.exception.DoNotInstantiateException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;

/**
 * 
 */
public class StackTraceHelper
{
	/**
	 * Field STACK_LINE_PREFIX<br>
	 * (value is ""\tat "")
	 */
	private static final String STACK_LINE_PREFIX = "\tat ";

	/**
	 * Field LINE_SEPARATOR
	 */
	private static final String LINE_SEPARATOR =
		System.getProperty("line.separator");

	/**
	 * Do not instantiate this class.
	 */
	private StackTraceHelper()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the complete stack trace of the <code>Throwable</code> parameter.<br>
	 * <br>
	 * Example :<br>
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

		return sw.toString().trim();
	}

	/**
	 * Returns the stack trace of the <code>Throwable</code> parameter
	 * without the message part.<br>
	 * <br>
	 * Example :<br>
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
		int firstLine = stackTrace.indexOf(STACK_LINE_PREFIX);

		return (firstLine == -1) ? "" : stackTrace.substring(firstLine);
	}

	/**
	 * Method getStackTraceElements
	 * @param throwable Throwable
	 * @return String[]
	 */
	public static String[] getStackTraceElements(Throwable throwable)
	{
		String stackTrace = getStackTraceOnly(throwable);
		StringTokenizer tokenizer = new StringTokenizer(stackTrace, "\t");
		String[] elements = new String[tokenizer.countTokens()];

		for (int i = 0; i < elements.length; i++)
		{
			// get rid of the first 3 characters
			// because every lines starts with "at "
			elements[i] = tokenizer.nextToken().substring(3).trim();
		}

		return elements;
	}

	/**
	 * Returns the stack element preceding the last stack element of the
	 * specified class. For example, if the stack is:
	 * <pre>
	 * org.highway.helper.StackTraceHelper.getInvokingStackTraceElement(StackTraceHelper.java:135)
	 * org.highway.helper.StackTraceHelper.getInvokingStackTraceElement(StackTraceHelper.java:116)
	 * org.highway.debug.Log4jDebugLog.getInvokingStackElement(Log4jDebugLog.java:520)
	 * org.highway.debug.Log4jDebugLog.error(Log4jDebugLog.java:497)
	 * org.highway.sample.SampleMain1.main(SampleMain1.java:34)</pre>
	 *
	 * And if this method has been invoked with the following parameter:
	 * <pre>org.highway.debug.Log4jDebugLog</pre>
	 *
	 * The returned stack element is:
	 * <pre>org.highway.sample.SampleMain1.main(SampleMain1.java:34)</pre>
	 *
	 * @param classInvoked the class to find in the stack
	 * @return the preceding stack element
	 */
	public static String getInvokingStackTraceElement(Class classInvoked)
	{
		return getInvokingStackTraceElement(classInvoked.getName());
	}

	/**
	 * Returns the stack element preceding the last stack element of the
	 * specified package. For example, if the stack is:
	 * <pre>
	 * org.highway.helper.StackTraceHelper.getInvokingStackTraceElement(StackTraceHelper.java:135)
	 * org.highway.helper.StackTraceHelper.getInvokingStackTraceElement(StackTraceHelper.java:116)
	 * org.highway.debug.Log4jDebugLog.getInvokingStackElement(Log4jDebugLog.java:520)
	 * org.highway.debug.Log4jDebugLog.error(Log4jDebugLog.java:497)
	 * org.highway.sample.SampleMain1.main(SampleMain1.java:34)</pre>
	 *
	 * And if this method has been invoked with the following parameter:
	 * <pre>org.highway.debug</pre>
	 *
	 * The returned stack element is:
	 * <pre>org.highway.sample.SampleMain1.main(SampleMain1.java:34)</pre>
	 *
	 * @param packageInvoked the package to find in the stack
	 * @return the preceding stack element
	 */
	public static String getInvokingStackTraceElement(Package packageInvoked)
	{
		return getInvokingStackTraceElement(packageInvoked.getName());
	}

	/**
	 * Returns the stack element preceding the last stack element of the
	 * specified package or class. For example, if the stack is:
	 * <pre>
	 * org.highway.helper.StackTraceHelper.getInvokingStackTraceElement(StackTraceHelper.java:135)
	 * org.highway.helper.StackTraceHelper.getInvokingStackTraceElement(StackTraceHelper.java:116)
	 * org.highway.debug.Log4jDebugLog.getInvokingStackElement(Log4jDebugLog.java:520)
	 * org.highway.debug.Log4jDebugLog.error(Log4jDebugLog.java:497)
	 * org.highway.sample.SampleMain1.main(SampleMain1.java:34)</pre>
	 *
	 * And if this method has been invoked with the following parameter:
	 * <pre>org.highway.debug</pre>
	 *
	 * The returned stack element is:
	 * <pre>org.highway.sample.SampleMain1.main(SampleMain1.java:34)</pre>
	 *
	 * Returns "Unavailable stack element" if the specified package or class
	 * is not found.
	 *
	 * @param invoked the package or class to find in the stack
	 * @return the preceding stack element
	 */
	private static String getInvokingStackTraceElement(String invoked)
	{
		boolean alreadyFoundInvoked = false;
		String[] elements = getStackTraceElements(new Throwable());

		for (int i = 0; i < elements.length; i++)
		{
			if (elements[i].startsWith(invoked))
			{
				alreadyFoundInvoked = true;
			}
			else if (alreadyFoundInvoked)
			{
				return elements[i];
			}
		}

		return "Unavailable stack element";
	}

	/**
	 * Method getClassAndMethodName
	 * @param stackElement String
	 * @return String
	 */
	public static String getClassAndMethodName(String stackElement)
	{
		return stackElement.substring(0, stackElement.indexOf('('));
	}

	/**
	 * Method getFullClassName
	 * @param stackElement String
	 * @return String
	 */
	public static String getFullClassName(String stackElement)
	{
		String classAndMethod = getClassAndMethodName(stackElement);

		return classAndMethod.substring(0, classAndMethod.lastIndexOf('.'));
	}

	/**
	 * Method getClassName
	 * @param stackElement String
	 * @return String
	 */
	public static String getClassName(String stackElement)
	{
		String fullClassName = getFullClassName(stackElement);

		return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
	}

	/**
	 * Method getMethodName
	 * @param stackElement String
	 * @return String
	 */
	public static String getMethodName(String stackElement)
	{
		String classAndMethod = getClassAndMethodName(stackElement);

		return classAndMethod.substring(classAndMethod.lastIndexOf('.') + 1);
	}

	/**
	 * Method getFileName
	 * @param stackElement String
	 * @return String
	 */
	public static String getFileName(String stackElement)
	{
		return stackElement.substring(
			stackElement.indexOf('(') + 1, stackElement.indexOf(':'));
	}

	/**
	 * Method getLineNumber
	 * @param stackElement String
	 * @return int
	 */
	public static int getLineNumber(String stackElement)
	{
		String lineNumberString =
			stackElement.substring(
				stackElement.indexOf(':') + 1, stackElement.indexOf(')'));

		return Integer.parseInt(lineNumberString);
	}

	/**
	 * Tests this helper methods.
	 * @param args String[]
	 */
	private static void main(String[] args)
	{
		try
		{
			System.getProperty(null);
		}
		catch (Exception e)
		{
			System.out.println("<--- getStackTrace ---> ");
			System.out.println(getStackTrace(e));
			System.out.println("<---------------------> ");
			System.out.println("<--- getStackTraceOnly ---> ");
			System.out.println(getStackTraceOnly(e));
			System.out.println("<---------------------> ");
			System.out.println("<--- getStackTraceElements ---> ");

			String[] elements = getStackTraceElements(e);

			for (int i = 0; i < elements.length; i++)
			{
				System.out.println(elements[i]);
			}

			System.out.println("<---------------------> ");
			System.out.println("<--- getClassName ---> ");
			System.out.println(getFullClassName(elements[0]));
			System.out.println("<---------------------> ");
			System.out.println("<--- getMethodName ---> ");
			System.out.println(getMethodName(elements[0]));
			System.out.println("<---------------------> ");
			System.out.println("<--- getFileName ---> ");
			System.out.println(getFileName(elements[0]));
			System.out.println("<---------------------> ");
			System.out.println("<--- getLineNumber ---> ");
			System.out.println("" + getLineNumber(elements[0]));
			System.out.println("<---------------------> ");
		}
	}
}
