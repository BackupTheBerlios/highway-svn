/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

/**
 * Service package home class.
 * Provides access to the default ImplementationFactory.
 *
 * @since 1.2
 * 
 * @see org.highway.service.ImplementationFactory
 */
public class ServiceHome
{
	///////
	/////// Default ImplementationFactory
	///////

	/**
	 * Default ImplementationFactory
	 */
	private static ImplementationFactory implementationFactory =
		new StandardImplementationFactory();

	/**
	 * Sets the default ImplementationFactory.
	 */
	public static synchronized void setImplementationFactory(
		ImplementationFactory factory)
	{
		implementationFactory = factory;
		DebugHome.getDebugLog().info(
			"Default ImplementationFactory is set to " + factory);
	}

	/**
	 * Returns the default ImplementationFactory.
	 * Returns null if no default ImplementationFactory is set.
	 */
	public static ImplementationFactory getImplementationFactory()
	{
		return implementationFactory;
	}

	/**
	 * Delegates to the default ImplementationFactory.
	 * @throws InitException if no default ImplementationFactory is set
	 * @see ImplementationFactory#newImplementation(Class)
	 */
	public static Service newImplementation(Class serviceClass)
	{
		if (implementationFactory == null)
		{
			throw new InitException("no default ImplementationFactory set");
		}

		return implementationFactory.newImplementation(serviceClass);
	}

	///////
	/////// implem
	///////

	/**
	 * Do not instantiate this class.
	 */
	private ServiceHome()
	{
		throw new DoNotInstantiateException();
	}
}
