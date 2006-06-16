/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import java.util.Collection;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.init.InitException;

/**
 * Home class of the supervise package.<br>
 * Provides static access to the default supervisor and other static help
 * methods.
 * 
 * @author attias
 * @author Christian de Bevotte
 */
public class SuperviseHome
{
	/**
	 * Default Supervisor.
	 */
	private static Supervisor supervisor;

	/**
	 * Do not instantiate this class.
	 */
	private SuperviseHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default Supervisor.
	 */
	public static Supervisor getSupervisor()
	{
		return supervisor;
	}

	/**
	 * Returns the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set
	 */
	private static Supervisor getSafeSupervisor()
	{
		if (supervisor == null)
		{
			throw new InitException("No default Supervisor set");
		}

		return supervisor;
	}

	/**
	 * Sets the supervisor oject to be used as default
	 */
	public static synchronized void setSupervisor(Supervisor newSupervisor)
	{
		supervisor = newSupervisor;
		DebugHome.getDebugLog().info(
			"Default Supervisor is set to " + newSupervisor);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#log(String)
	 * @since 1.4.7
	 */
	public static void log(String messageId)
	{
		getSafeSupervisor().log(messageId);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#log(String, String)
	 * @since 1.4.7
	 */
	public static void log(String messageId, String debugInfo)
	{
		getSafeSupervisor().log(messageId, debugInfo);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#log(String, String, Throwable)
	 * @since 1.4.7
	 */
	public static void log(String messageId, String debugInfo,
		Throwable throwable)
	{
		getSafeSupervisor().log(messageId, debugInfo, throwable);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#log(SupervisionMessage)
	 * @since 1.4.7
	 */
	public static void log(SupervisionMessage message)
	{
		getSafeSupervisor().log(message);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#log(SupervisionMessage, String)
	 * @since 1.4.7
	 */
	public static void log(SupervisionMessage message, String debugInfo)
	{
		getSafeSupervisor().log(message, debugInfo);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#log(SupervisionMessage, String, Throwable)
	 * @since 1.4.7
	 */
	public static void log(SupervisionMessage message, String debugInfo,
		Throwable throwable)
	{
		getSafeSupervisor().log(message, debugInfo, throwable);
	}

	/**
	 * Delegates to the default Supervisor.
	 * 
	 * @throws InitException if no default Supervisor is set.
	 * @see Supervisor#getCatalog()
	 * @since 1.4.7
	 */
	public static Collection getCatalog()
	{
		return getSafeSupervisor().getCatalog();
	}
}
