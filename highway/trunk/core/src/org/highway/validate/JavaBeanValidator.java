package org.highway.validate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.highway.bean.BeanMetadataHome;
import org.highway.collection.MapOfList;
import org.highway.collection.MapOfListImpl;
import org.highway.helper.ReflectHelper;


/**
 * Validates a JavaBean. Validates its properties and its global rules.
 * Converts its metadata into validators.<br><br>
 * 
 * Warning: objects of this class initializes when it first validates an object
 * by creating the property validators from metadata. Validation of subclasses
 * may not validate all the properties correctly.
 *
 */
public class JavaBeanValidator implements ClassValidator
{
	/**
	 * The validators set on properties.
	 */
	private MapOfList propertyValidatorMap;

	/**
	 * The mandatory property names.
	 */
	private Set mandatoryPropertySet;
	
	/**
	 * The type of bean to validate.
	 */
	private Class classToValidate;
	
	/**
	 * The property names of the bean and its superclasses.
	 */
	private List propertyNames;

	/**
	 * Indicates if we need to load the metadata of the bean
	 * to create validators on size, pattern, ...
	 */
	private boolean useMetadata;

	/**
	 * Constructs a JavaBeanValidator. Creates property validators from
	 * metadata by default.
	 */
	public JavaBeanValidator()
	{
		this(true);
	}

	/**
	 * Constructs a JavaBeanValidator.
	 * @param useMetadata indicates if this object
	 * need to create property validators from metadata
	 * 
	 */
	public JavaBeanValidator(boolean useMetadata)
	{
		this.useMetadata = useMetadata;
	}

	/**
	 * Returns true if this validator must use metadata
	 * to automatically create property validators.
	 * 
	 */
	public boolean isUseMetadata()
	{
		return useMetadata;
	}

	/**
	 * Sets a flag that indicates if this validator must use metadata
	 * to automatically create property validators.
	 * 
	 */
	public void setUseMetadata(boolean useMetadata)
	{
		this.useMetadata = useMetadata;
	}

	/**
	 * Sets the JavaBean class to validate.
	 * This method gets the whole list of its properties even the one
	 * declared in its superclasses and creates validatoros from its
	 * properties metadata.
	 */
	public final void setClassToValidate(Class value)
	{
		// FIXME update the javadoc and say that this method should not
		// be inside a subclass since this method is automaticaly called
		// and because it may be dangerous 
		this.classToValidate = value;
		propertyNames = ReflectHelper.getPropertyList(classToValidate);
		if (useMetadata) addDefaultValidators();
	}

	/**
	 * Adds the default validators representing the metadata of the class
	 * to validate. It convert the metadata (mandatory, min, max, patter, etc)
	 * into validators.
	 */
	private final void addDefaultValidators()
	{
		for (Iterator iter = propertyNames.iterator(); iter.hasNext();)
		{
			String propertyName = (String) iter.next();

			// mandatory
			setMandatory(
				propertyName,
				BeanMetadataHome.isPropertyMandatory(classToValidate, propertyName));

			// pattern
			String pattern =
				BeanMetadataHome.getPropertyPattern(classToValidate, propertyName);

			if (pattern != null)
			{
				addValidator(propertyName, new PatternValidator(pattern));
			}

			// size min and size max
			Integer sizeMin =
				BeanMetadataHome.getPropertySizeMin(classToValidate, propertyName);
			Integer sizeMax =
				BeanMetadataHome.getPropertySizeMax(classToValidate, propertyName);

			if ((sizeMin != null) || (sizeMax != null))
			{
				addValidator(propertyName, new SizeValidator(sizeMin, sizeMax));
			}
		}
	}

	public final Class getClassToValidate()
	{
		return classToValidate;
	}

	/**
	 * Validates the specified object and its properties. The specified
	 * object should be a JavaBean.
	 * @throws IllegalStateException if the class to validate is no set
	 */
	public final ValidateContext validate(
		Object object, ValidateContext context)
	{
		if (context == null)
		{
			context = new ValidateContext();
		}

		if (this.classToValidate == null)
		{
			setClassToValidate(object.getClass());
		}

		if (!this.classToValidate.isAssignableFrom(object.getClass()))
		{
			throw new IllegalArgumentException(
					"can not validate objects of type " + object.getClass());
		}
		
		validateProperties(object, context);
		validateObjectRules(object, context);

		return context;
	}

	/**
	 * Validation des règles sur les propriétés du value object.<br>
	 * Si des problèmes sont détectés, ils sont  agrégés à la {@link
	 * org.highway.validate.ValidationResponse}
	 *
	 * @param valueObject {@link org.highway.bean.ValueObject}
	 * @param context {@link ValueObjectValidationResponse}
	 */
	private void validateProperties(
		Object valueObject, ValidateContext context)
	{
		for (Iterator iter = getProperties().iterator(); iter.hasNext();)
		{
			String propertyName = (String) iter.next();
			Object propertyValue =
				ReflectHelper.getProperty(valueObject, propertyName);
			validateProperty(propertyName, propertyValue, context);
		}
	}

	/**
	 * Returns the property names of the class this JavaBeanValidator validates.
	 * @return a List of String objects
	 * @throws IllegalStateException if the class to validate is no set
	 */
	private List getProperties()
	{
		if (propertyNames == null)
		{
			throw new IllegalStateException(
				"the type of objects to validate has not been set yet");
		}

		return propertyNames;
	}

	/**
	 * Validates a property of the object.
	 */
	private void validateProperty(
		String propertyName, Object propertyValue, ValidateContext context)
	{
		// check if this property should be validated
		if (context.checkValidateProperty(propertyName))
		{
			if (isNull(propertyValue))
			{
				if (isMandatory(propertyName))
				{
					context.addMissingProperty(propertyName);
				}
			}
			else// no need to validate property if value is null 
			{
				// set property path context to ease problem addition
				context.enterProperty(propertyName);

				// validate the property value with its specific validator
				// only if deep
				if (context.isDeep())
				{
					ValidateHome.getGlobalValidator().validate(
						propertyValue, context);
				}

				if (propertyValidatorMap != null)
				{
					for (
						Iterator iter =
							propertyValidatorMap.iterator(propertyName);
							iter.hasNext();)
					{
						((Validator) iter.next()).validate(
							propertyValue, context);
					}
				}

				// unset property path context
				context.leaveProperty();
			}
		}
	}

	/**
	 * Validates the object global rules.
	 * This method should be redefined by subclasses and should contain
	 * validation rules involving multiple properties.
	 */
	protected void validateObjectRules(
		Object valueObject, ValidateContext context)
	{
	}

	/**
	 * Adds the specified Validator the list of validators to use on the
	 * specified property. This method should be used in the constructor
	 * of subclasses to add validators on properties.
	 *
	 * @param propertyName the name of the property
	 * @param validator the validator to add
	 */
	public final void addValidator(String propertyName, Validator validator)
	{
		if (propertyValidatorMap == null)
		{
			propertyValidatorMap = new MapOfListImpl();
		}

		propertyValidatorMap.add(propertyName, validator);
	}
	
	/**
	 * Checks if the specified property is mandatory.
	 *
	 * @param propertyName the property name
	 */
	public final boolean isMandatory(String propertyName)
	{
		return (mandatoryPropertySet != null)
		&& mandatoryPropertySet.contains(propertyName);
	}

	/**
	 * Sets the mandatory state fo the specified property.
	 *
	 * @param propertyName the property name
	 * @param mandatory the state to set
	 */
	public final void setMandatory(String propertyName, boolean mandatory)
	{
		if (mandatory)
		{
			if (mandatoryPropertySet == null)
			{
				mandatoryPropertySet = new HashSet();
			}

			mandatoryPropertySet.add(propertyName);
		}
		else
		{
			if (mandatoryPropertySet != null)
			{
				mandatoryPropertySet.remove(propertyName);
			}
		}
	}

	private static boolean isNull(Object object)
	{
		return object == null;
	}
}
