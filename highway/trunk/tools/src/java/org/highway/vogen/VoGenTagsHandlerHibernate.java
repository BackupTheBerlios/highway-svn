/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import xdoclet.XDocletException;
import xdoclet.XDocletTagSupport;
import xdoclet.tagshandler.ClassTagsHandler;
import xjavadoc.AbstractClass;
import xjavadoc.XClass;
import xjavadoc.XDoc;
import xjavadoc.XMember;
import xjavadoc.XMethod;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class VoGenTagsHandlerHibernate extends XDocletTagSupport
	implements VoGenConstantsHibernate2, VoGenTags
{
	public void ifHasCompositeId(String template, Properties attributes)
		throws XDocletException
	{
		if (countId() > 1)
		{
			generate(template);
		}
	}

	public void ifHasPrimitiveId(String template, Properties attributes)
		throws XDocletException
	{
		if (countId() == 1)
		{
			generate(template);
		}
	}

	public void ifIsClass(String template, Properties attributes)
		throws XDocletException
	{
		if (superEntity() == null)
		{
			generate(template);
		}
	}

	public void ifIsJoinedSubclass(String template, Properties attributes)
		throws XDocletException
	{
		if (
			(superEntity() != null)
				&& ! getCurrentClass().getDoc().hasTag(
					DISCRIMINATOR_MAPPING_TAG, true))
		{
			generate(template);
		}
	}

	public void ifIsSubclass(String template, Properties attributes)
		throws XDocletException
	{
		if (
			(superEntity() != null)
				&& getCurrentClass().getDoc().hasTag(
					DISCRIMINATOR_MAPPING_TAG, true))
		{
			generate(template);
		}
	}

	public String keyColumn(Properties attributes) throws XDocletException
	{
		XMember idMember = getHierarchyIdProperty();

		if (idMember == null)
		{
			throw new VoGenException(
				"no id mapping found in hierarchy of class "
				+ getCurrentClass().getName());
		}

		String columnName = VoGenHelper.getColumnName(idMember);

		if ((columnName == null) && idMember instanceof XMethod)
		{
			columnName = ((XMethod) idMember).getPropertyName();
		}

		return columnName;
	}

	public String superClassName(Properties attributes)
		throws XDocletException
	{
		XClass superEntity = superEntity();

		if (superEntity == null)
		{
			throw new XDocletException(
				"Joined subclass " + getCurrentClass().getName()
				+ " must extend a mapped class");
		}

		return VoGenHelper.getEntityClassName(superEntity);
	}

	private int countId()
	{
		int count = 0;

		Iterator methods = getCurrentClass().getMethods(true).iterator();

		while (methods.hasNext())
		{
			XMethod xmethod = (XMethod) methods.next();

			if (xmethod.getDoc().hasTag(ID_MAPPING_TAG))
			{
				count++;
			}
		}

		return count;
	}

	private XClass superEntity()
	{
		AbstractClass xclass = (AbstractClass) getCurrentClass();
		Iterator interfaces = xclass.getDeclaredInterfaces().iterator();

		while (interfaces.hasNext())
		{
			XClass superEntityDefInterface = (XClass) interfaces.next();
			XDoc xdoc = superEntityDefInterface.getDoc();

			if (xdoc.hasTag(VO_MAPPING_TAG))
			{
				return superEntityDefInterface;
			}
		}

		return null;
	}

	private XMember getHierarchyIdProperty()
	{
		Iterator methods = getCurrentClass().getMethods(true).iterator();

		while (methods.hasNext())
		{
			XMethod xmethod = (XMethod) methods.next();

			if (xmethod.getDoc().hasTag(ID_MAPPING_TAG))
			{
				return xmethod;
			}
		}

		return null;
	}

	public void forAllPersistentClasses(String template, Properties attributes)
		throws XDocletException
	{
		Collection generated = new ArrayList();
		Iterator xclasses = ClassTagsHandler.getAllClasses().iterator();

		while (xclasses.hasNext())
		{
			XClass xclass = (XClass) xclasses.next();

			if (xclass.getDoc().hasTag(VO_MAPPING_TAG, false))
			{
				pushCurrentClass(xclass);
				generate(template, generated);
				popCurrentClass();
			}
		}
	}

	public void forAllMethodTags(String template, Properties attributes)
		throws XDocletException
	{
		String tagName = attributes.getProperty("tagName");
		boolean superclasses =
			new Boolean(attributes.getProperty("superclasses")).booleanValue();

		Iterator methods =
			getCurrentClass().getMethods(superclasses).iterator();

		while (methods.hasNext())
		{
			XMethod method = (XMethod) methods.next();

			if ((tagName == null) || method.getDoc().hasTag(tagName))
			{
				setCurrentMethod(method);
				generate(template);
			}
		}

		// clean the current context
		// this is important to avoid
		// currentField != null and currentMethod != null
		// at the same time
		setCurrentMethod(null);
	}

	public void ifHasMethodTag(String template, Properties attributes)
		throws XDocletException
	{
		if (
			methodTagValue(
					attributes.getProperty("tagName"),
					attributes.getProperty("paramName")) != null)
		{
			generate(template);
		}
	}

	public String methodTagValue(Properties attributes)
		throws XDocletException
	{
		String result =
			methodTagValue(
				attributes.getProperty("tagName"),
				attributes.getProperty("paramName"));

		return (result == null) ? attributes.getProperty("default") : result;
	}

	private String methodTagValue(String tagName, String paramName)
	{
		XMethod method = getCurrentMethod();

		if (method != null)
		{
			return method.getDoc().getTagAttributeValue(tagName, paramName);
		}

		return null;
	}

	private void generate(String template, Collection generated)
		throws XDocletException
	{
		XClass superEntity = superEntity();

		if ((superEntity != null) && ! generated.contains(superEntity))
		{
			pushCurrentClass(superEntity);
			generate(template, generated);
			popCurrentClass();
		}

		XClass xclass = getCurrentClass();

		if (! generated.contains(xclass))
		{
			generate(template);
			generated.add(xclass);
		}
	}

	public String resource()
	{
		String result = VoGenHelper.getEntityClassName(getCurrentClass());
		result = result.replace('.', '/');
		result =
			MessageFormat.format(
				HIBERNATE_MAPPING_OUTPUT_FILE_NAME, new Object[] { result });

		return result;
	}
}
