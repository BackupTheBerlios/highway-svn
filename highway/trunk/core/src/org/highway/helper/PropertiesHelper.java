/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.exception.TechnicalException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class contains static methods to help manage properties.
 *
 * 
 * @since 1.1
 */
public class PropertiesHelper
{
	/**
	 * Converts the specified property value to a boolean.
	 * Returns false if the property value is null.
	 *
	 * @param the property string value
	 * @return the property value converted to boolean
	 */
	public static boolean toBoolean(String propertyValue)
	{
		return (propertyValue == null) ? false
									   : Boolean.valueOf(propertyValue)
												.booleanValue();
	}

	/**
	 * Returns the properties loaded from a resource with the specified path.
	 * Returns null if the resource is not found.
	 * @param path the class path of the resource to load
	 * @return a Properties object or null
	 * @since 1.3
	 */
	public static Properties loadAsResource(String resourcePath)
	{
		try
		{
			InputStream stream =
				ResourceHelper.getResourceAsStream(resourcePath);

			if (stream == null)
			{
				return null;
			}

			Properties properties = new Properties();
			properties.load(stream);

			return properties;
		}
		catch (IOException exc)
		{
			throw new TechnicalException(
				"Unable to load properties from " + resourcePath, exc);
		}
	}

	/**
	 * Returns the properties loaded from a resource in the specified context
	 * package and with the specified name. Returns null if the resource is
	 * not found.
	 * @param context object whom class is in the same package than the resource
	 * @param name the name of the resource
	 * @return a Properties object or null
	 * @since 1.3
	 */
	public static Properties loadAsResource(
		Object context, String resourceName)
	{
		return loadAsResource(
			ResourceHelper.getResourcePath(context, resourceName));
	}
}
