package org.highway.bean;

import java.lang.annotation.Annotation;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.helper.StringHelper.TrimPolicy;
import org.highway.lifecycle.InitException;

/**
 * Use the static methods of this class to access the default BeanMetadataManager
 * and get metadata on JavaBean classes. JavadocMetadataManager is the default
 * implementation od BeanMetadataManager. As a result, an instance of
 * JavadocMetadataManager is used if no default BeanMetadataManager is explicitly
 * set.
 * 
 * @see org.highway.bean.BeanMetadataManager
 */
public class BeanMetadataHome
{
	/**
	 * Default BeanMetadataManager.
	 */
	private static BeanMetadataManager beanMetadataManager = new StandardBeanMetadataManager();

	/**
	 * Returns the default BeanMetadataManager.
	 */
	public static BeanMetadataManager getMetadataManager()
	{
		return beanMetadataManager;
	}

	/**
	 * Sets the default BeanMetadataManager.
	 */
	public static synchronized void setMetadataManager(
			BeanMetadataManager manager)
	{
		beanMetadataManager = manager;
		DebugHome.getDebugLog().info("Default BeanMetadataManager is set to " + manager);
	}

	 ///////////////////////////////////////
	 //// BeanMetadataManager shortcuts ////
	 ///////////////////////////////////////

	/**
	 * Delegates to the default BeanMetadataManager.
	 * 
	 * @throws InitException if no BeanMetadataManager is set.
	 * @see BeanMetadataManager#getBeanAnnotation(Class, Class)
	 */
	public static <T extends Annotation> T getBeanAnnotation(Class beanClass,
			Class<T> annotationType)
	{
		if (beanMetadataManager == null)
		{
			throw new InitException("no default BeanMetadataManager set");
		}

		return beanMetadataManager.getBeanAnnotation(beanClass, annotationType);
	}

	/**
	 * Delegates to the default BeanMetadataManager.
	 * 
	 * @throws InitException if no BeanMetadataManager is set.
	 * @see BeanMetadataManager#getPropertyAnnotation(Class, String, Class)
	 */
	public static <T extends Annotation> T getPropertyMetaValue(
			Class beanClass, String propertyName, Class<T> annotationType)
	{
		if (beanMetadataManager == null)
		{
			throw new InitException("no default BeanMetadataManager set");
		}
		
		return beanMetadataManager.getPropertyAnnotation(beanClass, propertyName,
				annotationType);
	}

	//////////////////////////////////
	//// Common property metadata ////
	//////////////////////////////////

	/**
	 * Returns the minimum size of the specified JavaBean property. Returns null
	 * if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static Integer getPropertySizeMin(Class beanClass,
			String propertyName)
	{
		PropertySize propertySize = getPropertyMetaValue(beanClass, propertyName,
				PropertySize.class);
		
		return propertySize == null ? null : propertySize.min();
	}

	/**
	 * Returns the maximum size of the specified JavaBean property. Returns null
	 * if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static Integer getPropertySizeMax(Class beanClass,
			String propertyName)
	{
		PropertySize propertySize = getPropertyMetaValue(beanClass, propertyName,
				PropertySize.class);
		
		return propertySize == null ? null : propertySize.max();
	}

	/**
	 * Returns the regex pattern of the specified JavaBean property. Returns
	 * null if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static String getPropertyPattern(Class beanClass, String propertyName)
	{
		PropertyPattern propertyPattern = getPropertyMetaValue(beanClass, propertyName,
				PropertyPattern.class);
		
		return propertyPattern == null ? null : propertyPattern.value();
	}

	/**
	 * Returns true if the specified JavaBean property is mandatory. Returns
	 * false if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static boolean isPropertyMandatory(Class beanClass,
			String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName,
				MandatoryProperty.class) != null;
	}

	/**
	 * Returns true if the specified JavaBean property must be uppercase.
	 * Returns false if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static boolean isPropertyUppercase(Class beanClass,
			String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName,
				UpperCaseProperty.class) != null;
	}

	/**
	 * Returns true if the specified JavaBean property is read only. Returns
	 * false if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static boolean isPropertyReadOnly(Class beanClass,
			String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName,
				ReadOnlyProperty.class) != null;
	}

	/**
	 * Returns the short description of the specified JavaBean property. Returns
	 * null if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static String getPropertyShortDescription(Class beanClass,
			String propertyName)
	{
		PropertyShortDescription propertyShortDescription = getPropertyMetaValue(beanClass,
				propertyName, PropertyShortDescription.class);
		
		return propertyShortDescription == null ? null : propertyShortDescription.value();
	}

	/**
	 * Returns the long description of the specified JavaBean property. Returns
	 * null if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static String getPropertyLongDescription(Class beanClass,
			String propertyName)
	{
		PropertyLongDescription propertyLongDescription = getPropertyMetaValue(beanClass,
				propertyName, PropertyLongDescription.class);
		
		return propertyLongDescription == null ? null : propertyLongDescription.value();
	}

	/**
	 * Returns the scale of the specified JavaBean decimal property. Returns
	 * null if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static Integer getPropertyDecimalScale(Class beanClass,
			String propertyName)
	{
		PropertyScale propertyScale = getPropertyMetaValue(beanClass, propertyName,
				PropertyScale.class);
		
		return propertyScale == null ? null : propertyScale.value();
	}

	/**
	 * Returns the trim policy of the specified JavaBean string property. Returns
	 * null if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 * @see org.highway.helper.StringHelper.TrimPolicy
	 */
	public static TrimPolicy getPropertyTrimPolicy(Class beanClass, String propertyName)
	{
		PropertyTrimPolicy propertyTrimPolicy = getPropertyMetaValue(beanClass, propertyName,
				PropertyTrimPolicy.class);
		
		return propertyTrimPolicy == null ? null : propertyTrimPolicy.value();
	}
	
	////////////////////////
	//// Implementation ////
	////////////////////////

	/**
	 * Do not instantiate this class.
	 */
	private BeanMetadataHome()
	{
		throw new DoNotInstantiateException();
	}
}
