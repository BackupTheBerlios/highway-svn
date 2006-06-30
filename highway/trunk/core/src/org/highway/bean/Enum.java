/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import org.highway.collection.MapOfMap;
import org.highway.collection.MapOfMapImpl;
import org.highway.debug.DebugHome;
import org.highway.helper.ClassHelper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Base class for all static enumeration.
 * An Enum object has two basic properties: a code (internal view)
 * and a description (external view). If the description is omitted,
 * the code is used as external representation.</p>
 *
 * <p>The code can be of one of the three following types:
 * <ul><li>char</li>
 * <li>short</li>
 * <li>String</li></ul></p>
 *
 * <p>An Enum subclass must contain:
 * <ul>
 * <li>a private constructor whose arguments depends on the type of the code
 * and on the existence of a distinct description</li>
 * <li>static declarations of all the instances of the enumerate type</li>
 * <li>one or more <tt>getAll</tt> method returning a <tt>List</tt></li></ul></p>
 *
 * <p>A normal Enum subclass should look like:</p>
 * <pre>
 * public class Color extends Enum
 * {
 *     public static Color red = new Color("red");
 *     public static Color blue = new Color("blue");
 *     public static Color green = new Color("green");
 *
 *     private Color(String code)
 *     {
 *         super(code);
 *     }
 *
 *    public static List getAll()
 *    {
 *        return Enum.getAll(Color.class);
 *    }
 * }
 * </pre>
 *
 * <p>If an Enum subclass is to be used as a property of a persistent JavaBean
 * and if the persistence framework used is Hibernate, an additional class must
 * be created, whose name is obtained by adding the suffix "HibernateType" to
 * the name of the enumerate type. This class must extend
 * <tt>org.highway.database.hibernate.EnumHibernateType</tt>.</p>
 *
 * 
 * 
 * @see org.highway.database.hibernate.EnumHibernateType
 */
public abstract class Enum implements Serializable, Comparable
{
	/**
	 * Enum code.
	 */
	private Object code;

	/**
	 * Enum description.
	 *
	 */
	private String description;

	/**
	 * Index of this enum value in the declaration sequence.
	 */
	private final int index;

	/**
	 * Used to count and order the enum instances.
	 * Used to remember enum declaration order.
	 * Used in the Comparable implem.
	 */
	private static int nextIndex = 0;

	/**
	 * Constructs an Enum object with the specified code.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	protected Enum(char code)
	{
		this(code, null);
	}

	/**
	 * Constructs an Enum object with the specified code and description.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	protected Enum(char code, String description)
	{
		this(new Character(code), description);
	}

	/**
	 * Constructs an Enum object with the specified code.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	protected Enum(short code)
	{
		this(code, null);
	}

	/**
	 * Constructs an Enum object with the specified code and description.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	protected Enum(short code, String description)
	{
		this(new Short(code), description);
	}

	/**
	 * Constructs an Enum object with the specified code.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	protected Enum(String code)
	{
		this(code, null);
	}

	/**
	 * Constructs an Enum object with the specified code and description.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	protected Enum(String code, String description)
	{
		this((Object)code, description);
	}

	/**
	 * Initialize this object with the specified code and description.
	 *
	 * @throws IllegalArgumentException if the code is null
	 * @throws IllegalArgumentException if the code is already used by an enum
	 * of the same type
	 * @throws IllegalArgumentException if the code type is not consistent
	 * with enums of the same type
	 */
	private Enum(Object code, String description)
	{
		if (code == null)
		{
			throw new IllegalArgumentException("enum code is null");
		}

		this.code = code;
		this.description = description;
		this.index = nextIndex++;

		register(this);
	}

	/**
	 * Returns the code of the enum object.
	 */
	public final Object getCode()
	{
		return code;
	}

	/**
	 * Returns the description of the enum object.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Compares this enum object with the specified object.
	 * Returns true if the specified object is of type EnumMethod equals
	 *
	 * @param obj Object
	 * @return boolean
	 */
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (obj == this)
		{
			return true;
		}

		if (! getClass().equals(obj.getClass()))
		{
			return false;
		}

		return code.equals(((Enum) obj).getCode());
	}

	/**
	 * Returns the construction order. The first Enum object returns 0,
	 * the second 1 and so on. This order is used to compare and sort Enum
	 * objects of the same type.
	 *
	 * 
	 */
	public final int hashCode()
	{
		return this.index;
	}

	/**
	 * Compares this Enum with the specified Enum.
	 *
	 * @param  o Enum to which this Enum is to be compared.
	 * @return a negative number, zero, or a positive number as this
	 * Enum is declared before, is equal to, is declared after the
	 * specified Enum.
	 * @throws ClassCastException if the argument is not an Enum.
	 * 2
	 */
	public int compareTo(Object o)
	{
		// ClassCastException if o is not an enum
		// OK with the Comparable.compareTo javadoc
		return this.index - ((Enum) o).index;
	}

	/**
	 * Returns the enum description if any or the code converted to a String.
	 */
	public String toString()
	{
		return (description == null) ? code.toString() : description;
	}

	// Serialization management methods
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(code);
	}

	private void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException
	{
		code = in.readObject();
	}

	protected final Object readResolve()
	{
		return getEnum(getClass(), code);
	}

	////////////////////////////////////
	////// Enum Static Management //////
	////////////////////////////////////

	/**
	 * All enum objects are stored in this map.
	 */
	private static MapOfMap enums = new MapOfMapImpl();

	/**
	 * Registers the enum into the static internal map of all enum values.
	 * No need to synchronize since the static class loading and init is only
	 * done once even in heavy threading environment.
	 *
	 * @param myEnum enum to register
	 * @throws IllegalArgumentException if an enum of the same type is
	 * registered with the same code
	 * @throws IllegalArgumentException if the enum code type is not consistent
	 * with enums of the same type
	 */
	private static void register(Enum myEnum)
	{
		// get the code type of the first enum of this type registered
		Class alreadyRegisteredEnumCodeClass =
			getEnumCodeClass(myEnum.getClass());

		if (alreadyRegisteredEnumCodeClass != null)
		{
			Class myEnumCodeClass = myEnum.getCode().getClass();

			// if the to be registered enum code type is different,
			// the code type is not consistent, there is a problem
			if (! myEnumCodeClass.equals(alreadyRegisteredEnumCodeClass))
			{
				throw new IllegalArgumentException(
					"non consistent enum code type = "
					+ myEnumCodeClass.getName() + " for enum class = "
					+ myEnum.getClass().getName());
			}
		}

		Object oldValue =
			enums.put(myEnum.getClass(), myEnum.getCode(), myEnum);

		if (oldValue != null)
		{
			throw new IllegalArgumentException(
				"already used enum code = " + myEnum.getCode()
				+ " for class = " + myEnum.getClass().getName());
		}
	}

	/**
	 * Returns the Enum identified by the specified class and code.
	 *
	 * @param enumClass class of Enum to find
	 * @param code code of Enum to find
	 * @return Enum identified by class and code
	 */
	public static Enum getEnum(Class enumClass, Object code)
	{
		ClassHelper.initializeCheck(enumClass);

		Enum myEnum = (Enum) enums.get(enumClass, code);

		if (myEnum == null)
		{
			DebugHome.getDebugLog().warn(
				"Unknown enum, class = " + enumClass.getName() + ", code = "
				+ code);
		}

		return myEnum;
	}

	/**
	 * Returns the enum identified by the specified class and
	 * stringified code.
	 *
	 * @param enumClass class of Enum to find
	 * @param code code of Enum to find
	 * @return Enum identified by class and code
	 */
	public static Enum getEnumFromString(Class enumClass, String code)
	{
		ClassHelper.initializeCheck(enumClass);

		Iterator iter = enums.values(enumClass).iterator();

		while (iter.hasNext())
		{
			Enum myEnum = (Enum) iter.next();

			if (code.equals(myEnum.getCode().toString()))
			{
				return myEnum;
			}
		}

		DebugHome.getDebugLog().warn(
			"Unknown enum, class = " + enumClass.getName() + ", code = " + code);

		return null;
	}

	/**
	 * Returns the class of the code of the specified Enum class.
	 * Returns null if no instances of the specified class has been
	 * registered yet.
	 *
	 * @param enumClass the Enum class whose code class is to be returned.
	 * @return the class of the Enum code.
	 */
	public static Class getEnumCodeClass(Class enumClass)
	{
		// i do not need to call initializeCheck
		// because the getAll method does it
		Iterator iter = getAll(enumClass).iterator();

		if (iter.hasNext())
		{
			return ((Enum) iter.next()).getCode().getClass();
		}

		return null;
	}

	/**
	 * Returns all the Enum objects of the specified class.<br>
	 *
	 * The returned list can be changed without impact on the Enum class
	 * internal Enum registry. The returned list is sorted to match the
	 * declaration order of Enum values of the specified Enum subclass.<br>
	 *
	 * Enum subclasses should have their own <tt>getAll</tt> methods with
	 * different kind of sorting if necessary.<br>
	 *
	 * <pre>
	 * public static List getAll()
	 * {
	 *     return getAll(MyEnum.class);
	 * }
	 *
	 * public static List getAllReversed()
	 * {
	 *     List list = getAll();
	 *     Collections.reverse(list);
	 *     return list;
	 * }
	 * </pre>
	 *
	 * @param enumClass the subclass of witch values are returned
	 * @return the list of Enum values of the specified class
	 */
	public static List getAll(Class enumClass)
	{
		ClassHelper.initializeCheck(enumClass);

		List list = enums.listValues(enumClass);
		Collections.sort(list);

		return list;
	}
}
