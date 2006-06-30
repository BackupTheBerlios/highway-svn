/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 5 nov. 2004
 */
package org.highway.exception;


/**
 * 
 */
public class ThrowHelper
{
	/**
	 * Constructor for ThrowHelper
	 */
	private ThrowHelper()
	{
	}

	/**
	 * Method wrap
	 * @param message String
	 * @return TechnicalException
	 */
	public static TechnicalException wrap(String message)
	{
		return new TechnicalException(message);
	}

	/**
	 * Method wrap
	 * @param message String
	 * @param exc Exception
	 * @return TechnicalException
	 */
	public static TechnicalException wrap(String message, Exception exc)
	{
		//		if (exc instanceof TechnicalException)
		//		{
		//			((TechnicalException)exc).stackMessage(message);
		//			return (TechnicalException)exc;
		//		}
		//		else return new TechnicalException(message, exc);
		return null;
	}

	/**
	 * Method wrapBug
	 * @param message String
	 * @return BugException
	 */
	public static BugException wrapBug(String message)
	{
		return new BugException(message);
	}
}
