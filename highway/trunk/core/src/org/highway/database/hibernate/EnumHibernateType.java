/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import org.highway.exception.BugException;
import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;
import org.highway.helper.StringHelper;
import org.highway.vo.Enum;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.type.ImmutableType;
import net.sf.hibernate.type.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Base class for the persistence of enumerate types
 * </p>
 * <p>
 * To store an attribute whose type extends the Enum class, an additional class
 * extending the current class must be created. No attribute and no method need
 * to be defined in this additional class.
 * </p>
 * <p>
 * These child classes are used in the generated Hibernate mapping files
 * (*.hbm.xml) to represent attributes declared as subtypes of Enum in
 * ValueObject interfaces.
 * </p>
 * 
 * @since 1.1
 * @author David Attias
 * @see org.highway.vo.Enum
 */
public abstract class EnumHibernateType extends ImmutableType
{
	/**
	 * The suffix used by enum hibernate types.
	 * 
	 * @since 1.4.2
	 */
	public static final String ENUM_HIBERNATE_TYPE_CLASS_SUFFIX = "HibernateType";

	/**
	 * 
	 */
	private Class enumClass;

	/**
	 * 
	 */
	private ImmutableType codeType;

	/**
	 * 
	 */
	protected EnumHibernateType()
	{
	}

	/**
	 * <p>
	 * Returns an Enum Object from a ResultSet
	 * </p>
	 * <p>
	 * The returned object is an instance of the concrete class extending the
	 * Enum abstract class corresponding to the concrete class of the current
	 * instance.
	 * </p>
	 * 
	 * @param rs a ResultSet
	 * @param name the name of the column containing an enumerate value
	 * @return an Enum object corresponding to the value of the column given but
	 * the name param
	 */
	public Object get(ResultSet rs, String name) throws HibernateException,
			SQLException
	{
		Class codeClass = Enum.getEnumCodeClass(getReturnedClass());

		if (codeClass.equals(Short.class))
		{
			short code = rs.getShort(name);

			return Enum.getEnum(getReturnedClass(), new Short(code));
		}
		else if (codeClass.equals(Character.class))
		{
			String code = rs.getString(name);

			return Enum.getEnum(getReturnedClass(), new Character(code
					.charAt(0)));
		}
		else if (codeClass.equals(String.class))
		{
			String code = rs.getString(name);

			return Enum.getEnum(getReturnedClass(), code);
		}
		else
		{
			Object code = rs.getObject(name);

			return Enum.getEnum(getReturnedClass(), code);
		}
	}

	/**
	 * Add an enumerate value to a PreparedStatement
	 * 
	 * @param st a PreparedStatement
	 * @param value an instance of an enumerate type
	 * @param index the index dedicated to the enumerate value in the
	 * PreparedStatement
	 */
	public void set(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException
	{
		Object code = ((Enum) value).getCode();
		st.setObject(index, code, sqlType());
	}

	/**
	 * Returns the SQL type corresponding to the enumerate type associated with
	 * the current instance
	 * 
	 * @return a SQL type
	 */
	public int sqlType()
	{
		return getEnumCodeType().sqlType();
	}

	/**
	 * Returns the Java class of the enumerate type associated with the current
	 * instance
	 * 
	 * @return a Java Class Object
	 * @throws TechnicalException if no enumerate type is associated with the
	 * current instance
	 */
	public Class getReturnedClass()
	{
		try
		{
			if (enumClass == null)
			{
				// the enum class name is this class name minus its suddix
				enumClass = ClassHelper.loadClass(StringHelper.removeSuffix(
						getClass().getName(), ENUM_HIBERNATE_TYPE_CLASS_SUFFIX));
			}

			return enumClass;
		}
		catch (ClassNotFoundException exc)
		{
			throw new TechnicalException(
					"Enum class MyEnum must have its Hibernate type class"
							+ " MyEnumHibType if you use it as persistent property",
					exc);
		}
	}

	/**
	 * Compare two instances of the class mapped by this type for persistence
	 * "equality", ie. Equality of persistent state.
	 * 
	 * @see net.sf.hibernate.type.Type#equals(java.lang.Object,
	 * java.lang.Object)
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
	 * Returns the abbreviated name of the enumerate type associated with the
	 * current instance
	 */
	public String getName()
	{
		return getReturnedClass().getName();
	}

	private ImmutableType getEnumCodeType()
	{
		if (codeType == null)
		{
			Type type = HibernateTypes.getType(Enum
					.getEnumCodeClass(getReturnedClass()));

			if (!(type instanceof ImmutableType))
			{
				throw new BugException("Invalid code enum type: "
						+ type.getName());
			}

			codeType = (ImmutableType) type;
		}

		return codeType;
	}

	/**
	 * Returns a String representing a given enumerate value
	 * 
	 * @param value an instance of a subtype of Enum
	 * @return a String
	 */
	public String toString(Object value) throws HibernateException
	{
		return value.toString();
	}

	/**
	 * Returns an enumerate value corresponding to a String
	 * 
	 * @param a String
	 * @return an instance of a subtype of Enum
	 */
	public Object fromStringValue(String xml) throws HibernateException
	{
		return Enum.getEnumFromString(getReturnedClass(), xml);
	}
}
