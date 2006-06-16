package org.highway.vo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.highway.helper.ClassHelper;
import org.highway.helper.ReflectHelper;

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
 */
class AnnotationMetadataManager 
{
	public <A extends Annotation> A getClassMetaValue(Class beanClass, Class<A> annotationType)
	{
		// first search the class
		A metaValue =(A)beanClass.getAnnotation(annotationType);
		
		// if not found, search the definition class
		if (metaValue == null)
		{
			Class defClass = getDefinitionClass(beanClass);

			if (defClass != null)
			{
				metaValue = (A)defClass.getAnnotation(annotationType);
			}
		}

		// if not found, search the superclass
		if ((metaValue == null) && (beanClass != Object.class))
		{
			metaValue = getClassMetaValue(beanClass.getSuperclass(), annotationType);
		}

		return metaValue;
	}

	public <T extends Annotation> T getPropertyMetaValue(
		Class beanClass, String propertyName,  Class<T> annotationType)
	{
		T metaValue = null;

		// first search in the class for the property getter
		Method readMethod =
			ReflectHelper.getDeclaredGetter(beanClass, propertyName);

		if (readMethod != null)
		{
			metaValue =  readMethod.getAnnotation(annotationType);
		}

		// if not found, search in the definition class tree
		if (metaValue == null)
		{
			Class defClass = getDefinitionClass(beanClass);

			if (defClass != null)
			{
				readMethod =
					ReflectHelper.getDeclaredGetter(defClass, propertyName);

				if (readMethod != null)
				{
					metaValue =  readMethod.getAnnotation(annotationType);
				}
				else
				{
					try {
						metaValue = defClass.getDeclaredField(getConstantNameFromPropertyName(propertyName)).getAnnotation(annotationType);
					} catch (Exception e) {
						// search in the superclass
						metaValue=null;
					}
//					metaValue =
//						JavadocHome.getFieldTag(
//							defClass,
//							getConstantNameFromPropertyName(propertyName),
//							metaName);
				}
			}
		}

		// if not found, search the superclass
		if ((metaValue == null) && (beanClass != Object.class))
		{
			metaValue =
				getPropertyMetaValue(
					beanClass.getSuperclass(), propertyName, annotationType);
		}

		return  metaValue;
	}

	private Class getDefinitionClass(Class beanClass)
	{
		try
		{
			return ClassHelper.loadClass(beanClass.getName() + "Def");
		}
		catch (ClassNotFoundException exc)
		{
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
