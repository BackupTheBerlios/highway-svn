/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.javadoc;

import org.highway.collection.EmptyIterator;
import org.highway.helper.ClassHelper;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * JavadocCache implementation based on the metagen build tool.<br>
 * metagen generates a MetagenClassJavadoc subclass named XxxxJavadoc
 * for each source class Xxxx. An instance of the XxxxJavadoc contains
 * all the tags defined in the class Xxxx. MetagenJavadocCache constructs
 * and caches an instance of XxxxJavadoc when tags of Xxxx are requested.
 *
 * @author attias
 */
public class MetagenJavadocCache implements JavadocCache
{
	/**
	 * metagen generated classes suffix. Its value is "Javadoc".
	 */
	public static final String JAVADOC_CLASS_SUFFIX = "Javadoc";
	private static final Object JAVADOC_CLASS_NOT_FOUND = new Object();
	private static Map classJavadocMap = new HashMap();

	/**
	 * Constructs a MetagenJavadocCache. No need of parameters, this object
	 * will find the XxxxJavadoc classes in the classpath.
	 */
	public MetagenJavadocCache()
	{
	}

	public Iterator getClassTagNames(Class targetClass)
	{
		MetagenClassJavadoc javadoc = getClassJavadoc(targetClass);

		if (javadoc == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		Map classTagMap = javadoc.getClassTagMap();

		if (classTagMap == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		return classTagMap.keySet().iterator();
	}

	public String getClassTag(Class targetClass, String tagName)
	{
		MetagenClassJavadoc javadoc = getClassJavadoc(targetClass);

		if (javadoc == null)
		{
			return null;
		}

		Map classTagMap = javadoc.getClassTagMap();

		if (classTagMap == null)
		{
			return null;
		}

		return (String) classTagMap.get(tagName);
	}

	public Iterator getFieldTagNames(Class targetClass, String fieldName)
	{
		MetagenClassJavadoc javadoc = getClassJavadoc(targetClass);

		if (javadoc == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		Map fieldTagMap = javadoc.getFieldTagMap(fieldName);

		if (fieldTagMap == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		return fieldTagMap.keySet().iterator();
	}

	public String getFieldTag(
		Class targetClass, String fieldName, String tagName)
	{
		MetagenClassJavadoc javadoc = getClassJavadoc(targetClass);

		if (javadoc == null)
		{
			return null;
		}

		Map fieldTagMap = javadoc.getFieldTagMap(fieldName);

		if (fieldTagMap == null)
		{
			return null;
		}

		return (String) fieldTagMap.get(tagName);
	}

	public Iterator getMethodTagNames(Method method)
	{
		Class targetClass = method.getDeclaringClass();
		MetagenClassJavadoc javadoc = getClassJavadoc(targetClass);

		if (javadoc == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		Map methodTagMap = javadoc.getMethodTagMap(method);

		if (methodTagMap == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		return methodTagMap.keySet().iterator();
	}

	public String getMethodTag(Method method, String tagName)
	{
		Class targetClass = method.getDeclaringClass();
		MetagenClassJavadoc javadoc = getClassJavadoc(targetClass);

		if (javadoc == null)
		{
			return null;
		}

		Map methodTagMap = javadoc.getMethodTagMap(method);

		if (methodTagMap == null)
		{
			return null;
		}

		return (String) methodTagMap.get(tagName);
	}

	/**
	 * This method is synchronized because this is the only one that updates the
	 * javadoc map.
	 * @param targetClass Class
	 * @return MetagenClassJavadoc
	 */
	private synchronized MetagenClassJavadoc getClassJavadoc(Class targetClass)
	{
		Object javadoc = classJavadocMap.get(targetClass);

		if (javadoc == null)
		{
			javadoc = instantiateClassJavadoc(targetClass);

			if (javadoc == null)
			{
				javadoc = JAVADOC_CLASS_NOT_FOUND;
			}

			classJavadocMap.put(targetClass, javadoc);
		}

		if (javadoc == JAVADOC_CLASS_NOT_FOUND)
		{
			return null;
		}
		else
		{
			return (MetagenClassJavadoc) javadoc;
		}
	}

	/**
	 * Method instantiateClassJavadoc
	 * @param targetClass Class
	 * @return MetagenClassJavadoc
	 */
	private MetagenClassJavadoc instantiateClassJavadoc(Class targetClass)
	{
		try
		{
			return (MetagenClassJavadoc) ClassHelper.newInstance(
				targetClass.getName() + JAVADOC_CLASS_SUFFIX);
		}
		catch (ClassNotFoundException exc)
		{
			return null;
		}
	}
}
