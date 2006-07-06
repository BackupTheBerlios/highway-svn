package org.highway.database.hibernate;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;



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

	public int hashCode(Object arg0) throws HibernateException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Serializable disassemble(Object arg0) throws HibernateException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object assemble(Serializable arg0, Object arg1) throws HibernateException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException
	{
		// TODO Auto-generated method stub
		return null;
	}
}