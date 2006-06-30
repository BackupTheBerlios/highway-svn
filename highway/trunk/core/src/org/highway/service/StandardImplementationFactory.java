/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service;

import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;

/**
 * Standard implementation of ImplementationFactory.
 *
 * @since 1.2
 * 
 */
public class StandardImplementationFactory implements ImplementationFactory
{
	/**
	 * Implementation class suffix to use.
	 */
	private String implemClassSuffix;

	/**
	 * Field DEFAULT_IMPLEM_CLASS_SUFFIX<br>
	 * (value is "Impl")
	 */
	public static final String DEFAULT_IMPLEM_CLASS_SUFFIX = "Impl";

	/**
	 * Instantiate a StandardImplementationFactory that uses the default
	 * implementation class suffix.
	 */
	public StandardImplementationFactory()
	{
		this(DEFAULT_IMPLEM_CLASS_SUFFIX);
	}

	/**
	 * Instantiate a StandardImplementationFactory.
	 * @param implemClassSuffix the class suffix to use to find the
	 * implementation class
	 */
	public StandardImplementationFactory(String implemClassSuffix)
	{
		this.implemClassSuffix = implemClassSuffix;
	}

	/**
	 * Concatenates the Service interface class name and implementation class
	 * suffix of this factory to get the name of the implementation class.
	 * Loads, instantiate and returns the implementation class.
	 */
	public Service newImplementation(Class serviceClass)
	{
		if (! Service.class.isAssignableFrom(serviceClass))
		{
			throw new IllegalArgumentException(
				serviceClass.getName() + " does not extend "
				+ Service.class.getName());
		}

		try
		{
			String implClassName = serviceClass.getName() + implemClassSuffix;
			Object implem = ClassHelper.newInstance(implClassName);

			if (! serviceClass.isAssignableFrom(implem.getClass()))
			{
				throw new TechnicalException(
					"implementation class " + implClassName
					+ " does not implement " + serviceClass.getName());
			}

			return (Service) implem;
		}
		catch (ClassNotFoundException exc)
		{
			throw new TechnicalException(
				"implementation of service " + serviceClass.getName()
				+ " not found", exc);
		}
	}
}
