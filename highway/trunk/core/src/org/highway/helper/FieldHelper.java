/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author attias
 */
public class FieldHelper
{
	/**
	 * Field nonStaticFieldsCache
	 */
	private static Map nonStaticFieldsCache = new HashMap();

	private static List getAllFieldsNoCache(Class realClass, Class upperClass)
	{
		if (realClass == upperClass)
		{
			return new ArrayList(0);
		}

		List fields =
			getAllFieldsNoCache(realClass.getSuperclass(), upperClass);
		fields.addAll(Arrays.asList(realClass.getDeclaredFields()));

		return fields;
	}

	/**
	 * Retourne la liste des attributs de la classe realClass en remontant
	 * jusqu'à la super classe upperClass.
	 * @param realClass la classe de l'objet
	 * @param upperClass la classe la plus haute
	 * @return une liste d'objets Field
	 */
	public static List getNonStaticFields(Class realClass, Class upperClass)
	{
		List nonStaticFields = (List) nonStaticFieldsCache.get(realClass);

		if (nonStaticFields == null)
		{
			nonStaticFields = getAllFieldsNoCache(realClass, upperClass);

			for (Iterator iter = nonStaticFields.iterator(); iter.hasNext();)
			{
				Field field = (Field) iter.next();

				if (Modifier.isStatic(field.getModifiers()))
				{
					iter.remove();
				}
			}

			nonStaticFieldsCache.put(realClass, nonStaticFields);
		}

		return nonStaticFields;
	}

	/**
	 * Method getClassAndFieldName
	 * @param field Field
	 * @param qualified boolean
	 * @return String
	 */
	public static String getClassAndFieldName(Field field, boolean qualified)
	{
		return dumpClassAndFieldName(new StringBuffer(30), field, qualified)
				   .toString();
	}

	/**
	 * Method dumpClassAndFieldName
	 * @param buffer StringBuffer
	 * @param field Field
	 * @param qualified boolean
	 * @return StringBuffer
	 */
	public static StringBuffer dumpClassAndFieldName(
		StringBuffer buffer, Field field, boolean qualified)
	{
		if (qualified)
		{
			buffer.append(field.getDeclaringClass().getName());
		}
		else
		{
			ClassHelper.dumpClassName(buffer, field.getDeclaringClass(), false);
		}

		return buffer.append('.').append(field.getName());
	}
}
