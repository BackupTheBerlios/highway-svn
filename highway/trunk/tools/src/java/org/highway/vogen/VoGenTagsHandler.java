/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import org.highway.JavaHelper;
import xdoclet.XDocletException;
import xdoclet.tagshandler.AbstractProgramElementTagsHandler;
import xjavadoc.AbstractClass;
import xjavadoc.XClass;
import xjavadoc.XMember;
import xjavadoc.XMethod;
import xjavadoc.XTag;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class VoGenTagsHandler extends AbstractProgramElementTagsHandler
	implements VoGenTags, VoGenConstantsHibernate2
{
	private static final String HIBERNATE_TYPE_SUFFIX = "HibernateType";
	private static final String ENUM_CLASS_NAME = "org.highway.vo.Enum";
	private static final String DECIMAL_CLASS_NAME =
		"org.highway.vo.Decimal";
	private static final String DECIMAL_HIBERNATE_TYPE_CLASS_NAME =
		"org.highway.database.hibernate.DecimalHibernateType";

	public String generatedShortClassName(Properties attributes)
		throws XDocletException
	{
		return JavaHelper.getShortClassName(generatedFullClassName(attributes));
	}

	public String generatedFullClassName(Properties attributes)
		throws XDocletException
	{
		return VoGenHelper.getGeneratedClassName(getCurrentClass());
	}

	public String entityShortClassName(Properties attributes)
		throws XDocletException
	{
		return JavaHelper.getShortClassName(entityFullClassName(attributes));
	}

	public String entityFullClassName(Properties attributes)
		throws XDocletException
	{
		return VoGenHelper.getEntityClassName(getCurrentClass());
	}

	public String getAbstract(Properties attributes) throws XDocletException
	{
		return VoGenHelper.isAbstract(getCurrentClass()) ? " abstract" : "";
	}

	public String superClassName(Properties attributes)
		throws XDocletException
	{
		XClass xclass = getCurrentClass();
		XTag superClassTag = xclass.getDoc().getTag(VO_SUPERCLASS_TAG);

		if (superClassTag == null)
		{
			Collection interfaces =
				((AbstractClass) xclass).getDeclaredInterfaces();

			for (Iterator iter = interfaces.iterator(); iter.hasNext();)
			{
				String interfaceFullName =
					((XClass) iter.next()).getQualifiedName();

				if (VoGenHelper.isDefInterface(interfaceFullName))
				{
					return VoGenHelper.removeDefSuffix(interfaceFullName);
				}
			}

			return VoGenConstantsHibernate2.VALUE_OBJECT_ABSTRACT_CLASS_NAME;
		}
		else
		{
			return superClassTag.getValue();
		}
	}

	public String constantName(Properties attributes) throws XDocletException
	{
		if (getCurrentMethod() != null)
		{
			return JavaHelper.getConstantNameFromPropertyName(
				getCurrentMethod().getPropertyName());
		}

		throw new VoGenException("no current method set");
	}

	public String propertyName(Properties attributes) throws XDocletException
	{
		if (getCurrentMethod() != null)
		{
			return getCurrentMethod().getPropertyName();
		}

		throw new VoGenException("no current method set");
	}

	public String propertyType(Properties attributes) throws XDocletException
	{
		if (getCurrentMethod() != null)
		{
			return getCurrentMethod().getPropertyType().getType()
					   .getQualifiedName();
		}

		throw new VoGenException("no current method set");
	}

	public String propertyDimension(Properties attributes) throws XDocletException
	{
		if (getCurrentMethod() != null)
		{
			return getCurrentMethod().getPropertyType().getDimensionAsString();
		}

		throw new VoGenException("no current method set");
	}
	
	public String propertyTypeAndDimension(Properties attributes) throws XDocletException {
		if (getCurrentMethod() != null) {
			StringBuffer sb = new StringBuffer(getCurrentMethod().getPropertyType().getType()
					   .getQualifiedName());
			sb.append(getCurrentMethod().getPropertyType().getDimensionAsString());
			return sb.toString();
		}
		throw new VoGenException("no current method set");
		
	}
	
	public String propertyHibernateType(Properties attributes)
		throws XDocletException
	{
		if (getCurrentMethod() == null)
		{
			throw new VoGenException("no current method set");
		}
		
		// case where the hibernate type is explicitly specified
		String converter = getCurrentMethod().getDoc().getTagAttributeValue(PROPERTY_MAPPING_TAG, TYPE_ATTRIBUTE);
		if (converter != null)
		{
			return converter;
		}
		
		String propertyType = propertyType(attributes);

		// case where the property type extends Enum and is qualified 
		// the hibernate type is the property type + suffix
		XClass propertyXClass = getXJavaDoc().getXClass(propertyType);

		if (propertyXClass.isA(ENUM_CLASS_NAME, true))
		{
			return propertyType + HIBERNATE_TYPE_SUFFIX;
		}

		// case where the property type extends Decimal
		if (propertyXClass.isA(DECIMAL_CLASS_NAME, true))
		{
			return DECIMAL_HIBERNATE_TYPE_CLASS_NAME;
		}

		// case where the property type extends Enum and is not qualified
		// the hibernate type is the property type + suffix
		String currentPackageName =
			getCurrentClass().getContainingPackage().getName();
		String enumClassName =
			JavaHelper.isNullOrEmpty(currentPackageName) ? propertyType
														 : (currentPackageName
			+ '.' + propertyType);
		propertyXClass = getXJavaDoc().getXClass(enumClassName);

		if (propertyXClass.isA(ENUM_CLASS_NAME, true))
		{
			return propertyType + HIBERNATE_TYPE_SUFFIX;
		}

		// case where the property type is not an enum:
		// the hibernate type is the property type
		return propertyType;
	}

	public String columnName(Properties attributes) throws XDocletException
	{
		String columnName =
			VoGenHelper.getColumnName(getCurrentPropertyMember());

		return (columnName == null) ? propertyName(attributes) : columnName;
	}

	public String getMethodName(Properties attributes)
		throws XDocletException
	{
		if (getCurrentMethod() != null)
		{
			return getCurrentMethod().getName();
		}

		throw new VoGenException("no current method set");
	}

	public String setMethodName(Properties attributes)
		throws XDocletException
	{
		return JavaHelper.getSetterName(propertyName(attributes));
	}

	public void forAllProperties(String template, Properties attributes)
		throws XDocletException
	{
		// false -> no method from superclasses
		Iterator methods = getCurrentClass().getMethods(false).iterator();

		while (methods.hasNext())
		{
			XMethod method = (XMethod) methods.next();

			if (method.isPropertyAccessor())
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

	public static XMember getCurrentPropertyMember() throws VoGenException
	{
		XMember member = getCurrentMethod();

		if (member == null)
		{
			throw new VoGenException("no current method set");
		}

		return member;
	}
}
