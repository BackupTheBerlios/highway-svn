package org.highway.vo;

import java.lang.annotation.Annotation;

import org.highway.annotation.BeanPropertyDescriptionLong;
import org.highway.annotation.BeanPropertyDescriptionShort;
import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.BeanPropertyPattern;
import org.highway.annotation.BeanPropertyReadOnly;
import org.highway.annotation.BeanPropertyScale;
import org.highway.annotation.BeanPropertySize;
import org.highway.annotation.BeanPropertyUpperCase;
import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.init.InitException;


/**
 * Use the static methods of this class to access the default MetadataManager
 * and get metadata on JavaBean classes.
 * JavadocMetadataManager is the default implementation od MetadataManager.
 * As a result, an instance of JavadocMetadataManager is used if no default
 * MetadataManager is explicitly set.
 *
 * @see org.highway.vo.JavadocMetadataManager
 */
public class AnnotationMetadataHome
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
	private static AnnotationMetadataManager metadataManager =
		new AnnotationMetadataManager();

	/**
	 * Returns the default MetadataManager.
	 */
	public static AnnotationMetadataManager getMetadataManager()
	{
		return metadataManager;
	}

	/**
	 * Sets the default MetadataManager.
	 */
	public static synchronized void setMetadataManager(AnnotationMetadataManager manager)
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
	public static <T extends Annotation> T getClassMetaValue(Class beanClass, Class<T> annotationType)
	{
		if (metadataManager == null)
		{
			throw new InitException("no default MetadataManager set");
		}

		return metadataManager.getClassMetaValue(beanClass, annotationType);
	}

	/**
	 * Delegates to the default MetadataManager.
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
		return metadataManager.getPropertyMetaValue(beanClass, propertyName, annotationType);
	}

	
	//////////////////////////////////
	//// Common property metadata ////
	//////////////////////////////////

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
		BeanPropertySize meta = getPropertyMetaValue(beanClass, propertyName, BeanPropertySize.class);
		if (meta==null)
			return null;
		return meta.min();
	}

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
		BeanPropertySize meta = getPropertyMetaValue(beanClass, propertyName, BeanPropertySize.class);
		if (meta==null)
			return null;
		return meta.max();
	}

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
		BeanPropertyPattern meta = getPropertyMetaValue(beanClass, propertyName, BeanPropertyPattern.class);
		if (meta==null)
			return null;
		return meta.value();
	}

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
		return getPropertyMetaValue(beanClass, propertyName, BeanPropertyMandatory.class)!=null;
	}

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
		return getPropertyMetaValue(beanClass, propertyName, BeanPropertyUpperCase.class)!=null;
	}

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
		return getPropertyMetaValue(beanClass, propertyName, BeanPropertyReadOnly.class)!=null;
	}

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
		BeanPropertyDescriptionShort meta = getPropertyMetaValue(beanClass, propertyName, BeanPropertyDescriptionShort.class);
		if (meta==null)
			return null;
		return meta.value();
	}

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
		BeanPropertyDescriptionLong meta = getPropertyMetaValue(beanClass, propertyName, BeanPropertyDescriptionLong.class);
		if (meta==null)
			return null;
		return meta.value();
	}


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
		BeanPropertyScale meta = getPropertyMetaValue(beanClass, propertyName, BeanPropertyScale.class);
		if (meta==null)
			return null;
		return meta.value();
	}

	////////////////////////
	//// Implementation ////
	////////////////////////

	/**
	 * Do not instantiate this class.
	 */
	private AnnotationMetadataHome()
	{
		throw new DoNotInstantiateException();
	}
}
