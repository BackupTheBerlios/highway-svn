/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;


/**
 * @author attias
 */
public interface SocleThrowable
{
	/**
	 * Method getUserMessage
	 * @return UserMessage
	 */
	UserMessage getUserMessage();

	/**
	 * Method getCause
	 * @return Throwable
	 */
	Throwable getCause();
}
