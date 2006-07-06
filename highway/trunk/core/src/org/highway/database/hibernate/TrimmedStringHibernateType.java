package org.highway.database.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.highway.helper.StringHelper;
import org.highway.helper.StringHelper.TrimPolicy;

/**
 * Class used in generated Hibernate mapping files (*.hbm.xml) to represent
 * attributes declared as String in ValueObject you want to be trimmed
 * automatically.
 * 
 * Cf "<a href="http://www.hibernate.org/90.html">Automatically trimming String
 * properties</a>" from the Hibernate wiki.
 */
public class TrimmedStringHibernateType implements UserType
{
	/**
	 * Default trim policy.
	 */
	private static TrimPolicy DEFAULT_POLICY = TrimPolicy.SPACE_RIGHT;
	
	/**
	 * Trim policy.
	 */
	private TrimPolicy policy;

	/**
	 * Sets the default trim policy.
	 */
	public static void setDefaultPolicy(TrimPolicy policy)
	{
		DEFAULT_POLICY = policy;
	}
	
	/**
	 * Constructs a <tt>TrimmedStringHibernateType</tt> instance with the
	 * default trim policy.
	 * 
	 * @see #setDefaultPolicy(Object)
	 */
	public TrimmedStringHibernateType()
	{
		policy = DEFAULT_POLICY;
	}

	/**
	 * Sets the trim policy.
	 */
	public void setPolicy(TrimPolicy policy)
	{
		this.policy = policy;
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public int[] sqlTypes()
	{
		return new int[] { Types.CHAR };
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public Class returnedClass()
	{
		return String.class;
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public boolean equals(Object x, Object y)
	{
		return (x == y) || (x != null && y != null && x.equals(y));
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
		throws SQLException
	{
		return StringHelper.trim(policy, resultSet.getString(names[0]));
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public void nullSafeSet(PreparedStatement statement, Object value, int index)
		throws SQLException
	{
		statement.setString(index, (String) value);
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public Object deepCopy(Object value)
	{
		return value == null ? null : new String((String) value);
	}

	/*
	 * Implements or overrides an already defined method.
	 */
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