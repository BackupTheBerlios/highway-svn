package org.highway.database.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import net.sf.hibernate.UserType;

import org.highway.helper.StringHelper;

/**
 * Class used in generated Hibernate mapping files (*.hbm.xml) to represent
 * attributes declared as String in ValueObject you want to be trimmed
 * automatically.
 * 
 * Cf "<a href="http://www.hibernate.org/90.html">Automatically trimming String
 * properties</a>" from the Hibernate wiki.
 * 
 * @author Christian de Bevotte
 * @since 1.4.6
 */
public class TrimmedStringHibernateType implements UserType
{
	/**
	 * Default trim policy.
	 */
	private static String DEFAULT_POLICY = StringHelper.TRIM_SPACE_RIGHT;
	
	/**
	 * Trim policy.
	 */
	private String policy;

	/**
	 * Sets the default trim policy.
	 */
	public static void setDefaultPolicy(String policy)
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
	public void setPolicy(String policy)
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
}