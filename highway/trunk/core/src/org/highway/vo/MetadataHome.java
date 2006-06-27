/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vo;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

/**
 * Use the static methods of this class to access the default MetadataManager
 * and get metadata on JavaBean classes.
 * JavadocMetadataManager is the default implementation od MetadataManager.
 * As a result, an instance of JavadocMetadataManager is used if no default
 * MetadataManager is explicitly set.
 *
 * @since 1.2
 * @author David Attias
 * @author Christian de Bevotte
 * @see org.highway.vo.JavadocMetadataManager
 */
public class MetadataHome
{
	/* @todo faire en sorte que les annotations suivantes soient équivalentes :
	 *
	 * @highway.vo.property.min 2
	 * @highway.vo.property.max 10
	 * = @highway.vo.property min=2 max=10
	 * = @highway.vo.property min="2" max="10"
	 *
	 */

	//////////////////////////////////////
	//// Default MetadataManager init ////
	//////////////////////////////////////

	/**
	 * Default MetadataManager.
	 */
	private static MetadataManager metadataManager =
		new JavadocMetadataManager();

	/**
	 * Returns the default MetadataManager.
	 */
	public static MetadataManager getMetadataManager()
	{
		return metadataManager;
	}

	/**
	 * Sets the default MetadataManager.
	 */
	public static synchronized void setMetadataManager(MetadataManager manager)
	{
		metadataManager = manager;
		DebugHome.getDebugLog().info(
			"Default MetadataManager is set to " + manager);
	}

	///////////////////////////////////
	//// MetadataManager shortcuts ////
	///////////////////////////////////

	/**
	 * Delegates to the default MetadataManager.
	 * @throws InitException if no MetadataManager is set.
	 * @see MetadataManager#getClassMetaValue(Class, String)
	 */
	public static String getClassMetaValue(Class beanClass, String metaName)
	{
		if (metadataManager == null)
		{
			throw new InitException("no default MetadataManager set");
		}

		return metadataManager.getClassMetaValue(beanClass, metaName);
	}

	/**
	 * Delegates to the default MetadataManager.
	 * @throws InitException if no MetadataManager is set.
	 * @see MetadataManager#getPropertyMetaValue(Class, String, String)
	 */
	public static String getPropertyMetaValue(
		Class beanClass, String propertyName, String metaName)
	{
		if (metadataManager == null)
		{
			throw new InitException("no default MetadataManager set");
		}

		return metadataManager.getPropertyMetaValue(
			beanClass, propertyName, metaName);
	}

	//////////////////////////////////
	//// Common property metadata ////
	//////////////////////////////////

	/**
	 * Prefix used and reserved by metadata defined by the highway.
	 * Projects should not use this prefix for their own metadata.
	 */
	public static final String META_NAME_PREFIX = "highway.vo.property";

	/**
	 * Metadata property name of JavaBean property minimum size (value is
	 * {@value #MIN}).
	 */
	public static final String MIN = META_NAME_PREFIX + '.' + "min";

	/**
	 * Returns the minimum size of the specified JavaBean property.
	 * Returns null if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static Integer getPropertySizeMin(
		Class beanClass, String propertyName)
	{
		String value = getPropertyMetaValue(beanClass, propertyName, MIN);

		if (value == null)
		{
			return null;
		}

		return new Integer(value);
	}

	/**
	 * Metadata property name of JavaBean property maximum size (value is
	 * {@value #MAX}).
	 */
	public static final String MAX = META_NAME_PREFIX + '.' + "max";

	/**
	 * Returns the maximum size of the specified JavaBean property.
	 * Returns null if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static Integer getPropertySizeMax(
		Class beanClass, String propertyName)
	{
		String value = getPropertyMetaValue(beanClass, propertyName, MAX);

		if (value == null)
		{
			return null;
		}

		return new Integer(value);
	}

	/**
	 * Metadata property name of the regex pattern of JavaBean String property
	 * (value is {@value #PATTERN}).
	 */
	public static final String PATTERN = META_NAME_PREFIX + '.' + "pattern";

	/**
	 * Returns the regex pattern of the specified JavaBean property.
	 * Returns null if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static String getPropertyPattern(
		Class beanClass, String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName, PATTERN);
	}

	/**
	 * Metadata property name of JavaBean property mandatory flag (value is
	 * {@value #MANDATORY}).
	 */
	public static final String MANDATORY = META_NAME_PREFIX + '.' + "mandatory";

	/**
	 * Returns true if the specified JavaBean property is mandatory.
	 * Returns false if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static boolean isPropertyMandatory(
		Class beanClass, String propertyName)
	{
		String value = getPropertyMetaValue(beanClass, propertyName, MANDATORY);

		// valueOf returns false if the metadata value is null
		return Boolean.valueOf(value).booleanValue();
	}

	/**
	 * Metadata property name of JavaBean property uppercase flag (value is
	 * {@value #UPPERCASE}).
	 */
	public static final String UPPERCASE = META_NAME_PREFIX + '.' + "uppercase";

	/**
	 * Returns true if the specified JavaBean property must be uppercase.
	 * Returns false if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static boolean isPropertyUppercase(
		Class beanClass, String propertyName)
	{
		String value = getPropertyMetaValue(beanClass, propertyName, UPPERCASE);

		// valueOf returns false if the metadata value is null
		return Boolean.valueOf(value).booleanValue();
	}

	/**
	 * Metadata property name of JavaBean property read only flag (value is
	 * {@value #READ_ONLY}).
	 */
	public static final String READ_ONLY = META_NAME_PREFIX + '.' + "readonly";

	/**
	 * Returns true if the specified JavaBean property is read only.
	 * Returns false if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static boolean isPropertyReadOnly(
		Class beanClass, String propertyName)
	{
		String value = getPropertyMetaValue(beanClass, propertyName, READ_ONLY);

		// valueOf returns false if the metadata value is null
		return Boolean.valueOf(value).booleanValue();
	}

	/**
	 * Metadata property name of the trim policy of JavaBean string property
	 * (value is {@value #TRIMPOLICY}).
	 * 
	 * @since 1.4.6
	 */
	public static final String TRIMPOLICY = META_NAME_PREFIX + '.' + "trimpolicy";

	/**
	 * Returns the trim policy of the specified JavaBean property.
	 * Returns null if not found in the JavaBean metadata.
	 * 
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 * @since 1.4.6
	 */
	public static String getPropertyTrimPolicy(
		Class beanClass, String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName, TRIMPOLICY);
	}

	/**
	 * Metadata property name of JavaBean property short description (value is
	 * {@value #DESCRIPTION_SHORT}).
	 */
	public static final String DESCRIPTION_SHORT =
		META_NAME_PREFIX + '.' + "description.short";

	/**
	 * Returns the short description of the specified JavaBean property.
	 * Returns null if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static String getPropertyShortDescription(
		Class beanClass, String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName, DESCRIPTION_SHORT);
	}

	/**
	 * Metadata property name of JavaBean property long description (value is
	 * {@value #DESCRIPTION_LONG}).
	 */
	public static final String DESCRIPTION_LONG =
		META_NAME_PREFIX + '.' + "description.long";

	/**
	 * Returns the long description of the specified JavaBean property.
	 * Returns null if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static String getPropertyLongDescription(
		Class beanClass, String propertyName)
	{
		return getPropertyMetaValue(beanClass, propertyName, DESCRIPTION_LONG);
	}

	/**
	 * Metadata property name of the scale of JavaBean decimal property (value
	 * is {@value #DECIMAL_SCALE}).
	 */
	public static final String DECIMAL_SCALE =
		META_NAME_PREFIX + '.' + "decimal.scale";

	/**
	 * Returns the scale of the specified JavaBean decimal property.
	 * Returns null if not found in the JavaBean metadata.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name.
	 */
	public static Integer getPropertyDecimalScale(
		Class beanClass, String propertyName)
	{
		String value =
			getPropertyMetaValue(beanClass, propertyName, DECIMAL_SCALE);

		if (value == null)
		{
			return null;
		}

		return new Integer(value);
	}

	////////////////////////
	//// Implementation ////
	////////////////////////

	/**
	 * Do not instantiate this class.
	 */
	private MetadataHome()
	{
		throw new DoNotInstantiateException();
	}
}
