/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ognl;

import org.highway.vo.Decimal;
import ognl.DefaultTypeConverter;
import java.util.Map;

/**
 * <p>Conversion from or toward Decimal values</p>
 * <p>To instruct WebWork to use this class, you must put the line below in a file called "xwork-conversion.properties" in the root of your application classpath :
 * <tt>org.highway.vo.Decimal = org.highway.ognl.DecimalConverter</tt></p>
 * @since 1.1
 * @author GRIZARD
 * @see org.highway.vo.Decimal
 */
public class DecimalConverter extends DefaultTypeConverter
{
	/**
	 * Conversion of an object to a Decimal or of a Decimal to an object of a given type
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

		if (toType == String.class)
		{
			// Conversion de Decimal en String
			return value.toString();
		}
		else if (
			(toType == Decimal.class)
				&& value.getClass().isAssignableFrom(String.class))
		{
			// Conversion de String en Decimal
			return new Decimal((String) value);
		}

		return super.convertValue(context, value, toType);
	}
}
