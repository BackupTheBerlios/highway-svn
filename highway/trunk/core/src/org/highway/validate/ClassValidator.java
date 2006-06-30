/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;


/**
 * A class validator validates objects of one or more classes outside of
 * any context.
 *
 * @since 1.2
 * 
 */
public interface ClassValidator extends Validator
{
	/**
	 * Returns the class of object this validator validates.
	 */
	Class getClassToValidate();

	/**
	 * Sets the class of object this validator validates.
	 * This method is used to inform this specific instance that it is
	 * dedicated to the validation of the specified class.
	 */
	void setClassToValidate(Class classToValidate);
}
