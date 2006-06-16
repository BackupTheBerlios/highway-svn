/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.javadoc;

import org.highway.collection.MapOfMapImpl;
import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * This class represents a javadoc tag container.<br>
 * metagen generates a MetagenClassJavadoc subclass named XxxxJavadoc
 * for each source class Xxxx. An instance of the XxxxJavadoc contains
 * all the tags defined in the class Xxxx. The XxxxJavadoc constructor
 * adds all the tag name/value pairs through this class add methods.
 *
 * @author attias
 */
public abstract class MetagenClassJavadoc
{
	private Class targetClass;
	private Map classTags;
	private MapOfMapImpl fieldTags;
	private MapOfMapImpl methodTags;

	/**
	 * Constructs a MetagenClassJavadoc.
	 *
	 * @param targetClass the class for which this object contains
	 * tag name/value pairs
	 */
	protected MetagenClassJavadoc(Class targetClass)
	{
		this.targetClass = targetClass;
	}

	/**
	 * Method getTargetClass
	 * @return Class
	 */
	Class getTargetClass()
	{
		return targetClass;
	}

	/**
	 * Method getClassTagMap
	 * @return Map
	 */
	Map getClassTagMap()
	{
		return classTags;
	}

	/**
	 * Method getFieldTagMap
	 * @param fieldName String
	 * @return Map
	 */
	Map getFieldTagMap(String fieldName)
	{
		return (fieldTags == null) ? null : fieldTags.get(fieldName, false);
	}

	/**
	 * Method getMethodTagMap
	 * @param method Method
	 * @return Map
	 */
	Map getMethodTagMap(Method method)
	{
		return (methodTags == null) ? null : methodTags.get(method, false);
	}

	/**
	 * Adds a class tag to this javadoc tag container.<br>
	 * Do not use this method, it is used by metagen generated classes.
	 *
	 * @param tagName the tag name
	 * @param tagValue the tag value
	 */
	protected void addClassTag(String tagName, String tagValue)
	{
		if (classTags == null)
		{
			classTags = new HashMap();
		}

		classTags.put(tagName, tagValue);
	}

	/**
	 * Adds a field tag to this javadoc tag container.<br>
	 * Do not use this method, it is used by metagen generated classes.
	 *
	 * @param fieldName the field name
	 * @param tagName the tag name
	 * @param tagValue the tag value
	 */
	protected void addFieldTag(
		String fieldName, String tagName, String tagValue)
	{
		if (fieldTags == null)
		{
			fieldTags = new MapOfMapImpl();
		}

		fieldTags.put(fieldName, tagName, tagValue);
	}

	/**
	 * Adds a method tag to this javadoc tag container.<br>
	 * Do not use this method, it is used by metagen generated classes.
	 *
	 * @param methodName the method name
	 * @param parameterTypeNames the parameter type names separated by commas
	 * @param tagName the tag name
	 * @param tagValue the tag value
	 */
	protected void addMethodTag(
		String methodName, String parameterTypeNames, String tagName,
		String tagValue)
	{
		try
		{
			Class[] parameterTypes = loadParameterTypes(parameterTypeNames);
			Method method = targetClass.getMethod(methodName, parameterTypes);

			if (methodTags == null)
			{
				methodTags = new MapOfMapImpl();
			}

			methodTags.put(method, tagName, tagValue);
		}
		catch (SecurityException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (NoSuchMethodException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (ClassNotFoundException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method loadParameterTypes
	 * @param parameterTypeNames String
	 * @return Class[]
	 * @throws ClassNotFoundException
	 */
	private Class[] loadParameterTypes(String parameterTypeNames)
		throws ClassNotFoundException
	{
		StringTokenizer tokenizer =
			new StringTokenizer(parameterTypeNames, ",");
		Class[] types = new Class[tokenizer.countTokens()];

		for (int i = 0; i < types.length; i++)
		{
			types[i] = ClassHelper.loadClass(tokenizer.nextToken());
		}

		return types;
	}
}
