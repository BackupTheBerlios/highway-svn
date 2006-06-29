/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import org.highway.bean.Decimal;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.type.ImmutableType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Class used in generated Hibernate mapping files (*.hbm.xml) to represent attributes declared as Decimal in ValueObject interfaces
 * @since 1.1
 * @author attias
 * @see org.highway.bean.Decimal
 */
public class DecimalHibernateType extends ImmutableType
{
	/**
	 * Returns a Decimal Object from a ResultSet
	 * @param rs a ResultSet
	 * @param name the name of the column containing a decimal value
	 * @return a Decimal object corresponding to the value of the column given bu the name param
	 */
	public Object get(ResultSet rs, String name)
		throws HibernateException, SQLException
	{
		String value = rs.getString(name);

		if (value == null)
		{
			return null;
		}

		return new Decimal(value);
	}

	/**
	 * Add a decimal value to a PreparedStatement
	 * @param st a PreparedStatement
	 * @param value a decimal value
	 * @param index the index dedicated to the decimal value in the PreparedStatement
	 */
	public void set(PreparedStatement st, Object value, int index)
		throws HibernateException, SQLException
	{
		st.setString(index, value.toString());

		//		Decimal dec = (Decimal) value;
		//		st.setObject(index, dec.unscaledValue(), sqlType(), dec.scale());
	}

	/**
	 * Returns the SQL type corresponding to a decimal
	 * @return a SQL type
	 */
	public int sqlType()
	{
		return Types.DECIMAL;
	}

	/**
	 * Returns the Java class used to represent a decimal value
	 * @return a Java Class Object
	 */
	public Class getReturnedClass()
	{
		return Decimal.class;
	}

	/**
	 * Compare two instances of the class mapped by this type for persistence "equality", ie. Equality of persistent state.
	 * @see net.sf.hibernate.type.Type#equals(java.lang.Object, java.lang.Object)
	 */
	public boolean equals(Object x, Object y) throws HibernateException
	{
		if (x == null)
		{
			return y == null;
		}

		return x.equals(y);
	}

	/**
	 * Returns the abbreviated name of the Java class representing a decimal value
	 */
	public String getName()
	{
		return Decimal.class.getName();
	}

	/**
	 * Returns a String representing a given Decimal Object
	 * @param value a Decimal object
	 * @return a String
	 */
	public String toString(Object value) throws HibernateException
	{
		return value.toString();
	}

	/**
	 * Returns a Decimal object corresponding to a String
	 * @param a String
	 * @return a Decimal object
	 */
	public Object fromStringValue(String xml) throws HibernateException
	{
		return new Decimal(xml);
	}
}
