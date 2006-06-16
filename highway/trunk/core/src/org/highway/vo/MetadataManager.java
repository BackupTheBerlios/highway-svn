/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vo;


/**
 * A MetadataManager manages JavaBean class and property metadata.
 * The standard implementaion is JavadocMetadataManager.
 * Use MetadataHome to access the default MetadataManager and get metadata.
 *
 * @since 1.2
 * @author David Attias
 * @see org.highway.vo.JavadocMetadataManager
 */
public interface MetadataManager
{
	/**
	 * Returns the metadata value of the specified metadata property
	 * associated with specified JavaBean class.
	 * Searches the JavaBean class and superclasses.
	 *
	 * @param beanClass the JavaBean class
	 * @param metaName the metadata property name
	 */
	String getClassMetaValue(Class beanClass, String metaName);

	/**
	 * Returns the metadata value of the specified metadata property
	 * associated with the specified JavaBean property.
	 * Searches the JavaBean class and superclasses.
	 *
	 * @param beanClass the JavaBean class
	 * @param propertyName the JavaBean property name
	 * @param metaName the meta property name
	 */
	String getPropertyMetaValue(
		Class beanClass, String propertyName, String metaName);
}
