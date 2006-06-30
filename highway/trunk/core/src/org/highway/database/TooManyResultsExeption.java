/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database;

import org.highway.exception.TechnicalException;

/**
 * This exception is thrown when a select query returns more results than
 * expected.
 *
 * 
 * 
 * @see
 */
public class TooManyResultsExeption extends TechnicalException
{
	/**
	 * Default constructor.
	 */
	public TooManyResultsExeption()
	{
		super("the select query returned more results than expected");
	}
}
