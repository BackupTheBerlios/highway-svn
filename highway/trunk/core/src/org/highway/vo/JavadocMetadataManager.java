/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.highway.helper.ClassHelper;
import org.highway.helper.ReflectHelper;
import org.highway.javadoc.JavadocHome;

/**
 * <p>Default implementation of MetadataManager based on the default
 * JavadocCache of JavadocHome.
 * This MetadataManager extracts metadata from javadoc tags
 * found in JavaBean classes.</p>
 *
 * <p>This implementation searches for metadata in the JavaBean class,
 * then its definition interface if any, then its superclass, then
 * the superclass definition interface and so on.
 * It stops its search when a value is found.</p>
 *
 * <p>Example: if the <tt>AdressDef</tt> definition interface defines
 * the following property:</p>
 * <pre>
 * /**
 *  * @myproject.myclassmetaproperty mymetavalue
 *  %/
 * public interface AddressDef extends ValueObject
 * {
 *     /**
 *      * @highway.vo.property.min 5
 *      * @highway.vo.property.max 15
 *      * @highway.vo.property.pattern [a-zA-Z]*
 *      * @highway.vo.property.mandatory true
 *      %/
 *     String getCityName()
 * }
 * </pre>
 *
 * <p>Calling: <code>getClassMetaValue(Adress.class, "myproject.myclassmetaproperty")</code>
 * returns: <code>mymetavalue</code></p>
 *
 * <p>Calling: <code>getPropertyMetaValue(Adress.class, "cityName", "highway.vo.property.min")</code>
 * returns: <code>5</code></p>
 *
 * @since 1.2
 * @author David Attias
 * @author Christian de Bevotte
 * @see org.highway.javadoc.JavadocCache
 * @see org.highway.javadoc.JavadocHome
 */
public class JavadocMetadataManager implements MetadataManager
{
	/*
	 * Implements or overrides an already defined method.
	 */
	public String getClassMetaValue(Class beanClass, String metaName)
	{
		// first search the class
		String metaValue = JavadocHome.getClassTag(beanClass, metaName);

		// if not found, search the definition class
		if (metaValue == null)
		{
			Class defClass = getDefinitionClass(beanClass);

			if (defClass != null)
			{
				metaValue = JavadocHome.getClassTag(defClass, metaName);
			}
		}

		// if not found, search the superclass
		if ((metaValue == null) && (beanClass != Object.class))
		{
			metaValue = getClassMetaValue(beanClass.getSuperclass(), metaName);
		}

		return metaValue;
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public String getPropertyMetaValue(
		Class beanClass, String propertyName, String metaName)
	{
		String metaValue = null;
		
		try
		{
			// first search the class tree
			PropertyDescriptor propertyDescriptor = 
				new PropertyDescriptor(propertyName, beanClass);
			Method readMethod = propertyDescriptor.getReadMethod();
			metaValue = JavadocHome.getMethodTag(readMethod, metaName);

			// if not found, search the definition class
			if (metaValue == null)
			{
				Class defClass = 
					getDefinitionClass(readMethod.getDeclaringClass());
	
				if (defClass != null)
				{
					readMethod = ReflectHelper.getDeclaredGetter(
						defClass, propertyName);
	
					if (readMethod != null)
					{
						metaValue = 
							JavadocHome.getMethodTag(readMethod, metaName);
					}
					else
					{
						metaValue =
							JavadocHome.getFieldTag(
								defClass,
								getConstantNameFromPropertyName(propertyName),
								metaName);
					}
				}
			}
		}
		catch (IntrospectionException exc)
		{
			// TODO should work with definition interface ?
		}

		return metaValue;
	}

	private Class getDefinitionClass(Class beanClass)
	{
		String name = beanClass.getName();
		
		// if the given bean class is abstract and is called xxxBase, 
		// it was probably generated from a xxxDef definition class 
		// with the "highway.vo.base.only" annotation
		boolean base = Modifier.isAbstract(beanClass.getModifiers()) &&
			name.endsWith("Base");
		
		try
		{			
			return ClassHelper.loadClass(
				(base ? name.substring(0, name.length() - 4) : name) 
				+ "Def"
			);
		}
		catch (ClassNotFoundException exc)
		{
			if (base)
			{
				try
				{
					return ClassHelper.loadClass(beanClass.getName() + "Def");
				}
				catch (ClassNotFoundException e)
				{
					return null;
				}
			}
			
			return null;
		}
	}

	private static String getConstantNameFromPropertyName(String propertyName)
	{
		if (null == propertyName)
		{
			return null;
		}

		StringBuffer buffer = new StringBuffer(propertyName.length() + 5); // for 5 unerscore characters

		for (int i = 0; i < propertyName.length(); i++)
		{
			char c = propertyName.charAt(i);

			if (
				(i > 0) && Character.isUpperCase(c)
					&& (Character.isLowerCase(propertyName.charAt(i - 1))))
			{
				buffer.append('_');
			}

			buffer.append(Character.toUpperCase(c));
		}

		return buffer.toString();
	}
}
