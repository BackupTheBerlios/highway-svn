/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.debug;

import org.highway.helper.MethodHelper;
import org.highway.helper.PropertiesHelper;
import org.highway.helper.StackTraceHelper;
import org.highway.helper.StringHelper;
import org.highway.service.context.RequestContextHome;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;

/**
 * <p>Log4j implementation of DebugLog.</p>
 *
 * <p>To enable this DebugLog implementation, the following system property
 * must be set to true:</p>
 * <code>highway.debug.enable = true</code>
 *
 * <p>This implementation automaticaly calculates the exact location of
 * the call to the debug log. This location is added to the log messages.
 * The location format is:</p>
 *
 * <pre>somepackage.SomeClass.doSomething(SomeClass.java:123)</pre>
 *
 * <p>This class defines and uses default Log4j configuration properties
 * if no properties are passed to construct instances of this class.
 * This class default properties are:</p>
 * <pre>
 * log4j.rootLogger = DEBUG, stdout
 * log4j.appender.stdout = org.apache.log4j.ConsoleAppender
 * log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
 * log4j.appender.stdout.layout.ConversionPattern = &lt;default pattern&gt;</pre>
 *
 * <p>The default message pattern is defined by the <code>DEFAULT_PATTERN</code>
 * constant and is equal to:</p>
 *
 * <p><code>----> %d{HH:mm:ss:SSS} %p %t %m%n</code></p>
 *
 * <p>It is equivalent to:</p>
 *
 * <p><code>----> timestamp severity threadname message\n</code></p>
 *
 * <p>The message created by this class has the following format:</p>
 *
 * <p><code>userlogin\nmessage</code></p>
 *
 * <p>Exemple of debug log messages:</p>
 * <pre>
 * ----> 11:09:09:421 DEBUG main attias
 * ENTERING FournisseurAccess.mettreAJour(Fournisseur)
 * ----> 11:09:09:421 DEBUG main attias
 * VALUE DUMP in FournisseurAccess.mettreAJour(Fournisseur)
 * arg0 = Fournisseur@0
 * Fournisseur@0 = [dirty=false, isNew=true, nom=Auchan, adresse=Adresse@1]
 * Adresse@1 = [dirty=false, isNew=true, rue=12, rue de la paix, ville=Meudon]
 * ----> 11:09:09:421 DEBUG main attias
 * EXITING FournisseurAccess.mettreAJour(Fournisseur)
 * </pre>
 *
 * @author David Attias
 * @see org.highway.debug.Log4jDebugLog.DEFAULT_PATTERN
 */
public class Log4jDebugLog implements DebugLog, ServiceDebugLog
{
	private static final String ENTERING = "ENTERING ";
	private static final String EXITING = "EXITING ";
	private static final String MESSAGE = "MESSAGE in ";
	private static final String WARNING = "WARNING in ";
	private static final String ERROR = "ERROR in ";
	private static final String VALUE = "VALUE DUMP in ";
	private static final String RETURNED_VALUE = "Returned value = ";

	/**
	 * Default Log4j message pattern of this class.<br>
	 * If no specific Log4j properties are specified, this message pattern is used.
	 */
	public static final String DEFAULT_PATTERN =
		"----> %d{HH:mm:ss:SSS} %p %t %m%n";
	private static final int INITIAL_BUFFER_LENGTH = 250;
	private static final String DEBUG_LOGGER_PREFIX = "debug";
	private boolean enabled = false;
	private boolean useQualifiedClassNames = false;

	/**
	 * Constructs a Log4jDebugLog with this class default Log4j properties
	 * and non fully qualified names.
	 */
	public Log4jDebugLog()
	{
		this(false);
	}

	/**
	 * Constructs a Log4jDebugLog with this class default Log4j properties.
	 *
	 * @param useQualifiedClassNames indicate if log messages should
	 * use fully qualified class names
	 */
	public Log4jDebugLog(boolean useQualifiedClassNames)
	{
		this(null, useQualifiedClassNames);
	}

	/**
	 * Constructs a Log4jDebugLog configured with the specified properties.<br>
	 * If the specified properties object is null,
	 * this class default Log4j properties are used.
	 *
	 * @param properties the properties to configure Log4j
	 * @param useQualifiedClassNames indicate if log messages should
	 * use fully qualified class names
	 */
	public Log4jDebugLog(Properties properties, boolean useQualifiedClassNames)
	{
		enabled =
			PropertiesHelper.toBoolean(
				System.getProperty(ENABLE_SYSTEM_PROPERTY));

		if (enabled)
		{
			if (properties == null)
			{
				properties = new Properties();
				properties.put(
					"log4j.logger." + DEBUG_LOGGER_PREFIX, "DEBUG, stdout");
				properties.put(
					"log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
				properties.put(
					"log4j.appender.stdout.layout",
					"org.apache.log4j.PatternLayout");
				properties.put(
					"log4j.appender.stdout.layout.ConversionPattern",
					DEFAULT_PATTERN);
			}
			else
			{
				Properties newProperties = new Properties();
				Enumeration keys = properties.propertyNames();

				while (keys.hasMoreElements())
				{
					String key = (String) keys.nextElement();

					if (key.startsWith("log4j.logger"))
					{
						newProperties.setProperty(
							"log4j.logger." + DEBUG_LOGGER_PREFIX
							+ key.substring(12), properties.getProperty(key));
					}
					else if (key.startsWith("log4j.rootLogger"))
					{
						newProperties.setProperty(
							"log4j.logger." + DEBUG_LOGGER_PREFIX
							+ key.substring(16), properties.getProperty(key));
					}
					else
					{
						newProperties.setProperty(
							key, properties.getProperty(key));
					}
				}

				properties = newProperties;
			}

			PropertyConfigurator.configure(properties);
			this.useQualifiedClassNames = useQualifiedClassNames;
		}
	}

	/////////
	///////// debug
	/////////
	public boolean isEnabled()
	{
		return enabled;
	}

	public void debug(Object message)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(MESSAGE, stackElement);
			buffer.append('\n').append(message);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debug(Object msgPartA, Object msgPartB)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(MESSAGE, stackElement);
			buffer.append('\n').append(msgPartA).append(msgPartB);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debug(Object msgPartA, Object msgPartB, Object msgPartC)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(MESSAGE, stackElement);
			buffer.append('\n').append(msgPartA).append(msgPartB).append(
				msgPartC);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(MESSAGE, stackElement);
			buffer.append('\n').append(msgPartA).append(msgPartB).append(
				msgPartC);
			buffer.append(msgPartD);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debug(
		Object msgPartA, Object msgPartB, Object msgPartC, Object msgPartD,
		Object msgPartE)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(MESSAGE, stackElement);
			buffer.append('\n').append(msgPartA).append(msgPartB).append(
				msgPartC);
			buffer.append(msgPartD).append(msgPartE);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debugEnter()
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(ENTERING, stackElement);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	/////////
	///////// debugExit
	/////////
	public void debugExit()
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(EXITING, stackElement);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debugExit(Object returnedValue)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(EXITING, stackElement);
			buffer.append('\n').append(RETURNED_VALUE);
			dumpObject(buffer, returnedValue);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debugExit(int returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(long returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(short returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(byte returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(float returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(double returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(char returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	public void debugExit(boolean returnedValue)
	{
		if (enabled)
		{
			debugExit(StringHelper.toString(returnedValue));
		}
	}

	/////////
	///////// debugValue
	/////////
	public void debugValue(String valueName, Object value)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(VALUE, stackElement);
			buffer.append('\n').append(valueName).append(" = ");
			dumpObject(buffer, value);
			getLog4jLogger(stackElement).debug(buffer.toString());
		}
	}

	public void debugValue(String valueName, short value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, int value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, long value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, byte value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, boolean value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, char value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, double value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	public void debugValue(String valueName, float value)
	{
		if (enabled)
		{
			debugValue(valueName, StringHelper.toString(value));
		}
	}

	/////////
	///////// interceptor debug methods
	/////////
	public void debugServiceEnter(Method method)
	{
		if (enabled)
		{
			StringBuffer buffer = beginMessage(ENTERING, method);
			getLog4jLogger(method.getDeclaringClass()).debug(buffer.toString());
		}
	}

	public void debugServiceParameter(
		Method method, String parameterName, Object parameterValue)
	{
		if (enabled)
		{
			StringBuffer buffer = beginMessage(VALUE, method);
			buffer.append('\n').append(parameterName).append(" = ");
			dumpObject(buffer, parameterValue);
			getLog4jLogger(method.getDeclaringClass()).debug(buffer.toString());
		}
	}

	public void debugServiceExit(Method method)
	{
		if (enabled)
		{
			StringBuffer buffer = beginMessage(EXITING, method);
			getLog4jLogger(method.getDeclaringClass()).debug(buffer.toString());
		}
	}

	public void debugServiceExit(Method method, Object returnedValue)
	{
		if (enabled)
		{
			StringBuffer buffer = beginMessage(EXITING, method);
			buffer.append('\n').append(RETURNED_VALUE);
			dumpObject(buffer, returnedValue);
			getLog4jLogger(method.getDeclaringClass()).debug(buffer.toString());
		}
	}

	public void debugServiceError(Method method, Throwable throwable)
	{
		if (enabled)
		{
			StringBuffer buffer = beginMessage(ERROR, method);
			Logger logger = getLog4jLogger(method.getDeclaringClass());
			logger.error(buffer.toString(), throwable);
		}
	}

	/////////
	///////// info
	/////////
	public void info(Object message)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(MESSAGE, stackElement);
			buffer.append('\n').append(message);
			getLog4jLogger(stackElement).info(buffer.toString());
		}
	}

	public void warn(Object message)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(WARNING, stackElement);
			buffer.append('\n').append(message);
			getLog4jLogger(stackElement).warn(buffer.toString());
		}
	}

	public void warn(Throwable throwable)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(WARNING, stackElement);
			getLog4jLogger(stackElement).warn(buffer.toString(), throwable);
		}
	}

	public void warn(Object message, Throwable throwable)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(ERROR, stackElement);
			buffer.append('\n').append(message);
			getLog4jLogger(stackElement).warn(buffer.toString(), throwable);
		}
	}

	public void error(Object message)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(ERROR, stackElement);
			buffer.append('\n').append(message);
			getLog4jLogger(stackElement).error(buffer.toString());
		}
	}

	public void error(Throwable throwable)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(ERROR, stackElement);
			getLog4jLogger(stackElement).error(buffer.toString(), throwable);
		}
	}

	public void error(Object message, Throwable throwable)
	{
		if (enabled)
		{
			String stackElement = getInvokingStackElement();
			StringBuffer buffer = beginMessage(ERROR, stackElement);
			buffer.append('\n').append(message);
			getLog4jLogger(stackElement).error(buffer.toString(), throwable);
		}
	}

	/*******************************
	 ********* Implémentation *********
	 *******************************/
	private String getInvokingStackElement()
	{
		return StackTraceHelper.getInvokingStackTraceElement(
			DebugLog.class.getPackage());
	}

	/**
	 * Method getLog4jLogger
	 * @param stackElement String
	 * @return org.apache.log4j.Logger
	 */
	private Logger getLog4jLogger(String stackElement)
	{
		String className = StackTraceHelper.getFullClassName(stackElement);

		if (className == null)
		{
			return Logger.getLogger(DEBUG_LOGGER_PREFIX);
		}
		else
		{
			return Logger.getLogger(DEBUG_LOGGER_PREFIX + "." + className);
		}
	}

	/**
	 * Method getLog4jLogger
	 * @param clazz Class
	 * @return org.apache.log4j.Logger
	 */
	private Logger getLog4jLogger(Class clazz)
	{
		if (clazz == null)
		{
			return Logger.getLogger(DEBUG_LOGGER_PREFIX);
		}
		else
		{
			return Logger.getLogger(DEBUG_LOGGER_PREFIX + "." + clazz.getName());
		}
	}

	/**
	 * Method beginMessage
	 * @param messageType String
	 * @param method Method
	 * @return StringBuffer
	 */
	private StringBuffer beginMessage(String messageType, Method method)
	{
		StringBuffer buffer = beginMessage(messageType);
		MethodHelper.dumpClassAndMethodName(
			buffer, method, useQualifiedClassNames);

		return buffer;
	}

	/**
	 * Method beginMessage
	 * @param messageType String
	 * @param stackElement String
	 * @return StringBuffer
	 */
	private StringBuffer beginMessage(String messageType, String stackElement)
	{
		StringBuffer buffer = beginMessage(messageType);

		if (useQualifiedClassNames)
		{
			buffer.append(stackElement);
		}
		else
		{
			buffer.append(StackTraceHelper.getClassName(stackElement));
			buffer.append('.');
			buffer.append(StackTraceHelper.getMethodName(stackElement));
			buffer.append("(...)");
		}

		return buffer;
	}

	/**
	 * Method appendUserLogin
	 * @param buffer StringBuffer
	 * @return StringBuffer
	 */
	private StringBuffer beginMessage(String messageType)
	{
		StringBuffer buffer = new StringBuffer(INITIAL_BUFFER_LENGTH);
		buffer.append(RequestContextHome.getUserLogin());

		return buffer.append('\n').append(messageType);
	}

	/**
	 * Method dumpObject
	 * @param buffer StringBuffer
	 * @param object Object
	 */
	private void dumpObject(StringBuffer buffer, Object object)
	{
		if ((object != null) && ObjectDumper.isDumpable(object))
		{
			ObjectDumper dumper =
				new ObjectDumper(buffer, object, useQualifiedClassNames);
			dumper.dumpHeader();
			buffer.append('\n');
			dumper.dumpBody();
		}
		else
		{
			buffer.append(object);
		}
	}
}
