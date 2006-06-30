/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;

import org.highway.helper.ClassHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the non compliance to a validation rule.
 * A ValidateProblem contains 4 properties: the problem message,
 * the Validator class responsible for the problem, the properties
 * involved in the problem and the type of problem. A problem can be a
 * real problem or just a warning problem.<br>
 *
 * Validation problems are added to the validation contaxt by validators.
 * Developers can not create problems outside a validation process.
 *
 * 
 * 
 */
public final class ValidateProblem implements Serializable
{
	private String message;
	private List properties;
	private boolean warning;
	private Class validator;

	ValidateProblem(
		String message, List properties, Class validator, boolean warning)
	{
		this.validator = validator;
		this.message = message;
		this.properties = properties;
		this.warning = warning;
	}

	/**
	 * Returns true if the specified property is involved in this problem.
	 */
	public boolean isInvolved(String propertyName)
	{
		if (properties != null)
		{
			return properties.contains(propertyName);
		}

		return false;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer(50);
		buffer.append("[validator=");
		buffer.append(ClassHelper.getClassName(validator, false));
		buffer.append(", property=").append(properties);

		if (message != null)
		{
			buffer.append(", message=").append(message);
		}

		buffer.append(", warning=").append(warning);

		return buffer.append(']').toString();
	}

	//////////////////////////
	///// public get/set /////
	//////////////////////////

	/**
	 * Returns the problem message or null if this problem does not have any.
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Returns true if this problem is just a warning problem.
	 */
	public boolean isWarning()
	{
		return warning;
	}

	/**
	 * Returns the Validator class responsible for this problem.
	 */
	public Class getValidatorClass()
	{
		return validator;
	}

	/**
	 * Returns all the properties involved in this problem.
	 * @return a List of property names of type String
	 */
	public List getPropertiesInvolved()
	{
		return properties;
	}
}
