/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ejbgen;

import org.highway.JavaHelper;
import xdoclet.XDocletException;
import java.util.Properties;

/**
 *
 * Specialization of the class for processing tags on methods to
 * add processings on exceptions returned by methods
 */
public class EjbGenTagsHandlerMethod
	extends xdoclet.tagshandler.MethodTagsHandler
{
	/**
	 * Name of the current exception
	 */
	private String currentException = null;
	
	private int index= 0;
	

	/**
	 * Generate for all exceptions of the current method
	 */
	public void forAllExceptions(String template, Properties attributes)
		throws XDocletException
	{
		String exceptionList = exceptionList(attributes);

		if (exceptionList.length() > 0)
		{
			// remove "throws " from exception list
			exceptionList = exceptionList.substring(7);

			// split into individual exceptions
			String[] exceptions = exceptionList.split(", ");

			for (int i = 0; i < exceptions.length; i++)
			{
				currentException = exceptions[i];

				if (currentException.length() > 0)
				{
					generate(template);
				}
			}
		}
	}

	public void forAllMethods(String template, Properties attributes) throws XDocletException {
		index=0;
		super.forAllMethods(template, attributes);
		index=0;
	}
	
	/**
	 * Incremente index with 1 and return the value
	 * @return l'index incrémenté.
	 */
	public String getIncrementIndex () throws XDocletException {
		index++;
		return Integer.toString(index) ;
	}

	/**
	 * Return the value of index without incrementation
	 * @return l'index incrémenté.
	 */
	public String getIndex () throws XDocletException {
		return Integer.toString(index) ;
	}

	
	/**
	 * Returns the name of the current exception
	 */
	public String currentException(Properties attributes)
		throws XDocletException
	{
		return currentException;
	}

	public void ifReturnsObject(String template, Properties attributes)
		throws XDocletException
	{
		if (! JavaHelper.isPrimitiveType(this.methodType(attributes)))
		{
			this.ifDoesntReturnVoid(template, attributes);
		}
	}

	public void ifReturnsPrimitive(String template, Properties attributes)
		throws XDocletException
	{
		if (JavaHelper.isPrimitiveType(this.methodType(attributes)))
		{
			generate(template);
		}
	}
}
