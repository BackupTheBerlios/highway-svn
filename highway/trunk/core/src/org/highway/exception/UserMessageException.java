/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;

import java.util.List;

/**
 *
 * 
 */
public class UserMessageException extends TechnicalException
{
	/**
	 * Field userMessage
	 */
	private UserMessage userMessage;

	/**
	 * Constructor for UserMessageException
	 * @param message String
	 */
	public UserMessageException(String message)
	{
		this(message, null, false, null);
	}

	/**
	 * Constructor for UserMessageException
	 * @param message String
	 * @param warning boolean
	 */
	public UserMessageException(String message, boolean warning)
	{
		this(message, null, warning, null);
	}

	/**
	 * Constructor for UserMessageException
	 * @param message String
	 * @param parameterList List
	 * @param warning boolean
	 * @param cause Throwable
	 */
	public UserMessageException(
		String message, List parameterList, boolean warning, Throwable cause)
	{
		super(null, cause);
		this.userMessage = new UserMessage(message, parameterList, warning);
	}

	/**
	 * Method getMessage
	 * @return String
	 */
	public String getMessage()
	{
		return this.getClass().getName() + ", message="
		+ this.userMessage.getMessage() + ", parameterList="
		+ this.userMessage.getParameterList() + ", warning="
		+ this.userMessage.isWarning();
	}

	public UserMessage getUserMessage()
	{
		return userMessage;
	}
}
