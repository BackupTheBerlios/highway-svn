/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;


/**
 * Validates objects.
 *
 * @since 1.2
 * 
 */
public interface Validator
{
	/**
	 * Validates the specified object. The specified context contains the
	 * validation parameters and the problems found during the validation.
	 * The specified context can be null. In this case a new context is
	 * created by this method and returned. The context returned must be
	 * exactly the one passed as argument if not null.
	 *
	 * @param object the object to validate
	 * @param context the context of the validation process
	 * @return the context of the validation process (the one passed as an
	 * argument or a new one if null was passed)
	 * @see org.highway.validate.ValidateProblem
	 * @see org.highway.validate.ValidateHome
	 */
	ValidateContext validate(Object object, ValidateContext context);
}
