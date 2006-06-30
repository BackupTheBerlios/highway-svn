/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.debug;

import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;
import org.highway.helper.FieldHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class is used to dump internal data of objects into a StringBuffer.
 * Objects can be dump if of type Collection, Map or Dumpable.
 * The object to dump is called the root object.<br>
 * <br>
 * The dump is split into two parts, the header and the body.
 * The header represents the reference to the object and contains the
 * object class name and a generated unique number.
 * The header of an ArrayList object looks like this:<br>
 * <br>
 * <code>java.util.ArrayList@1</code><br>
 * <br>
 * The body depends on the class of the object.
 * The body contains a String representation of the object internal data.
 * Dumpable data values are converted into headers and added to the
 * list of objects to dump.
 * Non dumpable data values are converted into String objects with their
 * regular <code>toString()</code> methods.<br>
 * <br>
 * The body of a Dumpable object containing three non static fields
 * (name, age and friends) looks like this:<br>
 * <br>
 * <code>[name=david, age=35, friends=java.util.ArrayList@51]</code><br>
 * <br>
 * The body of a Collection containing an Integer object, a String object
 * and an Person object looks like this:<br>
 * <br>
 * <code>[35, Hello, Person@27]</code><br>
 * <br>
 * The body of a Map containing an Integer object mapped to a String key,
 * and an Person object mapped to a String key looks like this:<br>
 * <br>
 * <code>{age=35, person=Person@11}</code><br>
 * <br>
 * All the dumpable objects of the graph of the root object are dumped
 * one at the time. The complete dump of an object looks like this:<br>
 * <br>
 * <code>HashMap@0 = {paul=Employee@1, jean=Employee@2}<br>
 * Employe@1 = [firstName=Paul, lastName=Martin, company=Company@3]<br>
 * Employe@2 = [firstName=Jean, lastName=Dupont, company=Company@3]<br>
 * Company@3 = [name=MyCompany, revenu=1534654, ...]</code><br>
 * <br>
 * 
 * @see org.highway.debug.Dumpable
 */
public class ObjectDumper
{
	/**
	 * Field root
	 */
	private Object root;

	/**
	 * Field buffer
	 */
	private StringBuffer buffer;

	/**
	 * Field references
	 */
	private List references;

	/**
	 * Field useQualifiedClassNames
	 */
	private boolean useQualifiedClassNames = false;

	/**
	 * Constructs an ObjectDumper to dump the specified object in the
	 * specified buffer. The specified object must be of a type that can
	 * be dump.
	 *
	 * @param buffer the buffer in which the object is dumped
	 * @param object the object to dump
	 * @param useQualifiedClassNames indicate if log messages should
	 * use fully qualified class names
	 * @throws IllegalArgumentException if the object is of a type that
	 * can not be dump
	 */
	public ObjectDumper(
		StringBuffer buffer, Object object, boolean useQualifiedClassNames)
	{
		if (object == null)
		{
			throw new IllegalArgumentException("object parameter is null");
		}

		if (! isDumpable(object))
		{
			throw new IllegalArgumentException(
				"object parameter is not dumpable");
		}

		this.root = object;
		this.buffer = buffer;
		this.useQualifiedClassNames = useQualifiedClassNames;
	}

	/**
	 * Dumps the header of the root object in the buffer.
	 */
	public void dumpHeader()
	{
		dumpHeader(root, 0);
	}

	/**
	 * Dumps the body of the root object and all the objects of its graph in the buffer.
	 */
	public void dumpBody()
	{
		int index = 0;
		Object object = this.root;

		while (true)
		{
			dumpHeader(object, index);
			buffer.append(" = ");
			dumpBody(object);
			object = getReference(++index);

			if (object == null)
			{
				return;
			}

			buffer.append('\n');
		}
	}

	/**
	 * Checks if the specified object is of a type this class can dump.
	 *
	 * @param object the object to check
	 * @return true if the object can be dump
	 */
	public static boolean isDumpable(Object object)
	{
		return object instanceof Dumpable || object instanceof Collection
		|| object instanceof Map;
	}

	/**
	 * Method dumpBody
	 * @param object Object
	 */
	private void dumpBody(Object object)
	{
		if (object instanceof Dumpable)
		{
			dumpDumpable((Dumpable) object);
		}
		else if (object instanceof Collection)
		{
			dumpCollection((Collection) object);
		}
		else if (object instanceof Map)
		{
			dumpMap((Map) object);
		}
	}

	/**
	 * Method dumpMap
	 * @param map Map
	 */
	private void dumpMap(Map map)
	{
		buffer.append('{');

		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();)
		{
			Map.Entry entry = (Map.Entry) iter.next();
			dumpObject(entry.getKey());
			buffer.append('=');
			dumpObject(entry.getValue());

			if (iter.hasNext())
			{
				buffer.append(", ");
			}
		}

		buffer.append('}');
	}

	/**
	 * Method dumpCollection
	 * @param collection Collection
	 */
	private void dumpCollection(Collection collection)
	{
		buffer.append('[');

		for (Iterator iter = collection.iterator(); iter.hasNext();)
		{
			dumpObject(iter.next());

			if (iter.hasNext())
			{
				buffer.append(", ");
			}
		}

		buffer.append(']');
	}

	/**
	 * Method dumpDumpable
	 * @param dumpable object to dump
	 */
	private void dumpDumpable(Dumpable dumpable)
	{
		buffer.append('[');

		Iterator fields =
			FieldHelper.getNonStaticFields(dumpable.getClass(), Object.class)
					   .iterator();

		while (fields.hasNext())
		{
			Field field = (Field) fields.next();

			if (! Modifier.isTransient(field.getModifiers()))
			{
				dumpField(field, dumpable);

				if (fields.hasNext())
				{
					buffer.append(", ");
				}
			}
		}

		buffer.append(']');
	}

	/**
	 * Method dumpHeader
	 * @param object Object
	 * @param index int
	 */
	private void dumpHeader(Object object, int index)
	{
		ClassHelper.dumpClassName(
			buffer, object.getClass(), useQualifiedClassNames);
		buffer.append('@').append(index);
	}

	/**
	 * @param field
	 * @param object
	 */
	private void dumpField(Field field, Object object)
	{
		try
		{
			buffer.append(field.getName()).append('=');
			field.setAccessible(true);

			Object fieldValue = field.get(object);
			dumpObject(fieldValue);
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException(
				"Failed to dump field "
				+ FieldHelper.getClassAndFieldName(field, true), exc);
		}
	}

	/**
	 * Method dumpObject
	 * @param value Object
	 */
	private void dumpObject(Object value)
	{
		if (isDumpable(value))
		{
			int index = getReferenceIndex(value);

			if (index < 0)
			{
				index = addReference(value);
			}

			dumpHeader(value, index);
		}
		else
		{
			buffer.append(value);
		}
	}

	/**
	 * Method getReference
	 * @param index int
	 * @return Object
	 */
	private Object getReference(int index)
	{
		if (references == null)
		{
			return null;
		}

		if (index >= references.size())
		{
			return null;
		}

		return references.get(index);
	}

	/**
	 * Method addReference
	 * @param object Object
	 * @return int
	 */
	private int addReference(Object object)
	{
		if (references == null)
		{
			references = new ArrayList();
			references.add(root);
		}

		references.add(object);

		return references.size() - 1;
	}

	/**
	 * @param object the object for wich we need an index
	 * @return the index or -1 if not found
	 */
	private int getReferenceIndex(Object object)
	{
		if (object == root)
		{
			return 0;
		}

		if (references == null)
		{
			return -1;
		}

		// do not use indexOf or any equals based method
		// we must find the object index using ==
		// since we do an exact memory graph object dump
		for (int i = 0; i < references.size(); i++)
		{
			if (object == references.get(i))
			{
				return i;
			}
		}

		// not found, i return -1
		return -1;
	}
}
