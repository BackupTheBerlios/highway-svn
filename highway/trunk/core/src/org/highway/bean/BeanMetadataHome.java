package org.highway.bean;

import java.lang.annotation.Annotation;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;
import org.highway.vo.JavadocMetadataManager;
import org.highway.vo.MetadataManager;

/**
 * Use the static methods of this class to access the default MetadataManager
 * and get metadata on JavaBean classes. JavadocMetadataManager is the default
 * implementation od MetadataManager. As a result, an instance of
 * JavadocMetadataManager is used if no default MetadataManager is explicitly
 * set.
 * 
 * @see org.highway.vo.JavadocMetadataManager
 */
public class BeanMetadataHome
{
	/*
	 * @todo faire en sorte que les annotations suivantes soient équivalentes :
	 * @highway.vo.property.min 2 @highway.vo.property.max 10 =
	 * @highway.vo.property min=2 max=10 = @highway.vo.property min="2" max="10"
	 */

	// ////////////////////////////////////
	// // Default MetadataManager init ////
	// ////////////////////////////////////
	/**
	 * Default MetadataManager.
	 */
	private static StandardBeanMetadataManager metadataManager = new StandardBeanMetadataManager();

	/**
	 * Returns the default MetadataManager.
	 */
	public static StandardBeanMetadataManager getMetadataManager()
	{
		return metadataManager;
	}

	/**
	 * Sets the default MetadataManager.
	 */
	public static synchronized void setMetadataManager(
			StandardBeanMetadataManager manager)
	{
		metadataManager = manager;
		DebugHome.getDebugLog().info(
				"Default MetadataManager is set to " + manager);
	}

	// /////////////////////////////////
	// // MetadataManager shortcuts ////
	// /////////////////////////////////

	/**
	 * Delegates to the default MetadataManager.
	 * 
	 * @throws InitException if no MetadataManager is set.
	 * @see MetadataManager#getClassMetaValue(Class, String)
	 */
	public static <T extends Annotation> T getClassMetaValue(Class beanClass,
			Class<T> annotationType)
	{
		if (metadataManager == null)
		{
			throw new InitException("no default MetadataManager set");
		}

		return metadataManager.getClassMetaValue(beanClass, annotationType);
	}

	/**
	 * Delegates to the default MetadataManager.
	 * 
	 * @throws InitException if no MetadataManager is set.
	 * @see MetadataManager#getPropertyMetaValue(Class, String, String)
	 */
	public static <T extends Annotation> T getPropertyMetaValue(
			Class beanClass, String propertyName, Class<T> annotationType)
	{
		if (metadataManager == null)
		{
			throw new InitException("no default MetadataManager set");
		}
		return metadataManager.getPropertyMetaValue(beanClass, propertyName,
				annotationType);
	}

	// ////////////////////////////////
	// // Common property metadata ////
	// ////////////////////////////////

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
		PropertySize meta = getPropertyMetaValue(beanClass, propertyName, PropertySize.class);
		if (meta == null) return null;
		return meta.min();
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
		PropertySize meta = getPropertyMetaValue(beanClass, propertyName, PropertySize.class);
		if (meta == null) return null;
		return meta.max();
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
		PropertyPattern meta = getPropertyMetaValue(beanClass, propertyName,
				PropertyPattern.class);
		if (meta == null) return null;
		return meta.value();
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
		return getPropertyMetaValue(beanClass, propertyName, MandatoryProperty.class) != null;
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
		return getPropertyMetaValue(beanClass, propertyName, UpperCaseProperty.class) != null;
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
		return getPropertyMetaValue(beanClass, propertyName, ReadOnlyProperty.class) != null;
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
		PropertyShortDescription meta = getPropertyMetaValue(beanClass, propertyName,
				PropertyShortDescription.class);
		if (meta == null) return null;
		return meta.value();
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
		PropertyLongDescription meta = getPropertyMetaValue(beanClass, propertyName,
				PropertyLongDescription.class);
		if (meta == null) return null;
		return meta.value();
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
		PropertyScale meta = getPropertyMetaValue(beanClass, propertyName, PropertyScale.class);
		if (meta == null) return null;
		return meta.value();
	}

	// //////////////////////
	// // Implementation ////
	// //////////////////////

	/**
	 * Do not instantiate this class.
	 */
	private BeanMetadataHome()
	{
		throw new DoNotInstantiateException();
	}
}
