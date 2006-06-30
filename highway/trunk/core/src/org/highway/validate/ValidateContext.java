/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.validate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.highway.collection.MapOfList;
import org.highway.collection.MapOfListImpl;
import org.highway.exception.Assert;
import org.highway.helper.CollectionHelper;
import org.highway.helper.StringHelper;

/**
 * A ValidateContext contains validation parameters and problems.
 * The parameters are set by the developper before the validation of an object
 * to control the validation process.
 * The problems are added to the context by validators during the validation
 * process.
 * At the end of the validation process, the context contains the problems
 * found, meaning the validation result.<br>
 *
 * Exemple:
 * <pre>
 * ValidateContext context = new ValidateContext();
 * context.setPropertiesToAvoid(myPropertyNameSet);
 * ValidateHome.validate(object, context);
 * if (context.isInvalid()) ...
 * </pre>
 *
 * A property path is a string composed of property names separated by dots.
 * Example: a property path valid for a Person object could be:
 * <pre>
 * address.city.name
 * </pre>
 *
 * @since 1.2
 * 
 * 
 * @see org.highway.validate.Validator
 * @see org.highway.validate.ValidateProblem
 */
public class ValidateContext implements Serializable
{
	//TODO how should if use the warning problems ?
	//is the context valid if only warning problems ?

	/**
	 * The whole list of problems.
	 */
	private MapOfList problems;

	/**
	 * Names of missing mandatory properties.
	 */
	private Set missingProperties;

	/**
	 * Property paths (String) to avoid when validating.
	 */
	private Set propertyPathsToAvoid;

	/**
	 * Only property paths (String) to validate.
	 */
	private Set propertyPathsToValidate;

	/**
	 * Validators to use in this specific validation context.
	 * Keys are property paths or null for the root object.
	 */
	private Map contextValidators;
	
	/**
	 * Default constructor. A just instanciated context is valid.
	 */
	public ValidateContext()
	{
	}

	////////
	//////// VALIDATION PARAMETERS
	////////

	/**
	 * @deprecated
	 * @see #setDeep(boolean)
	 */
	public boolean isDeep()
	{
		return propertyPathsToValidate == null && propertyPathsToAvoid == null;
	}

	/**
	 * Use {@link #setPropertiesToValidate(Set)} with new property path notation
	 * instead.
	 * 
	 * @deprecated
	 */
	public void setDeep(boolean deep)
	{
		Set propertyPathsToValidate = null;
		if (! deep)
		{
			propertyPathsToValidate = new HashSet();
			propertyPathsToValidate.add("*");
		}
		setPropertiesToValidate(propertyPathsToValidate);
	}

	/**
	 * Returns the set of properties to avoid when validating.
	 * All the other properties of the object graph to validate will be
	 * validated.
	 * @return the set of property paths (String) to avoid
	 */
	public Set getPropertiesToAvoid()
	{
		return propertyPathsToAvoid;
	}

	/**
	 * Sets the set of property paths to avoid when validating.
	 * All the other properties of the object graph to validate
	 * will be validated (sets the set of properties to validate to null).
	 * Use this method if you need a validation specific to this context.
	 * @param propertyPaths the set of property paths (String) to avoid
	 */
	public void setPropertiesToAvoid(Set propertyPaths)
	{
		propertyPathsToAvoid = propertyPaths;
		propertyPathsToValidate = null;
	}

	/**
	 * Returns the set of properties to validate.
	 * @return the set of property path (String) to validate.
	 */
	public Set getPropertiesToValidate()
	{
		return propertyPathsToValidate;
	}

	/**
	 * Sets the set of properties to validate.
	 * All the other properties of the object graph to validate will be
	 * avoided (sets to set of properties to avoid to null).
	 * If set to null, all the properties are validated but the ones in the
	 * set of properties to avoid.
	 * Use this method if you need a validation specific to this context.
	 * @param propertyPaths the set of property paths (String) to validate
	 */
	public void setPropertiesToValidate(Set propertyPaths)
	{
		propertyPathsToValidate = propertyPaths;
		propertyPathsToAvoid = null;
	}

	/**
	 * Sets validator that the home validator should use on the
	 * root object in this specific validation context.
	 * Use this method if you need a validation specific to this context.
	 * @since 1.4.3
	 */
	public void setContextValidator(ClassValidator validator)
	{
		setContextValidator(null, validator);
	}
	
	/**
	 * Sets the validator the home validator should use on the
	 * specified property object in this specific validation context.
	 * Use this method if you need a validation specific to this context.
	 * @param propertyPath the path to the property object
	 * @param validator the validator to use in this specific context
	 * @since 1.4.3
	 */
	public void setContextValidator(String propertyPath, ClassValidator validator)
	{
		if (contextValidators == null)
		{
			contextValidators = new HashMap();
		}

		contextValidators.put(propertyPath, validator);
	}
	
	/**
	 * Returns the validator attached to the object about to be validated
	 * i.e. the value of the current property path.
	 * This validator is specific to this validation context and must be set
	 * by the <tt>setContextValidator</tt> methods.
	 * @since 1.4.3
	 * @see #setContextValidator(Validator)
	 * @see #setContextValidator(String, Validator)
	 * @see #enterProperty(String)
	 * @see #leaveProperty()
	 */
	public Validator getContextValidator()
	{
		return (contextValidators == null) ? null :
			(Validator) contextValidators.get(propertyPath);
	}

	////////
	//////// VALIDATION PROBLEMS
	////////

	/**
	 * Returns the problem list associated with the root object
	 * of the validated graph.
	 *
	 * @return a list of problems (ValidationProblem)
	 */
	public List getRootProblems()
	{
		return getPropertyProblems(null);
	}

	/**
	 * Returns the problem list associated with the specified property
	 * or null if no problem if found.
	 *
	 * @param propertyPath the property path
	 * @return a list of problems (ValidationProblem)
	 */
	public List getPropertyProblems(String propertyPath)
	{
		if (problems == null)
		{
			return null;
		}

		List propertyProblems = problems.getList(propertyPath, false);

		if (propertyProblems == null)
		{
			return null;
		}

		return Collections.unmodifiableList(propertyProblems);
	}

	/**
	 * Returns the list mandatorty properties missing
	 * or null if no mandatory property is missing.
	 *
	 * @return a set of property paths (String)
	 */
	public Set getMissingProperties()
	{
		if (missingProperties == null)
		{
			return null;
		}

		return Collections.unmodifiableSet(missingProperties);
	}

	/**
	 * Returns all the properties associated to a problem.
	 *
	 * @return a set of property paths (String)
	 */
	public Set getProblematicProperties()
	{
		if (problems == null)
		{
			return null;
		}

		Set propertyPaths = new HashSet(problems.keySet());
		propertyPaths.remove(null);

		return propertyPaths;
	}

	/**
	 * Returns all invalid properties or null if no property is invalid.
	 * Invalid means missing if mandatory or associated to a problem.
	 *
	 * @return a set of property paths (String)
	 */
	public Set getInvalidProperties()
	{
		Set propertyPaths = getProblematicProperties();

		if (propertyPaths == null)
		{
			return getMissingProperties();
		}
		CollectionHelper.addAllIfNotNull(propertyPaths, missingProperties);

		return propertyPaths;
	}

	/**
	 * Returns true if the whole validation context is valid.
	 * It means that no problem has been detected and that no properties
	 * are missing.
	 */
	public boolean isInvalid()
	{
		return isProblematic() || hasMissingProperties();
	}

	/**
	 * Returns true if this validation context contains missing mandatory
	 * properties.
	 */
	public boolean hasMissingProperties()
	{
		return missingProperties != null;
	}

	/**
	 * Returns true if this validation context contains problems
	 * (warning or not).
	 */
	public boolean isProblematic()
	{
		return problems != null;
	}

	/**
	 * Returns true if the specified property is valid
	 * in this validation context.
	 *
	 * @param propertyPath the property path
	 */
	public boolean isInvalid(String propertyPath)
	{
		return isProblematic(propertyPath) || isMissing(propertyPath);
	}

	/**
	 * Returns true if the specified property has one or more problems
	 * in this validation context.
	 *
	 * @param propertyPath the property path
	 */
	public boolean isProblematic(String propertyPath)
	{
		return (problems != null) && problems.containsKey(propertyPath);
	}

	/**
	 * Returns true if the specified mandatory property is missing
	 * in this validation context. A property is missing when its value
	 * is null.
	 *
	 * @param propertyPath the property path
	 */
	public boolean isMissing(String propertyPath)
	{
		return (missingProperties != null)
		&& missingProperties.contains(propertyPath);
	}

	public String toString()
	{
		return "[ValidationContext, missing=" + missingProperties
		+ ", problems=" + problems + ']';
	}

	////////////////////////////////////////////
	//// Management of properties to validate ////
	////////////////////////////////////////////

	/**
	 * Checks if the specified property should be validated.
	 * The property path checked is the concatenation of the context
	 * current property path and the specified property name.
	 * Only Validator should use this method.
	 *
	 * @param propertyName the property name
	 * @return true if the specified property should be validated
	 */
	public boolean checkValidateProperty(String propertyName)
	{
//		if (propertyPathsToValidate != null)
//		{
//			String propertyPath = buildPropertyPath(propertyName);
//			
//			Iterator iterator = propertyPathsToValidate.iterator();
//			while (iterator.hasNext())
//			{
//				if (((String) iterator.next()).startsWith(propertyPath))
//				{
//					return true;
//				}
//			}
//			return false;
//		}
//
//		if (propertyPathsToAvoid != null)
//		{
//			String propertyPath = buildPropertyPath(propertyName);
//
//			return ! propertyPathsToAvoid.contains(propertyPath);
//		}

		return true;
	}
	
	private boolean acceptProperty(String propertyPath)
	{
		if (propertyPathsToValidate != null)
		{
			Iterator iterator = propertyPathsToValidate.iterator();
			while (iterator.hasNext())
			{
				String path = (String) iterator.next();
				
				if (StringHelper.safeEqualsIgnoreEmpty(path, propertyPath) ||
					(! StringHelper.isNullOrEmpty(path) && 
					(path.equals("**") || 
					(! StringHelper.isNullOrEmpty(propertyPath) &&  
					((path.equals("*") && 
						propertyPath.indexOf('.') == -1) ||
					(path.endsWith(".**") && 
						propertyPath.startsWith(path.substring(0, path.length() - 3))) ||
					(path.endsWith(".*") && 
						propertyPath.startsWith(path.substring(0, path.length() - 2)) &&
						propertyPath.lastIndexOf('.') < path.length()))))))
				{
					return true;
				}
			}
			return false;
		}

		if (propertyPathsToAvoid != null)
		{
			Iterator iterator = propertyPathsToAvoid.iterator();
			while (iterator.hasNext())
			{
				String path = (String) iterator.next();
				if (propertyPath.startsWith(path))
				{
					return false;
				}
			}
		}

		return true;
	}

	////////////////////////////////////////////
	//// Add problem methods for validators ////
	////////////////////////////////////////////

	/**
	 * Adds the specified property to the missing mandatory properties
	 * of this validation context.
	 * Only Validator should use this method.
	 */
	public void addMissingProperty(String propertyName)
	{
		String path = buildPropertyPath(propertyName);
		if (acceptProperty(path))
		{
			if (missingProperties == null)
			{
				missingProperties = new HashSet();
			}

			missingProperties.add(path);
		}
	}

	/**
	 * Adds a problem to this validation context.
	 * Only Validator should use this method.
	 * @param validator the Validator class adding the problem
	 * @param warning the problem type
	 * @throws IllegalArgumentException if the validator class is null
	 */
	public void addProblem(Class validator, boolean warning)
	{
		Assert.checkNotNull(validator);
		addProblem0(validator, warning, null);
	}

	/**
	 * Adds a problem to this validation context.
	 * Only Validator should use this method.
	 * @param validator the Validator class adding the problem
	 * @param warning the problem type
	 * @param message the problem message
	 * @throws IllegalArgumentException if the validator class
	 * or the message are null
	 */
	public void addProblem(Class validator, boolean warning, String message)
	{
		Assert.checkNotNull(validator);
		Assert.checkNotNull(message);
		addProblem0(validator, warning, message);
	}

	/**
	 * Adds a problem to this validation context.
	 * Only Validator should use this method.
	 * @param validator the Validator class adding the problem
	 * @param propertyName the property involved in the problem
	 * @param warning the problem type
	 * @throws IllegalArgumentException if the validator class or the property
	 * are null
	 */
	public void addProblem(
		Class validator, String propertyName, boolean warning)
	{
		Assert.checkNotNull(validator);
		Assert.checkNotNull(propertyName);
		addProblem0(validator, propertyName, warning, null);
	}

	/**
	 * Adds a problem to this validation context.
	 * Only Validator should use this method.
	 * @param validator the Validator class adding the problem
	 * @param propertyName the property involved in the problem
	 * @param warning the problem type
	 * @param message the problem message
	 * @throws IllegalArgumentException if the validator class, the property
	 * or the message are null
	 */
	public void addProblem(
		Class validator, String propertyName, boolean warning, String message)
	{
		Assert.checkNotNull(validator);
		Assert.checkNotNull(propertyName);
		Assert.checkNotNull(message);
		addProblem0(validator, propertyName, warning, message);
	}

	/**
	 * Adds a problem to this validation context.
	 * Only Validator should use this method.
	 * @param validator the Validator class adding the problem
	 * @param propertyNames the properties involved in the problem
	 * @param warning the problem type
	 * @throws IllegalArgumentException if the validator class
	 * or the properties are null
	 */
	public void addProblem(
		Class validator, List propertyNames, boolean warning)
	{
		Assert.checkNotNull(validator);
		Assert.checkNotNull(propertyNames);
		Assert.check(! propertyNames.isEmpty(), "empty property name list");
		addProblem0(validator, propertyNames, warning, null);
	}

	/**
	 * Adds a problem to this validation context.
	 * Only Validator should use this method.
	 * @param validator the Validator class adding the problem
	 * @param propertyNames the properties involved in the problem
	 * @param warning the problem type
	 * @param message the problem message
	 * @throws IllegalArgumentException if the validator class, the properties
	 * or the message are null
	 */
	public void addProblem(
		Class validator, List propertyNames, boolean warning, String message)
	{
		Assert.checkNotNull(validator);
		Assert.checkNotNull(message);
		Assert.checkNotNull(propertyNames);
		Assert.check(! propertyNames.isEmpty(), "empty property name list");
		addProblem0(validator, propertyNames, warning, message);
	}

	private void addProblem0(Class validator, boolean warning, String message)
	{
		addProblem0(
			getCurrentPropertyPath(),
			new ValidateProblem(
				message, Collections.singletonList(getCurrentPropertyPath()),
				validator, warning));
	}

	private void addProblem0(
		Class validator, String propertyName, boolean warning, String message)
	{
		if (checkValidateProperty(propertyName))
		{
			// replace relative property name by absolut property path
			String propertyPath = buildPropertyPath(propertyName);

			// add problem in the map for this property problem list
			addProblem0(
				propertyPath,
				new ValidateProblem(
					message, Collections.singletonList(propertyPath), validator,
					warning));
		}
	}

	private void addProblem0(
		Class validator, List propertyNames, boolean warning, String message)
	{
		ValidateProblem problem =
			new ValidateProblem(message, propertyNames, validator, warning);

		for (int i = propertyNames.size() - 1; i >= 0; --i)
		{
			String propertyName = (String) propertyNames.get(i);

			if (checkValidateProperty(propertyName))
			{
				// replace relative property name by absolut property path
				String propertyPath = buildPropertyPath(propertyName);
				propertyNames.set(i, propertyPath);

				// add problem in the map for this property problem list
				addProblem0(propertyPath, problem);
			}
			else
			{
				propertyNames.remove(i);
			}
		}
	}

	private void addProblem0(String propertyPath, ValidateProblem problem)
	{
		if (acceptProperty(propertyPath))
		{
			if (problems == null)
			{
				problems = new MapOfListImpl();
			}
	
			problems.add(propertyPath, problem);
		}
	}

	//////////////////////////////////////////
	//// Property path management methods ////
	//////////////////////////////////////////
	private String propertyPath;

	/**
	 * Informs this context that the specified property value is about to be
	 * validated. It sets the context current property path to the current
	 * property path concatenated to the specified property name.<br>
	 * Only validator should use this method to inform the context
	 * that the following new problems are related to this property.
	 * @param propertyName the name of the property about to be validated
	 */
	public void enterProperty(String propertyName)
	{
		propertyPath = buildPropertyPath(propertyName);
	}

	/**
	 * Informs this context that the last property it has entered
	 * has been validated.
	 * It sets the context current property path to the current
	 * property path minus the last entered property name.<br>
	 * Only Validator should use this method.
	 */
	public void leaveProperty()
	{
		int index = propertyPath.lastIndexOf('.');

		if (index < 0)
		{
			propertyPath = null;
		}
		else
		{
			propertyPath = propertyPath.substring(0, index);
		}
	}

	private String buildPropertyPath(String propertyName)
	{
		return (propertyPath == null) ? propertyName
									  : (propertyPath + '.' + propertyName);
	}

	private String getCurrentPropertyPath()
	{
		return propertyPath;
	}

	////////////////////////////////////////
	//// Circular validation management ////
	////////////////////////////////////////

	/**
	 * List of all the checked objects. An object is checked when
	 * it is about to be validated. Used to avoid multiple validation
	 * of the same object in object graphs.
	 */
	private List checkedObjects;

	/**
	 * Checks the specified object if it has not already been checked.
	 * Returns false if the specified object has already been checked.
	 * Used to avoid multiple validation when validating circular object graph.<br>
	 * Only Validator objects should use this method.
	 *
	 * @param object the object to check
	 * @return false if already checked
	 */
	public boolean checkObject(Object object)
	{
		// null is of course checked
		if (object == null) return true;
		
		if (checkedObjects == null) checkedObjects = new ArrayList();
		
		for (int i = 0; i < checkedObjects.size(); i++)
		{
			if (object == checkedObjects.get(i))
			{
				return false;
			}
		}

		checkedObjects.add(object);

		return true;
	}

	public void reset(boolean keepValidationSettings)
	{
		if (!keepValidationSettings)
		{
//			this.deep = true;
			if (this.propertyPathsToAvoid != null) this.propertyPathsToAvoid.clear();
			if (this.propertyPathsToValidate != null) this.propertyPathsToValidate.clear();
			if (this.contextValidators != null) this.contextValidators.clear();
		}
		
		this.propertyPath = null;
		if (this.checkedObjects != null) this.checkedObjects.clear();
		if (this.missingProperties != null) this.missingProperties.clear();
		if (this.problems != null) this.problems.clear();
	}
}
