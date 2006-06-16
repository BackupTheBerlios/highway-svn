/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.init;

import org.highway.exception.TechnicalException;

/**
 * Thrown when an initialization problem is discovered. This includes
 * errors during application initialization, errors due to incorect
 * initilization, etc.
 *
 * @author attias
 */
public class InitException extends TechnicalException
{
	/**
	 * Construts an InitException with the specified detail massage.
	 * @param message
	 */
	public InitException(String message)
	{
		super(message);
	}

	/**
	 * Construts an InitException with the specified detail massage and cause.
	 * @param message
	 * @param cause
	 */
	public InitException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Construts an InitException with the specified cause.
	 * @param cause
	 */
	public InitException(Throwable cause)
	{
		super(cause);
	}
}
