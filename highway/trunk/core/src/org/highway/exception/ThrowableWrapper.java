/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * This class is used to wrap a cause exception and avoid exceptions
 * to be sent to other process (in a distributed architecture) that do
 * not have their class.<br>
 * <br>
 * It is deprecated because it is complex and there is a problem in the
 * stack trace management.
 *
 * 
 * @deprecated
 */
class ThrowableWrapper extends Throwable implements SocleThrowable
{
	private String realClassName;
	private UserMessage userMessage;

	/**
	 * Field stackTrace
	 */
	private String stackTrace;

	/**
	 * Field cause
	 */
	private ThrowableWrapper cause;

	/**
	 * Constructor for ThrowableWrapper
	 * @param throwable Throwable
	 */
	ThrowableWrapper(Throwable throwable)
	{
		super(throwable.getMessage());

		this.realClassName = throwable.getClass().getName();

		this.stackTrace = ThrowableHelper.getStackTrace(throwable);

		if (throwable instanceof SocleThrowable)
		{
			SocleThrowable socleThrowable = (SocleThrowable) throwable;
			this.userMessage = socleThrowable.getUserMessage();
		}

		Throwable cause = ThrowableHelper.getCause(throwable);

		if (cause != null)
		{
			this.cause =
				(cause instanceof ThrowableWrapper) ? (ThrowableWrapper) cause
													: new ThrowableWrapper(
					cause);
		}
	}

	/**
	 * Method fillInStackTrace
	 * @return Throwable
	 */
	public Throwable fillInStackTrace()
	{
		return this;
	}

	/**
	 * Method getRealClassName
	 * @return String
	 */
	public String getRealClassName()
	{
		return this.realClassName;
	}

	/**
	 * Method getUserMessage
	 * @return UserMessage
	 * @see org.highway.exception.SocleThrowable#getUserMessage()
	 */
	public UserMessage getUserMessage()
	{
		if (this.userMessage != null)
		{
			return this.userMessage;
		}

		if (this.cause != null)
		{
			return this.cause.getUserMessage();
		}

		return null;
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
		writer.println(this.stackTrace);
		ThrowableHelper.printCauseStackTrace(this.cause, writer);
		writer.flush();
	}

	/**
	 * Method toString
	 * @return String
	 */
	public String toString()
	{
		String message = getLocalizedMessage();

		return (message != null) ? (this.realClassName + ": " + message)
								 : this.realClassName;
	}
}
