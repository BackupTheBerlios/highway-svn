package org.highway.lifecycle;

import org.highway.exception.TechnicalException;

/**
 * Thrown when an initialization problem is discovered. This includes
 * errors during application initialization, errors due to incorect
 * initilization, etc.
 *
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
