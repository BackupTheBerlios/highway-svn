/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Root of functional exception hierarchie.
 *
 * 
 */
public abstract class FunctionalException extends Exception
	implements SocleThrowable
{
	private ThrowableWrapper cause;

	/**
	 * Constructor for FunctionalException
	 */
	public FunctionalException()
	{
		this(null, null);
	}

	/**
	 * Constructor for FunctionalException
	 * @param message String
	 */
	public FunctionalException(String message)
	{
		this(message, null);
	}

	/**
	 * Constructor for FunctionalException
	 * @param cause Throwable
	 */
	public FunctionalException(Throwable cause)
	{
		this(null, cause);
	}

	/**
	 * Constructor for FunctionalException
	 * @param message String
	 * @param cause Throwable
	 */
	public FunctionalException(String message, Throwable cause)
	{
		super(message);

		if (cause != null)
		{
			this.cause = new ThrowableWrapper(cause);
		}
	}

	/**
	 * Method getUserMessage
	 * @return UserMessage
	 * @see org.highway.exception.SocleThrowable#getUserMessage()
	 */
	public UserMessage getUserMessage()
	{
		return (this.cause == null) ? null : this.cause.getUserMessage();
	}

	/**
	 * Method getCause
	 * @return Throwable
	 * @see org.highway.exception.SocleThrowable#getCause()
	 */
	public Throwable getCause()
	{
		return this.cause;
	}

	/**
	 * Method printStackTrace
	 * @param stream PrintStream
	 */
	public void printStackTrace(PrintStream stream)
	{
		printStackTrace(new PrintWriter(stream));
	}

	/**
	 * Method printStackTrace
	 * @param writer PrintWriter
	 */
	public void printStackTrace(PrintWriter writer)
	{
		super.printStackTrace(writer);
		ThrowableHelper.printCauseStackTrace(this.cause, writer);
	}
}
