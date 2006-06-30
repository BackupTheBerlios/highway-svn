/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.bean.ValueObject;
import org.highway.debug.DebugHome;
import org.highway.exception.Assert;
import org.highway.exception.TechnicalException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 */
public abstract class ReflectHelper
{
	private static int BUFFER_INITIAL_SIZE = 200;

	/**
	 * Ensemble des PropertyDescriptor des objets sur lesquels sont invoquées
	 * les méthodes get/setProperty. Structure = map[Class, map[String,
	 * PropertyDescriptor]]
	 */
	private static Map s_descriptorMap;

	/**
	 * Ensemble des classes/méthodes
	 * @param o1 Object
	 * @param o2 Object
	 * @return boolean
	 */

	//	private static Map s_methodMap = new HashMap();
	public static boolean equals(Object o1, Object o2)
	{
		if ((o1 == null) || (o2 == null))
		{
			return (o1 == o2);
		}

		if (o1.getClass() != o2.getClass())
		{
			return false;
		}

		if (
			(o1 instanceof String) || (o1 instanceof Number)
				|| (o1 instanceof Boolean) || (o1 instanceof Character)
				|| (o1 instanceof Date))
		{
			return o1.equals(o2);
		}

		try
		{
			Iterator fields =
				FieldHelper.getNonStaticFields(o1.getClass(), Object.class)
						   .iterator();

			while (fields.hasNext())
			{
				Field field = (Field) fields.next();

				if (field.getName().equals("_objectClass"))
				{
					continue;
				}

				field.setAccessible(true);

				Object value1 = field.get(o1);

				if (! equals(value1, field.get(o2)))
				{
					return false;
				}
			}

			return true;
		}
		catch (IllegalAccessException iae)
		{
			throw new RuntimeException(
				"erreur lors de l'accès à un champ lors de la comparaison des objets"
				+ iae.getMessage());
		}
	}

	/**
	 * Retourne la valeur de la propriété propertyName de l'objet object
	 * @change mettre ou non les assert ?
	 * @param object l'objet à interroger
	 * @param propertyName le nom de la propriété
	 * @return la valeur de la propriété
	 */
	public static final Object getProperty(Object object, String propertyName)
	{
		//assert(object != null);
		//assert(propertyName != null);
		try
		{
			Method method =
				getPropertyDescriptor(object.getClass(), propertyName)
					.getReadMethod();

			return method.invoke(object, null);
		}
		catch (InvocationTargetException exc)
		{
			throw new TechnicalException(
				"Impossible de lire la propriété " + propertyName + " de "
				+ object.getClass(), exc);
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException(
				"Impossible de lire la propriété " + propertyName + " de "
				+ object.getClass(), exc);
		}
	}

	/**
	 * Retourne le type de la propriété propertyName ddans la classe aClass
	 * @param aClass la classe a interroger
	 * @param propertyName le nom de la propriété
	 * @return le type de la propriété
	 */
	public static final Class getPropertyType(
		Class aClass, String propertyName)
	{
		return getPropertyDescriptor(aClass, propertyName).getPropertyType();
	}

	/**
	 * Positionne la valeur de la propriété propertyName de l'objet object
	 * @param object l'objet à interroger
	 * @param propertyName le nom de la propriété
	 * @param value Object
	 */
	public static final void setProperty(
		Object object, String propertyName, Object value)
	{
		try
		{
			Method method =
				getPropertyDescriptor(object.getClass(), propertyName)
					.getWriteMethod();

			if (method != null)
			{
				method.invoke(object, new Object[] { value });
			}
			else
			{
				throw new TechnicalException(
					"Pas de setter pour la propriété " + propertyName + " de "
					+ object.getClass());
			}
		}

		//IllegalArgumentException
		//InvocationTargetException
		//IllegalAccessException
		//TechnicalException
		catch (Exception exc)
		{
			throw new TechnicalException(
				"Impossible de mettre à jour la propriété " + propertyName
				+ " de " + object.getClass() + " avec " + value, exc);
		}
	}

	/**
	 * Retourne le PropertyDescriptor de la propriété passée en paramètre. Les
	 * PropertyDescriptors sont cachés dans une map statique.
	 * @param objectClass Class
	 * @param propertyName String
	 * @return PropertyDescriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor(
		Class objectClass, String propertyName)
	{
		Map byPropertyMap = null;
		PropertyDescriptor descriptor = null;

		if (s_descriptorMap == null)
		{
			s_descriptorMap = new HashMap();
		}
		else
		{
			byPropertyMap = (Map) s_descriptorMap.get(objectClass);
		}

		if (byPropertyMap == null)
		{
			byPropertyMap = new HashMap();
			s_descriptorMap.put(objectClass, byPropertyMap);
		}
		else
		{
			descriptor = (PropertyDescriptor) byPropertyMap.get(propertyName);
		}

		if (descriptor == null)
		{
			try
			{
				descriptor = new PropertyDescriptor(propertyName, objectClass);
			}
			catch (IntrospectionException exc)
			{
				try
				{
					try
					{
						descriptor =
							new PropertyDescriptor(
								propertyName, objectClass,
								"is"
								+ StringHelper.capitalizeFirstCharacter(
									propertyName), null);
					}
					catch (Exception getterExc)
					{
						descriptor =
							new PropertyDescriptor(
								propertyName, objectClass,
								"get"
								+ StringHelper.capitalizeFirstCharacter(
									propertyName), null);
					}
				}
				catch (IntrospectionException e)
				{
					throw new TechnicalException(
						"Impossible de trouver le getter de " + propertyName
						+ " dans " + objectClass, e);
				}
			}

			byPropertyMap.put(propertyName, descriptor);
		}

		return descriptor;
	}

	private static Class[] ADD_PROPERTY_CHANGE_LISTENER_ARG_TYPES =
		{ PropertyChangeListener.class };

	/**
	 * Returns true if the specified listener has been removed from
	 * the specified object listener list.
	 *
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws TechnicalException
	 * @see Method#invoke(java.lang.Object, java.lang.Object[])
	 */
	public static boolean addPropertyChangeListener(
		Object bean, PropertyChangeListener listener)
	{
		return addRemovePropertyChangeListener(
			bean, "addPropertyChangeListener", listener);
	}

	/**
	 * Returns true if the specified listener has been removed from
	 * the specified object listener list.
	 *
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws TechnicalException
	 * @see Method#invoke(java.lang.Object, java.lang.Object[])
	 */
	public static boolean removePropertyChangeListener(
		Object bean, PropertyChangeListener listener)
	{
		return addRemovePropertyChangeListener(
			bean, "removePropertyChangeListener", listener);
	}

	/**
	 * Returns true if the specified listener has been added or removed from
	 * the specified object listener list. The specified method name indicates
	 * the add or remove method to use.
	 *
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws TechnicalException
	 * @see Method#invoke(java.lang.Object, java.lang.Object[])
	 */
	private static boolean addRemovePropertyChangeListener(
		Object bean, String methodName, PropertyChangeListener listener)
	{
		if (bean == null)
		{
			return false;
		}

		try
		{
			Method method =
				bean.getClass().getMethod(
					methodName, ADD_PROPERTY_CHANGE_LISTENER_ARG_TYPES);

			Object[] args = { listener };
			method.invoke(bean, args);

			return true;
		}
		catch (SecurityException exc)
		{
			throw exc;
		}
		catch (NoSuchMethodException exc)
		{
			return false;
		}
		catch (IllegalArgumentException exc)
		{
			throw exc;
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (InvocationTargetException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Returns the list of JavaBeans properties of the specified class.
	 * Returns an empty list if no property is found.
	 *
	 * @param objectClass the class to search
	 * @return a list of propertt names (String)
	 *
	 * @todo voir ci dessous
	 */
	public static List getPropertyList(Class objectClass)
	{
		try
		{
			Introspector.setBeanInfoSearchPath(new String[0]);

			BeanInfo info = Introspector.getBeanInfo(objectClass, Object.class);
			PropertyDescriptor[] descriptors = info.getPropertyDescriptors();

			// @TODO put these descriptors in the cache for future use
			List propertyList = new ArrayList(descriptors.length);

			for (int i = 0; i < descriptors.length; i++)
			{
				propertyList.add(i, descriptors[i].getName());
			}

			return propertyList;
		}
		catch (IntrospectionException exc)
		{
			throw new TechnicalException(
				"Failed to get class " + objectClass.getName()
				+ " property list", exc);
		}
	}

	/**
	 * @param defClass
	 * @param propertyName
	 * @return
	 */
	public static Method getDeclaredGetter(
		Class objectClass, String propertyName)
	{
		propertyName = StringHelper.capitalizeFirstCharacter(propertyName);

		try
		{
			return objectClass.getDeclaredMethod("get" + propertyName, null);
		}
		catch (NoSuchMethodException exc)
		{
			try
			{
				return objectClass.getDeclaredMethod("is" + propertyName, null);
			}
			catch (NoSuchMethodException exc2)
			{
				return null;
			}
		}
	}

	/**
	 * Renvoie l'état de l'objet au format : [className, attribut1=[],
	 * attribut2=...]
	 * @param object Object
	 * @return String
	 */
	public static String dump(Object object)
	{
		if (object == null)
		{
			return "null";
		}
		else if (object instanceof ValueObject)
		{
			return dumpValueObject(object, ValueObject.class);
		}
		else
		{
			return object.toString();
		}
	}

	/**
	 * Method dump
	 * @param buffer StringBuffer
	 * @param object Object
	 * @return StringBuffer
	 */
	public static StringBuffer dump(StringBuffer buffer, Object object)
	{
		if (object == null)
		{
			return buffer;
		}
		else if (object instanceof ValueObject)
		{
			return dumpValueObject(buffer, object, ValueObject.class);
		}
		else
		{
			return buffer.append(object.toString());
		}
	}

	/**
	 * Method dumpValueObject
	 * @param object Object
	 * @param upperClass Class
	 * @return String
	 */
	private static String dumpValueObject(Object object, Class upperClass)
	{
		return dumpValueObject(
			new StringBuffer(BUFFER_INITIAL_SIZE), object, upperClass).toString();
	}

	/**
	 * Method dumpValueObject
	 * @param buffer StringBuffer
	 * @param object Object
	 * @param upperClass Class
	 * @return StringBuffer
	 */
	private static StringBuffer dumpValueObject(
		StringBuffer buffer, Object object, Class upperClass)
	{
		buffer.append('[')
			  .append(ClassHelper.getClassName(object.getClass(), false))
			  .append(", ");

		try
		{
			Iterator fields =
				FieldHelper.getNonStaticFields(object.getClass(), upperClass)
						   .iterator();

			while (fields.hasNext())
			{
				Field field = (Field) fields.next();
				field.setAccessible(true);
				buffer.append(trimFieldPrefix(field.getName()));
				buffer.append('=');
				dump(buffer, field.get(object));

				if (fields.hasNext())
				{
					buffer.append(", ");
				}
			}
		}
		catch (IllegalAccessException exc)
		{
			DebugHome.getDebugLog().warn(
				"Impossible de dumper un objet de type " + object.getClass(),
				exc);
		}

		return buffer.append(']');
	}

	/**
	 * Method trimFieldPrefix
	 * @param s String
	 * @return String
	 */
	public static String trimFieldPrefix(String s)
	{
		int index = s.indexOf('_');

		if (index < 0)
		{
			return s;
		}
		else
		{
			return s.substring(index + 1);
		}
	}

	/**
	 * Permet de chercher un champ dans une classe et toutes ses super classes
	 * récursivement.
	 * @param targetClass le nom du champ à chercher
	 * @param fieldName la classe dans laquelle chercher le champ.
	 * @return Field
	 * @throws NoSuchFieldException si le champ spécifié n'est pas trouvé dans
	 * la classe ou ses super classes.
	 */

	//	public static Field getField(Class aClass, String name)
	//		throws NoSuchFieldException
	//	{
	//		return getField(aClass, name, aClass.getName());
	//	}
	//
	//	private static Field getField(Class aClass, String attributeName, String
	// initialClassName)
	//		throws NoSuchFieldException
	//	{
	//		if (aClass == Object.class)
	//		{
	//			throw new NoSuchFieldException("Attribut " + attributeName + "
	// introuvable dans " + initialClassName);
	//		}
	//		try
	//		{
	//			return aClass.getDeclaredField(attributeName);
	//		}
	//		catch (NoSuchFieldException nsfe)
	//		{
	//			return getField(aClass.getSuperclass(), attributeName, initialClassName);
	//		}
	//	}

	/**
	 * Permet de chercher une méthode dans une classe et toutes ses super
	 * classes, connaissant son nom. Note: on fait une recherche par nom (sans
	 * spécifier le type des paramètres) car c'est une facon plus souple de
	 * procéder. En effet, cela autorise d'utiliser dans les ValueObject des
	 * accesseurs ayant en paramètres des types différents de ceux des attributs
	 * concernés (typiquement des setXXX prenant en paramètres des Object...).
	 * @param Class la classe sur laquelle s'effectue la recherche
	 * @param name nom de la méthode à rechercher
	 * @return Method la méthode concernée
	 * @throws NoSuchMethodException si la méthode recherchée n'est pas trouvée
	 * dans la classe ou ses super classes
	 */

	//	public static Method getMethod(Class aClass, String name)
	//		throws NoSuchMethodException
	//	{
	//		Method result = null;
	//		if (aClass == Object.class)
	//		{
	//			throw new NoSuchMethodException("erreur : impossible de trouver la
	// methode " + name);
	//		}
	//
	//		Map methodMap = (Map) s_methodMap.get(aClass);
	//
	//		if (methodMap == null)
	//		{
	//			methodMap = new HashMap();
	//			s_methodMap.put(aClass, methodMap);
	//		}
	//
	//		result = (Method) methodMap.get(name);
	//
	//		if (result == null)
	//		{
	//			Method[] methods = aClass.getDeclaredMethods();
	//			for (int i = 0; i < methods.length; i++)
	//			{
	//				if (methods[i].getName().equals(name))
	//				{
	//					result = methods[i];
	//					break;
	//				}
	//			}
	//
	//			if (result == null)
	//			{
	//				result = getMethod(aClass.getSuperclass(), name);
	//			}
	//
	//			methodMap.put(name, result);
	//		}
	//
	//		return result;
	//	}
	public static Field getField(Class targetClass, String fieldName)
	{
		try
		{
			return targetClass.getDeclaredField(fieldName);
		}
		catch (SecurityException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (NoSuchFieldException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method get
	 * @param object Object
	 * @param fieldPath List
	 * @return Object
	 */
	public static Object get(Object object, List fieldPath)
	{
		Assert.checkNotNull(object);
		Assert.checkNotNull(fieldPath);
		Assert.check(! fieldPath.isEmpty());

		Object value = object;
		Iterator iterator = fieldPath.iterator();

		while (iterator.hasNext() && (value != null))
		{
			Field field = (Field) iterator.next();

			try
			{
				value = field.get(value);
			}
			catch (IllegalAccessException exc)
			{
				throw new TechnicalException(
					"Illegal access to field " + field.getName() + " of class "
					+ object, exc);
			}
		}

		return value;
	}

	/**
	 * Method set
	 * @param object Object
	 * @param fieldPath List
	 * @param value Object
	 */
	public static void set(Object object, List fieldPath, Object value)
	{
		Assert.checkNotNull(object);
		Assert.checkNotNull(fieldPath);
		Assert.check(! fieldPath.isEmpty());

		Iterator it = fieldPath.iterator();

		while (it.hasNext())
		{
			Field field = (Field) it.next();

			try
			{
				if (it.hasNext())
				{
					Object fieldValue = field.get(object);

					if (fieldValue == null)
					{
						fieldValue = ClassHelper.newInstance(field.getType());
						field.set(object, fieldValue);
					}

					object = fieldValue;
				}
				else
				{
					field.set(object, value);
				}
			}
			catch (IllegalAccessException exc)
			{
				throw new TechnicalException(
					"Illegal access to field " + field.getName() + " of class "
					+ object, exc);
			}
		}
	}
}
