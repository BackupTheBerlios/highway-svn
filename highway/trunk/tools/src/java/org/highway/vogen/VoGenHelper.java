/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import xdoclet.XDocletException;
import xjavadoc.XClass;
import xjavadoc.XDoc;
import xjavadoc.XMember;

/**
 * @author attias
 */
public class VoGenHelper implements VoGenTags
{
	public static String getEntityClassName(XClass xclass)
	{
		String className = xclass.getQualifiedName();

		if (VoGenHelper.isDefInterface(className))
		{
			className = VoGenHelper.removeDefSuffix(className);
		}

		return className;
	}

	public static String getGeneratedClassName(XClass xclass)
	{
		String className = getEntityClassName(xclass);

		if (VoGenHelper.isBaseOnly(xclass))
		{
			className = className + "Base";
		}

		return className;
	}

	public static String getColumnName(XMember xmember)
		throws XDocletException
	{
		XDoc xdoc = xmember.getDoc();

		if (xdoc.hasTag(ID_MAPPING_TAG) && xdoc.hasTag(PROPERTY_MAPPING_TAG))
		{
			throw new XDocletException(
				"tags " + ID_MAPPING_TAG + " and " + PROPERTY_MAPPING_TAG
				+ " are mutualy exclusive");
		}

		String columnName =
			xdoc.getTagAttributeValue(ID_MAPPING_TAG, COLUMN_ATTRIBUTE);

		if (columnName != null)
		{
			return columnName;
		}

		columnName =
			xdoc.getTagAttributeValue(PROPERTY_MAPPING_TAG, COLUMN_ATTRIBUTE);

		if (columnName != null)
		{
			return columnName;
		}

		return null;
	}

	public static boolean isAbstract(XClass xclass)
	{
		return xclass.getDoc().hasTag(VO_ABSTRACT_TAG) || isBaseOnly(xclass);
	}

	public static boolean isBaseOnly(XClass xclass)
	{
		return xclass.getDoc().hasTag(VO_BASE_ONLY);
	}

	public static boolean isDefInterface(XClass xclass)
	{
		return VoGenHelper.isDefInterface(xclass.getName());
	}

	public static boolean isDefInterface(String className)
	{
		return className.endsWith("Def");
	}

	public static String removeDefSuffix(String className)
	{
		return className.substring(0, className.length() - 3);
	}
}
