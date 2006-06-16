package org.highway.database.hibernate;

import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

public class String2ClobHibernateType implements UserType
{
	public int[] sqlTypes()
	{
		return new int[] {Types.CLOB};
	}

	public Class returnedClass()
	{
		return String.class;
	}

	public boolean equals(Object x, Object y)
	{
		return (x == y) || (x != null && y != null && x.equals(y));
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException
	{
		Clob clob = rs.getClob(names[0]);
		if (clob == null) return null;
		return clob.getSubString(1, (int) clob.length());
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException
	{
//		st.setClob(index, Hibernate.createClob((String) value));
		st.setString(index, (String) value);
	}

	public Object deepCopy(Object value)
	{
		if (value == null) return null;
		return new String((String) value);
	}

	public boolean isMutable()
	{
		return false;
	}
}