/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;


import java.util.regex.Pattern;

/**
 * Pattern validation using a regex api.
 */
public class PatternValidator implements Validator
{
	// pattern constants

	/**
	 * Comment for <code>INTEGER</code>
	 */
	public static final String INTEGER = "[0-9]*";

	/**
	 * Comment for <code>CHAR</code>
	 */
	public static final String CHAR = "[A-Za-z]*";

	/**
	 * Comment for <code>UPPERCASE_CHAR</code>
	 */
	public static final String UPPERCASE_CHAR = "[A-Z]*";

	/**
	 * Comment for <code>pattern</code>
	 */
	private Pattern pattern;

	/**
	 * Comment for <code>message</code>
	 */
	private String message;

	/**
	 * Comment for <code>warning</code>
	 */
	private boolean warning;

	/**
	 * Constructs a PatternValidator with the specified pattern.
	 * @param pattern the regex pattern to use
	 */
	public PatternValidator(String pattern)
	{
		this(pattern, null);
	}

	/**
	 * @param pattern
	 * @param message
	 */
	public PatternValidator(String pattern, String message)
	{
		this(pattern, message, false);
	}

	/**
	 * @param pattern
	 * @param message
	 * @param warning
	 */
	public PatternValidator(String pattern, String message, boolean warning)
	{
		this.pattern = Pattern.compile(pattern);
		this.message = message;
		this.warning = warning;
	}

	/**
	 * Validates that the specified value complies to this validator
	 * regex pattern. Adds a problem to the context if it does not comply.
	 * @param value the String value to validate
	 * @param context the validation context
	 * @throws IllegalArgumentException if the value is not a String
	 */
	public ValidateContext validate(Object value, ValidateContext context)
	{
		if (value instanceof String)
		{
			String s = (String) value;

			if (!pattern.matcher(s).matches())
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

		throw new IllegalArgumentException(
			"value = " + value + " is not a string");
	}
}
