/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

/**
 * Provides access to the default GlobalValidator.
 * The standard default GlobalValidator is a JavaBeanGlobalValidator.
 *
 * @since 1.2
 * @author David Attias
 * @see org.highway.validate.GlobalValidator
 * @see org.highway.validate.JavaBeanGlobalValidator
 */
public final class ValidateHome
{
	/**
	 * Default GlobalValidator.
	 */
	private static GlobalValidator globalValidator =
		new JavaBeanGlobalValidator();

	/**
	 * Returns the default GlobalValidator.
	 * Returns null if no default GlobalValidator is set.
	 */
	public static GlobalValidator getGlobalValidator()
	{
		return globalValidator;
	}

	/**
	 * Sets the default GlobalValidator.
	 */
	public static void setGlobalValidator(GlobalValidator validator)
	{
		globalValidator = validator;
		DebugHome.getDebugLog().info(
			"Default GlobalValidator is set to " + validator);
	}

	/**
	 * Delegates to the default GlobalValidator.
	 * @throws InitException if no default GlobalValidator is set
	 * @see GlobalValidator#validate(Object, ValidateContext)
	 */
	public static ValidateContext validate(
		Object object, ValidateContext context)
	{
		if (globalValidator == null)
		{
			throw new InitException("no default GlobalValidator set");
		}

		return globalValidator.validate(object, context);
	}

	/**
	 * Do not instantiate this class.
	 */
	private ValidateHome()
	{
		throw new DoNotInstantiateException();
	}
}
