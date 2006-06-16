/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;
import org.highway.vo.Decimal;
import org.highway.vo.Enum;

/**
 * Hibernate type matching map. Used by HibernateDatabaseSession to convert
 * query argument Java types to the matching Hibernate type.
 *
 * @since 1.0
 * @author David Attias
 */
class HibernateTypes
{
	/**
	 * Matching type map.
	 * Key = Java Class object, value = Hibernate Type object.
	 */
	private static Map types;

	static
	{
		types = new HashMap();
		
		// primitives
		types.put(int.class, Hibernate.INTEGER);
		types.put(Integer.class, Hibernate.INTEGER);
		types.put(long.class, Hibernate.LONG);
		types.put(Long.class, Hibernate.LONG);
		types.put(short.class, Hibernate.SHORT);
		types.put(Short.class, Hibernate.SHORT);
		types.put(byte.class, Hibernate.BYTE);
		types.put(Byte.class, Hibernate.BYTE);
		types.put(float.class, Hibernate.FLOAT);
		types.put(Float.class, Hibernate.FLOAT);
		types.put(double.class, Hibernate.DOUBLE);
		types.put(Double.class, Hibernate.DOUBLE);
		types.put(char.class, Hibernate.CHARACTER);
		types.put(Character.class, Hibernate.CHARACTER);
		types.put(boolean.class, Hibernate.BOOLEAN);
		types.put(Boolean.class, Hibernate.BOOLEAN);
		
		// simple object types
		types.put(String.class, Hibernate.STRING);
		types.put(BigDecimal.class, Hibernate.BIG_DECIMAL);
		
		// date types
		types.put(java.util.Date.class, Hibernate.TIMESTAMP);
		types.put(java.sql.Date.class, Hibernate.DATE);
		types.put(Locale.class, Hibernate.LOCALE);
		types.put(TimeZone.class, Hibernate.TIMEZONE);
		types.put(Class.class, Hibernate.CLASS);
		
		// socle special types
		// enum types are added to the map at runtime when used
		types.put(Decimal.class, new DecimalHibernateType());
	}

	/**
	 * Returns the Hibernate Type associated with the specified Java type.
	 */
	static Type getType(Class javaType)
	{
		Type type = (Type) types.get(javaType);
		
		if (type == null && Enum.class.isAssignableFrom(javaType))
		{
			String typeClassName = javaType.getName()
				+ EnumHibernateType.ENUM_HIBERNATE_TYPE_CLASS_SUFFIX;
			
			try
			{
				type = (Type) ClassHelper.newInstance(typeClassName);
				types.put(javaType, type);
			}
			catch (ClassNotFoundException exc)
			{
				throw new TechnicalException("can not find Hibernate type "
						+ typeClassName	+ " for enum " + javaType.getName(), exc);
			}
		}
		return type;
	}

	/**
	 * Returns the Hibernate Type associated with the specified parameter type.
	 */
	static Type getParameterType(Object parameter)
	{
		return getType(parameter.getClass());
	}

	/**
	 * Returns the Hibernate Types associated with the specified parameter types.
	 */
	static Type[] getParameterTypes(Object[] parameters)
	{
		Type[] types = new Type[parameters.length];

		for (int i = 0; i < types.length; i++)
		{
			types[i] = HibernateTypes.getParameterType(parameters[i]);
		}

		return types;
	}
}
