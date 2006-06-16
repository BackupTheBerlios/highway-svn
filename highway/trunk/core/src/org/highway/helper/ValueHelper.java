/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;


/**
 * Provides static helper methods on object and primitive values.
 *
 * @author attias
 */
public final class ValueHelper
{
	/**
	 * int null value equals Integer min value.
	 */
	public static final int NULL_INT = Integer.MIN_VALUE;

	/**
	 * long null value equals Long min value.
	 */
	public static final long NULL_LONG = Long.MIN_VALUE;

	/**
	 * float null value equals Float min value.
	 */
	public static final float NULL_FLOAT = Float.MIN_VALUE;

	/**
	 * double null value equals Double min value.
	 */
	public static final double NULL_DOUBLE = Double.MIN_VALUE;

	/**
	 * Empty String.
	 */
	public static final String EMPTY_STRING = "";

	//////////////////////////
	///// isNull methods /////
	//////////////////////////

	/**
	 * Determines if the specified object is null.
	 */
	public static final boolean isNull(Object obj)
	{
		return obj == null;
	}

	/**
	 * Determines if the specified int value is null.
	 */
	public static final boolean isNull(int i)
	{
		return (i == NULL_INT);
	}

	/**
	 * Determines if the specified long value is null.
	 */
	public static final boolean isNull(long l)
	{
		return (l == NULL_LONG);
	}

	/**
	 * Determines if the specified float value is null.
	 */
	public static final boolean isNull(float f)
	{
		return (f == NULL_FLOAT);
	}

	/**
	 * Determines if the specified double value is null.
	 */
	public static final boolean isNull(double d)
	{
		return (d == NULL_DOUBLE);
	}

	//////////////////////////
	///// equals methods /////
	//////////////////////////

	/**
	 * Determines if the two specified objects are equals.
	 */
	public static boolean equals(Object value1, Object value2)
	{
		if (isNull(value1) && isNull(value2))
		{
			return true;
		}
		else if (isNull(value1) || isNull(value2))
		{
			return false;
		}
		else
		{
			return value1.equals(value2);
		}
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(byte value1, byte value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(short value1, short value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(int value1, int value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(long value1, long value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(double value1, double value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(float value1, float value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(char value1, char value2)
	{
		return value1 == value2;
	}

	/**
	 * Determines if the two specified values are equals.
	 */
	public static boolean equals(boolean value1, boolean value2)
	{
		return value1 == value2;
	}

	////////////////////////
	///// wrap methods /////
	////////////////////////

	/**
	 * Wraps an int value into an Integer object.
	 */
	public static final Integer wrap(int i)
	{
		return new Integer(i);
	}

	/**
	 * Wraps a long value into an Long object.
	 */
	public static final Long wrap(long l)
	{
		return new Long(l);
	}

	/**
	 * Wraps a byte value into an Byte object.
	 */
	public static final Byte wrap(byte b)
	{
		return new Byte(b);
	}

	/**
	 * Wraps a short value into an Short object.
	 */
	public static final Short wrap(short s)
	{
		return new Short(s);
	}

	/**
	 * Wraps a char value into an Character object.
	 */
	public static final Character wrap(char c)
	{
		return new Character(c);
	}

	/**
	 * Wraps a double value into an Double object.
	 */
	public static final Double wrap(double d)
	{
		return new Double(d);
	}

	/**
	 * Wraps a float value into an Float object.
	 */
	public static final Float wrap(float f)
	{
		return new Float(f);
	}

	/**
	 * Wraps a boolean value into an Boolean object.
	 */
	public static final Boolean wrap(boolean b)
	{
		return (b ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * Returns the specified object. Convenient method used
	 * by generated code only.
	 */
	public static final Object wrap(Object o)
	{
		return o;
	}

	/////////////////////////////////
	///// Static unwrap methods /////
	/////////////////////////////////

	/**
	 * Unwraps the specified Integer object into an int value.
	 */
	public static final int unwrap(Integer i)
	{
		return i.intValue();
	}

	/**
	 * Unwraps the specified Long object into a long value.
	 */
	public static final long unwrap(Long l)
	{
		return l.longValue();
	}

	/**
	 * Unwraps the specified Byte object into a byte value.
	 */
	public static final byte unwrap(Byte b)
	{
		return b.byteValue();
	}

	/**
	 * Unwraps the specified Short object into a short value.
	 */
	public static final short unwrap(Short s)
	{
		return s.shortValue();
	}

	/**
	 * Unwraps the specified Character object into a char value.
	 */
	public static final char unwrap(Character c)
	{
		return c.charValue();
	}

	/**
	 * Unwraps the specified Double object into a double value.
	 */
	public static final double unwrap(Double d)
	{
		return d.doubleValue();
	}

	/**
	 * Unwraps the specified Float object into a float value.
	 */
	public static final float unwrap(Float f)
	{
		return f.floatValue();
	}

	/**
	 * Unwraps the specified Boolean object into a boolean value.
	 */
	public static final boolean unwrap(Boolean b)
	{
		return b.booleanValue();
	}

	////////////////////////////
	///// hashCode methods /////
	////////////////////////////

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(Object value)
	{
		return (value == null) ? 0 : value.hashCode();
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(int value)
	{
		return value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(long value)
	{
		return (int) value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(byte value)
	{
		return value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(short value)
	{
		return value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(char value)
	{
		return value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(double value)
	{
		return (int) value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(float value)
	{
		return (int) value;
	}

	/**
	 * Returns the hashcode of the specified value.
	 */
	public static final int hashCode(boolean value)
	{
		return value ? 1 : 0;
	}
}
