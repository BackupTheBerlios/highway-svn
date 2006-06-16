/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.javadoc;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Implemenation of this interface provide access to the
 * class, field and method javadoc tags.<br>
 * <br>
 * The default implementation must be set through the <code>JavadocHome</code> class.
 *
 * @author attias
 */
public interface JavadocCache
{
	/**
	 * Returns an iterator of the class tag names of the specified class.
	 *
	 * @param targetClass the class searched for the tag names
	 * @return iterator of tag names of type String
	 */
	public Iterator getClassTagNames(Class targetClass);

	/**
	 * Returns the class tag value associated to the specified class and
	 * tag name.
	 *
	 * @param targetClass the class searched for the tag
	 * @param tagName the tag name
	 * @return the tag value
	 */
	public String getClassTag(Class targetClass, String tagName);

	/**
	 * Returns an iterator of the field tag names of the specified field.
	 *
	 * @param targetClass the class of the field
	 * @param fieldName the name of the field
	 * @return iterator of tag names of type String
	 */
	public Iterator getFieldTagNames(Class targetClass, String fieldName);

	/**
	 * Returns the field tag value associated to the specified class,
	 * field and tag.
	 *
	 * @param targetClass the class searched for the tag
	 * @param fieldName the field name
	 * @param tagName the tag name
	 * @return the tag value
	 */
	public String getFieldTag(
		Class targetClass, String fieldName, String tagName);

	/**
	 * Returns an iterator of the method tag names of the specified method.
	 *
	 * @param method the method searched for tags
	 * @return iterator of tag names of type String
	 */
	public Iterator getMethodTagNames(Method method);

	/**
	 * Returns the method tag value associated to the specified method and tag.
	 *
	 * @param method the method searched for the tag
	 * @param tagName the tag name
	 * @return the tag value
	 */
	public String getMethodTag(Method method, String tagName);
}
