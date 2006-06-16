/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ognl;

import org.highway.vo.Enum;
import ognl.DefaultTypeConverter;
import java.util.Map;

/**
 * <p>Conversion from or toward Enum values</p>
 * <p>To instruct WebWork to use this class for any Enum class, you must put the line below in a file called "xwork-conversion.properties" in the root of your application classpath :
 * <tt>org.highway.vo.Enum = org.highway.ognl.EnumConverter</tt></p>
 * @since 1.1
 * @author GRIZARD
 * @see org.highway.vo.Enum
 */
public class EnumConverter extends DefaultTypeConverter
{
	/**
	 * Conversion of an object to an enumerate value or of an enumerate value to an object of a given type
	 * @param context a context
	 * @param value the value to convert
	 * @param toType the target type of the conversion
	 * @return the result of the conversion
	 */
	public Object convertValue(Map context, Object value, Class toType)
	{
		// Dans le cas d'un tableau de valeurs, on ne s'intéresse qu'à la première
		if (value instanceof Object[])
		{
			value = ((Object[]) value)[0];
		}

		if (
			Enum.class.isAssignableFrom(toType)
				&& String.class.isAssignableFrom(value.getClass()))
		{
			// Conversion d'une String en Enum : celle-ci doit correspondre au code de l'Enum
			return Enum.getEnumFromString(toType, (String) value);
		}
		else if (value instanceof Enum)
		{
			// Conversion d'un Enum en String
			return value.toString();
		}

		return super.convertValue(context, value, toType);
	}
}
