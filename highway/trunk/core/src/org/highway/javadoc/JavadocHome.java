/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.javadoc;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Home class of the javadoc package.<br>
 * Provides static access methods to the default javadoc cache object.<br>
 * The default javadoc cache object must be set by the application code.<br>
 * No default javadoc cache means exception of type InitException thrown.
 *
 * @author attias
 */
public class JavadocHome
{
	/**
	 * Default javadoc cache object.
	 */
	private static JavadocCache javadocCache = new MetagenJavadocCache();

	/**
	 * Do not instantiate this class.
	 */
	private JavadocHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default JavadocManager.
	 */
	public static JavadocCache getJavadocCache()
	{
		return javadocCache;
	}

	/**
	 * Returns the default JavadocManager.
	 * Throws an <tt>InitException</tt> if no manager is set.
	 */
	private static JavadocCache getSafeJavadocManager()
	{
		if (javadocCache == null)
		{
			throw new InitException("no default JavadocManager set");
		}

		return javadocCache;
	}

	/**
	 * Sets the default implementation of the JavadocCache.
	 *
	 * @param cache the cache to set
	 */
	public static synchronized void setJavadocCache(JavadocCache cache)
	{
		javadocCache = cache;
		DebugHome.getDebugLog().info("Default JavadocCache is set to " + cache);
	}

	/**
	 * Delegates to the default JavadocCache.
	 *
	 * @throws InitException if no default JavadocManager is set
	 * @see org.highway.javadoc.JavadocCache#getClassTag(Class,String)
	 */
	public static String getClassTag(Class targetClass, String tagName)
	{
		return getSafeJavadocManager().getClassTag(targetClass, tagName);
	}

	/**
	 * Delegates to the default JavadocCache.
	 *
	 * @throws InitException if no default JavadocManager is set
	 * @see org.highway.javadoc.JavadocCache#getClassTagNames(Class)
	 */
	public static Iterator getClassTagNames(Class targetClass)
	{
		return getSafeJavadocManager().getClassTagNames(targetClass);
	}

	/**
	 * Delegates to the default JavadocCache.
	 *
	 * @throws InitException if no default JavadocManager is set
	 * @see org.highway.javadoc.JavadocCache#getFieldTag(Class,String,String)
	 */
	public static String getFieldTag(
		Class targetClass, String fieldName, String tagName)
	{
		return getSafeJavadocManager().getFieldTag(
			targetClass, fieldName, tagName);
	}

	/**
	 * Delegates to the default JavadocCache.
	 *
	 * @throws InitException if no default JavadocManager is set
	 * @see org.highway.javadoc.JavadocCache#getFieldTagNames(Class,String)
	 */
	public static Iterator getFieldTagNames(
		Class targetClass, String fieldName)
	{
		return getSafeJavadocManager().getFieldTagNames(targetClass, fieldName);
	}

	/**
	 * Delegates to the default JavadocCache.
	 *
	 * @throws InitException if no default JavadocManager is set
	 * @see org.highway.javadoc.JavadocCache#getMethodTag(Method,String)
	 */
	public static String getMethodTag(Method method, String tagName)
	{
		return getSafeJavadocManager().getMethodTag(method, tagName);
	}

	/**
	 * Delegates to the default JavadocCache.
	 *
	 * @throws InitException if no default JavadocManager is set
	 * @see org.highway.javadoc.JavadocCache#getMethodTagNames(Method)
	 */
	public static Iterator getMethodTagNames(Method method)
	{
		return getSafeJavadocManager().getMethodTagNames(method);
	}
}
