/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;

import org.highway.bean.PropertySizeHome;
import org.highway.debug.DebugHome;

/**
 * Validates the size of an object.
 * How the size of an object is calculated depends on the object type.
 * We use:
 * <pre>
 *         String.length()
 *         Object[].length, int[].length, ...
 *         Collection.size()
 *         Map.size()
 * </pre>
 *
 * @since 1.2
 * 
 */
public class SizeValidator implements Validator
{
	private Integer max;
	private Integer min;
	private String message;
	private boolean warning;

	/**
	 * Constructs a SizeValidator with the specified maximum size.
	 * @param max the maximum size
	 */
	public SizeValidator(int max)
	{
		this(new Integer(max));
	}

	/**
	 * Constructs a SizeValidator with the specified maximum size.
	 * @param max the maximum size
	 */
	public SizeValidator(Integer max)
	{
		this(null, max);
	}

	/**
	 * Constructs a SizeValidator with the specified minimum
	 * and maximum size.
	 * @param min the minimum size
	 * @param max the maximum size
	 */
	public SizeValidator(int min, int max)
	{
		this(new Integer(min), new Integer(max));
	}

	/**
	 * Constructs a SizeValidator with the specified minimum
	 * and maximum size.
	 * @param min the minimum size
	 * @param max the maximum size
	 */
	public SizeValidator(Integer min, Integer max)
	{
		this(min, max, false, null);
	}

	/**
	 * Constructs a SizeValidator with the specified minimum
	 * and maximum size.
	 * @param min the minimum size
	 * @param max the maximum size
	 * @param warning the type of problem to create
	 * @param message the problem message to use
	 */
	public SizeValidator(
		Integer min, Integer max, boolean warning, String message)
	{
		this.max = max;
		this.min = min;
		this.message = message;
		this.warning = warning;
	}

	/**
	 * Validates that the specified object size is greater or equal to
	 * this SizeValidator minimum size and lesser or equal to this
	 * SizeValidator maximum size. Adds problems to the validation context
	 * if it finds a size problem.
	 *
	 * @throws IllegalArgumentException if the specified object has an
	 * unknown type.
	 */
	public ValidateContext validate(Object object, ValidateContext context)
	{
		try
		{
			// this throws an IllegalArgumentException if PropertySizeHome
			// does not know how to size the specified object
			int size = PropertySizeHome.size(object);

			if (
				((min != null) && (size < min.intValue()))
					|| ((max != null) && (size > max.intValue())))
			{
				if (message == null)
				{
					context.addProblem(this.getClass(), warning);
				}
				else
				{
					context.addProblem(this.getClass(), warning, message);
				}
			}
			
		}
		catch (IllegalArgumentException exc)
		{
			// no size = no validation problem
			DebugHome.getDebugLog().debug(exc);
		}

		return context;
	}
	
}