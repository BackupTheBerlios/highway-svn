/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.highway.exception.TechnicalException;
import org.highway.helper.StackTraceHelper;

/**
 * <p>Log4j implementation</p>
 *
 * <p>This class defines and uses default Log4j configuration properties. These
 * class default properties are :
 *
 * <pre>
 * log4j.rootLogger = INFO, stdout
 * log4j.appender.stdout = org.apache.log4j.ConsoleAppender
 * log4j.appender.stdout.layout =
 *     org.highway.supervise.Log4jSupervisionLayout
 * log4j.renderer.org.highway.supervise.SupervisionMessage =
 *     org.highway.supervise.Log4jSupervisionRenderer
 * </pre>
 *
 * Properties provided by the user as constructor parameter are added to the
 * class default configuration. A user supplied property replaces the default
 * value if both exist.</p>
 *
 * <p>The class default properties listed below should NOT be overrided by user
 * supplied properties :
 * <pre>
 * log4j.appender.stdout.layout =
 *     org.highway.supervise.Log4jSupervisionLayout
 * log4j.renderer.org.highway.supervise.SupervisionMessage =
 *     org.highway.supervise.Log4jSupervisionRenderer
 * </pre>
 * It is expected that <b><tt>log4j.appender.stdout</tt></b> is about the only
 * user supplied Log4j property.</p>
 *
 * <p>The user should provide two other properties in addition of Log4j
 * configuration :
 * <ul>
 * <li><tt>highway.supervise.application.code</tt> : application code</li>
 * <li><tt>highway.supervise.application.name</tt> : application name</li>
 * </ul></p>
 *
 * <p>A supervision message may contain up to 10 application specific keys.
 * Those keys are provided to the <tt>Log4jSupervisor</tt> through a
 * {@link AppSpecificKeyProvider} implemented by the application.</p>
 *
 * <p>Every message is logged with a Log4j {@link Level} that depends on the
 * message <tt>Severity</tt>, through the {@link Severity#toLevel()}
 * method.</p>
 *
 * <p>Example of supervision log message :
 *
 * <pre>
 * 2005/04/28;10:58:52:23;PAG000004W;FLORE;PORTAILAGENCE;1;10.0.178.53;0GM10D;E;#ERREUR APPLICATIVE
 * java.lang.Throwable
 *         at test.supervise.SuperviseTest.run(SuperviseTest.java:66)
 *         at test.supervise.SuperviseTest.main(SuperviseTest.java:26)
 * </pre>
 * </p>
 *
 * @author attias
 * @author Christian de Bevotte
 */
public class Log4jSupervisor implements Supervisor
{
	/**
	 * Constant for the application code property (value is
	 * {@value #APPLICATION_CODE}).
	 * 
	 * @since 1.1
	 */
	public static final String APPLICATION_CODE =
		"highway.supervise.application.code";

	/**
	 * Constant for the application name property (value is
	 * {@value #APPLICATION_NAME}).
	 * 
	 * @since 1.1
	 */
	public static final String APPLICATION_NAME =
		"highway.supervise.application.name";

	/**
	 * Constant for the prefix of supervision {@link Logger} instances name
	 * (value is {@value #SUPERVISE_LOGGER_PREFIX}).
	 * 
	 * @since 1.1
	 */
	private static final String SUPERVISE_LOGGER_PREFIX = "supervise";
	
	private Map catalog;
	
	private Properties properties;
	
	private AppSpecificKeyProvider keyProvider;

	/**
	 * Constructs a <tt>Log4jSupervisor</tt> with the specified messages
	 * catalog and properties.
	 *
	 * @param catalog a {@link Collection} of {@link SupervisionMessage}
	 *        instances.
	 * @param properties the {@link Properties} to configure this
	 *        <tt>Log4jSupervisor</tt>.
	 * @since 1.1
	 */
	public Log4jSupervisor(Collection catalog, Properties properties)
	{
		this(catalog, properties, null);
	}

	/**
	 * Constructs a <tt>Log4jSupervisor</tt> with the specified messages
	 * catalog, properties and key provider.
	 *
	 * @param catalog a {@link Collection} of {@link SupervisionMessage}
	 *        instances.
	 * @param properties the {@link Properties} to configure this
	 *        <tt>Log4jSupervisor</tt>.
	 * @param keyProvider the {@link AppSpecificKeyProvider} implemented by the
	 *        application to provide the application specific keys.
	 * @since 1.1
	 */
	public Log4jSupervisor(
		Collection catalog, Properties properties,
		AppSpecificKeyProvider keyProvider)
	{
		this.keyProvider = keyProvider;

		this.catalog = new HashMap(catalog.size());

		Iterator messages = catalog.iterator();

		while (messages.hasNext())
		{
			SupervisionMessage message = (SupervisionMessage) messages.next();
			this.catalog.put(message.getMessageId(), message);
		}

		this.properties = new Properties();
		this.properties.put(
			"log4j.logger." + SUPERVISE_LOGGER_PREFIX, "INFO, stdout");
		this.properties.put(
			"log4j.renderer.org.highway.supervise.SupervisionMessage",
			"org.highway.supervise.Log4jSupervisionRenderer");
		this.properties.put(
			"log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		this.properties.put(
			"log4j.appender.stdout.layout",
			"org.highway.supervise.Log4jSupervisionLayout");

		if (properties != null)
		{
			Enumeration keys = properties.propertyNames();

			while (keys.hasMoreElements())
			{
				String key = (String) keys.nextElement();

				if (key.startsWith("log4j.logger"))
				{
					this.properties.setProperty(
						"log4j.logger." + SUPERVISE_LOGGER_PREFIX
						+ key.substring(12), properties.getProperty(key));
				}
				else if (key.startsWith("log4j.rootLogger"))
				{
					this.properties.setProperty(
						"log4j.logger." + SUPERVISE_LOGGER_PREFIX
						+ key.substring(16), properties.getProperty(key));
				}
				else
				{
					this.properties.setProperty(
						key, properties.getProperty(key));
				}
			}
		}

		PropertyConfigurator.configure(this.properties);

		Log4jSupervisionRenderer.hostname = getHostname();
		Log4jSupervisionRenderer.applicationCode =
			properties.getProperty(APPLICATION_CODE);
		Log4jSupervisionRenderer.applicationName =
			properties.getProperty(APPLICATION_NAME);
	}

	/**
	 * @throws IllegalArgumentException if message is not found in catalog.
	 * @since 1.1
	 */
	public void log(String messageId)
	{
		log(messageId, null, null);
	}

	/**
	 * @throws IllegalArgumentException if message is not found in catalog.
	 * @since 1.1
	 */
	public void log(String messageId, String debugInfo)
	{
		log(messageId, debugInfo, null);
	}

	/**
	 * @throws IllegalArgumentException if message is not found in catalog.
	 * @since 1.1
	 */
	public void log(String messageId, String debugInfo, Throwable throwable)
	{
		SupervisionMessage message =
			(SupervisionMessage) catalog.get(messageId);

		if (message == null)
		{
			throw new IllegalArgumentException(
				"No message found in catalog for messageId : " + messageId);
		}

		log(message, debugInfo, throwable);
	}

	/**
	 * @throws IllegalArgumentException if message is null or not found in
	 *         catalog.
	 * @since 1.1
	 */
	public void log(SupervisionMessage message)
	{
		log(message, null, null);
	}

	/**
	 * @throws IllegalArgumentException if message is null or not found in
	 *         catalog.
	 * @since 1.1
	 */
	public void log(SupervisionMessage message, String debugInfo)
	{
		log(message, debugInfo, null);
	}

	/**
	 * @throws IllegalArgumentException if message is null or not found in
	 *         catalog.
	 * @since 1.1
	 */
	public void log(
		final SupervisionMessage message, final String debugInfo,
		Throwable throwable)
	{
		if (message == null)
		{
			throw new IllegalArgumentException("message is null");
		}

		if (! catalog.containsKey(message.getMessageId()))
		{
			throw new IllegalArgumentException(
				"No message found in catalog for messageId : "
				+ message.getMessageId());
		}

		SupervisionMessage log4jMessage =
			new SupervisionMessage()
			{
				public String getMessageId()
				{
					return message.getMessageId();
				}

				public Severity getSeverity()
				{
					return message.getSeverity();
				}

				public String[] getKeys()
				{
					return (keyProvider == null) ? message.getKeys()
												 : keyProvider.getKeys(message);
				}

				public String getInfo()
				{
					return (debugInfo == null) ? message.getInfo()
											   : (message.getInfo()
					+ Layout.LINE_SEP + debugInfo);
				}
			};

		String stackElement = getInvokingStackElement();
		Logger logger = getLog4jLogger(stackElement);
		Severity severity = message.getSeverity();
		logger.log(severity.toLevel(), log4jMessage, throwable);
	}

	/**
	 * @since 1.1
	 */
	public Collection getCatalog()
	{
		return new ArrayList(catalog.values());
	}

	/**
	 * Returns the local host name.
	 *
	 * @return the local host name.
	 * @throws TechnicalException if the local host name could not be found.
	 */
	private String getHostname()
	{
		try
		{
			InetAddress host = InetAddress.getLocalHost();
			String hostname = host.getHostName();

			return hostname.toUpperCase();
		}
		catch (UnknownHostException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (SecurityException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	private String getInvokingStackElement()
	{
		return StackTraceHelper.getInvokingStackTraceElement(
			Supervisor.class.getPackage());
	}

	private Logger getLog4jLogger(String stackElement)
	{
		String className = StackTraceHelper.getFullClassName(stackElement);

		return (className == null) ? Logger.getLogger(SUPERVISE_LOGGER_PREFIX)
								   : Logger.getLogger(
			SUPERVISE_LOGGER_PREFIX + "." + className);
	}
}
