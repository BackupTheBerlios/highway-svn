/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;

import org.highway.bean.JavaBean;
import org.highway.exception.Assert;
import org.highway.helper.ClassHelper;
import java.util.HashMap;
import java.util.Map;

/**
 * Standard GlobalValidator implementation.<br><br>
 *
 * Validates any type of objects. Search for the ClassValidator associated
 * to the class of the specified object, i.e. searches for the class
 * with a name = object.getClass().getName() + "Validator".
 * If this class is not found, searches for the ClassValidator of its
 * superclass.<br><br>
 *
 * If the specified object is a ValueObject and no specific ClassValidator
 * is found, a regular JavaBeanValidator is created and associated with the
 * class of the specified object. The regular JavaBeanValidator creates
 * validation rules from the ValueObject metadata (mandatory, min, max, pattern,
 * etc).
 *
 * A class mypackage.MyClass should have a ClassValidator class
 * mypackage.MyClassValidator. A JavaBean mypackage.MyBean should have
 * a JavaBeanValidator class mypackage.MyBeanValidator.
 *
 * 
 * 
 */
public class JavaBeanGlobalValidator implements GlobalValidator
{
	/**
	 * Validator class suffix.
	 */
	public static final String VALIDATOR_CLASS_SUFFIX = "Validator";

	/**
	 * Default Validator for classes with no associated Validator.
	 * TODO replace by a SilentClassValidator for type consistency
	 */
	private static final Object NO_VALIDATOR_FOR_THIS_TYPE = new Object();

	/**
	 * Map the validators of the java classes.
	 * Contains JavaBeanValidator objects for ValueObject types
	 * and ClassValidator subclass objects for other java types.
	 */
	private Map classValidatorMap = new HashMap();

	/**
	 * Default constructor.
	 */
	public JavaBeanGlobalValidator()
	{
	}

	/**
	 * Associates the specified Validator to the specified class.
	 * The specified validator will be used to validate objects of the
	 * specified class.
	 */
	public void setValidator(Class objectClass, Validator validator)
	{
		classValidatorMap.put(objectClass, validator);
	}

	/**
	 * Validates any type of objects. Search for the ClassValidator associated
	 * with the specified object class or superclasses. Takes the name of the
	 * object class and add "Validator".
	 *
	 * If the specified object is a ValueObject, search for the
	 * JavaBeanValidator class of name = object.getClass().getName()
	 * + "Validator". If none is found, search the object superclasses.
	 * If none is found, creates a regular JavaBeanValidator.
	 */
	public ValidateContext validate(Object object, ValidateContext context)
	{
		Assert.checkNotNull(object);

		if (context == null)
		{
			context = new ValidateContext();
		}

		// check if object needs to be validated
		// and register it not to validate it again
		// later if circular validation
		if (context.checkObject(object))
		{
			Validator validator = getValidator(object.getClass());

			if (validator != null)
			{
				validator.validate(object, context);
			}
		}

		return context;
	}

	/**
	 * Returns the ClassValidator associated with the specified class.
	 * Returns null if no ClassValidator is found.
	 * Warning: this method is recursive.
	 * @param objectClass the type of object to validate
	 */
	private ClassValidator getValidator(Class objectClass)
	{
		if (objectClass == null)
		{
			return null;
		}

		// is the validator in cache?
		Object validator = classValidatorMap.get(objectClass);

		if (validator == null)
		{
			// try to find the validator class and create the validator
			validator = createClassValidator(objectClass);

			if (validator == null)
			{
				// arbitrary object to indicate that no validator has been found
				// for this type of object and that there is no need to search
				// again next time
				validator = NO_VALIDATOR_FOR_THIS_TYPE;
			}
			else
			{
				// sets the exact class this validator instance must validate
				((ClassValidator) validator).setClassToValidate(objectClass);
			}

			// the class validator is cached
			classValidatorMap.put(objectClass, validator);
		}

		if (validator == NO_VALIDATOR_FOR_THIS_TYPE)
		{
			// if no registered validator, returns null
			validator = null;
		}

		return (ClassValidator) validator;
	}

	private Object createClassValidator(Class objectClass)
	{
		// find the validator class
		// returns null if not found
		Class validatorClass = findValidatorClass(objectClass);
		
		// Javabeans must be validated by a JavaBeanValidator
		// for the metadata to be used for validation
		// and for its properties to be deeply validated
		if (JavaBean.class.isAssignableFrom(objectClass))
		{
			if ((validatorClass != null)
					&& JavaBeanValidator.class.isAssignableFrom(validatorClass))
			{
				return ClassHelper.newInstance(validatorClass);
			}
			
			return new JavaBeanValidator();
		}


		if ((validatorClass != null)
				&& ClassValidator.class.isAssignableFrom(validatorClass))
		{
			return ClassHelper.newInstance(validatorClass);
		}

		// return null if the class found is null
		// or does not implement the right interface
		return null;
	}

	private Class findValidatorClass(Class objectClass)
	{
		if (objectClass.equals(Object.class))
		{
			return null;
		}

		String validatorClassName =
			objectClass.getName() + VALIDATOR_CLASS_SUFFIX;

		ClassLoader classLoader =
			Thread.currentThread().getContextClassLoader();

		try
		{
			return Class.forName(validatorClassName, true, classLoader);
		}
		catch (ClassNotFoundException cnfe)
		{
			// objectClass does not have a direct validator class
			// get the super class validator class
			return findValidatorClass(objectClass.getSuperclass());
		}
	}
}
