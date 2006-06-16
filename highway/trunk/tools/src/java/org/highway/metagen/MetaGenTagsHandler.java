/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.metagen;

import org.apache.commons.lang.StringEscapeUtils;
import xdoclet.XDocletException;
import xdoclet.tagshandler.AbstractProgramElementTagsHandler;
import xjavadoc.XField;
import xjavadoc.XMethod;
import xjavadoc.XParameter;
import xjavadoc.XTag;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class MetaGenTagsHandler extends AbstractProgramElementTagsHandler
{
	private XTag currentTag;

	public void forAllClassTags(String template, Properties attributes)
		throws XDocletException
	{
		XTag oldTag = getCurrentClassTag();

		// false = get tags only directly declared in this class
		Collection tags = getCurrentClass().getDoc().getTags(false);

		for (Iterator iter = tags.iterator(); iter.hasNext();)
		{
			currentTag = (XTag) iter.next();
			setCurrentClassTag(currentTag);
			generate(template);
		}

		setCurrentClassTag(oldTag);
	}

	public void forAllMethodTags(String template, Properties attributes)
		throws XDocletException
	{
		XMethod oldMethod = getCurrentMethod();

		// we pass false not to consider the superclasses
		Iterator methods = getCurrentClass().getMethods(false).iterator();

		while (methods.hasNext())
		{
			XMethod method = (XMethod) methods.next();
			setCurrentMethod(method);

			XTag oldTag = getCurrentMethodTag();

			// we pass false not to consider the superclasses
			Iterator tags = method.getDoc().getTags(false).iterator();

			while (tags.hasNext())
			{
				currentTag = (XTag) tags.next();
				setCurrentMethodTag(currentTag);
				generate(template);
			}

			setCurrentMethodTag(oldTag);
		}

		setCurrentMethod(oldMethod);
	}

	public void forAllFieldTags(String template, Properties attributes)
		throws XDocletException
	{
		XField oldField = getCurrentField();

		// we pass false not to consider the superclasses
		Iterator fields = getCurrentClass().getFields(false).iterator();

		while (fields.hasNext())
		{
			XField field = (XField) fields.next();
			setCurrentField(field);

			XTag oldTag = getCurrentFieldTag();

			// we pass false not to consider the superclasses
			Iterator tags = field.getDoc().getTags(false).iterator();

			while (tags.hasNext())
			{
				currentTag = (XTag) tags.next();
				setCurrentFieldTag(currentTag);
				generate(template);
			}

			setCurrentFieldTag(oldTag);
		}

		setCurrentField(oldField);
	}

	public String tagName(Properties attributes) throws XDocletException
	{
		return currentTag.getName();
	}

	public String tagValue(Properties attributes) throws XDocletException
	{
		return StringEscapeUtils.escapeJava(currentTag.getValue());
	}

	public String parameterTypes(Properties attributes)
		throws XDocletException
	{
		StringBuffer buffer = new StringBuffer(100);
		Iterator parameters = getCurrentMethod().getParameters().iterator();

		while (parameters.hasNext())
		{
			XParameter parameter = (XParameter) parameters.next();
			buffer.append(parameter.getType().getQualifiedName());
			buffer.append(parameter.getDimensionAsString());
			if (parameters.hasNext())
			{
				buffer.append(',');
			}
		}

		return buffer.toString();
	}
}
