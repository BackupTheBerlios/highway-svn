/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Racine des exceptions techniques.
 *
 * 
 */
public class TechnicalException extends RuntimeException
	implements SocleThrowable
{
	/**
	 * Field cause
	 */
	private ThrowableWrapper cause;

	/**
	 * Constructor for TechnicalException
	 */
	public TechnicalException()
	{
		this(null, null);
	}

	/**
	 * Constructor for TechnicalException
	 * @param cause Throwable
	 */
	public TechnicalException(Throwable cause)
	{
		this(null, cause);
	}

	/**
	 * Constructor for TechnicalException
	 * @param message String
	 */
	public TechnicalException(String message)
	{
		this(message, null);
	}

	/**
	 * Constructor for TechnicalException
	 * @param message String
	 * @param cause Throwable
	 */
	public TechnicalException(String message, Throwable cause)
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
	 */
	public void printStackTrace()
	{
		printStackTrace(System.err);
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
		writer.flush();
	}

	/**
	 * Method main
	 * @param args String[]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		try
		{
			Object o = null;

			try
			{
				o.toString();
			}
			catch (Exception e)
			{
				throw new TechnicalException("Gotcha", e);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
