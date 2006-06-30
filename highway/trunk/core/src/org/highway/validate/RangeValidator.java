/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;


/**
 * Validates Comparable objects to a minimum and a maximum.
 *
 * @since 1.2
 * 
 * @see java.lang.Comparable
 */
public class RangeValidator implements Validator
{
	private Comparable min;
	private boolean greaterStrictly;
	private Comparable max;
	private boolean lesserStrictly;
	private boolean warning;
	private String message;

	/**
	 * Constructs a RangeValidator with the specified minimum and maximum.
	 *
	 * @param min the minimum to compare with
	 * @param greaterStrictly to strictly compare the minimum
	 * @param max the maximum to compare with
	 * @param lesserStrictly to strictly compare the maximum
	 */
	public RangeValidator(
		Comparable min, boolean greaterStrictly, Comparable max,
		boolean lesserStrictly)
	{
		this(min, greaterStrictly, max, lesserStrictly, false, null);
	}

	/**
	 * Constructs a RangeValidator with the specified minimum and maximum.
	 *
	 * @param min the minimum to compare with
	 * @param greaterStrictly to strictly compare the minimum
	 * @param max the maximum to compare with
	 * @param lesserStrictly to strictly compare the maximum
	 * @param warning the type of problem to add to the validation context
	 * @param message the message to use in problems it creates
	 */
	public RangeValidator(
		Comparable min, boolean greaterStrictly, Comparable max,
		boolean lesserStrictly, boolean warning, String message)
	{
		this.min = min;
		this.greaterStrictly = greaterStrictly;
		this.max = max;
		this.lesserStrictly = lesserStrictly;
		this.message = message;
		this.warning = warning;
	}

	/**
	 * Compares the specified object to this RangeValidator minimum and maximum.
	 * Adds problems to the validation context if the specified object is
	 * lesser than the minimum or greater than the maximum.
	 * @throws IllegalArgumentException if the specified value is not Comparable
	 */
	public ValidateContext validate(Object value, ValidateContext context)
	{
		Comparable c = getComparableValue(value);

		int minCompare = (min == null) ? 1 : c.compareTo(min);
		int maxCompare = (max == null) ? (-1) : c.compareTo(max);

		if (
			(minCompare < 0) || (greaterStrictly && (minCompare == 0))
				|| (maxCompare > 0) || (lesserStrictly && (maxCompare == 0)))
		{
			if (message == null)
			{
				context.addProblem(getClass(), warning);
			}
			else
			{
				context.addProblem(getClass(), warning, message);
			}
		}

		return context;
	}

	private Comparable getComparableValue(Object value)
	{
		if (value instanceof Comparable)
		{
			return (Comparable) value;
		}

		throw new IllegalArgumentException("not a Comparable value");
	}
}
