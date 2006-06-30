package org.highway.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.highway.helper.ClassHelper;
import org.highway.helper.ReflectHelper;

/**
 * <p>
 * Default implementation of MetadataManager based on the default JavadocCache
 * of JavadocHome. This MetadataManager extracts metadata from javadoc tags
 * found in JavaBean classes.
 * </p>
 * <p>
 * This implementation searches for metadata in the JavaBean class, then its
 * definition interface if any, then its superclass, then the superclass
 * definition interface and so on. It stops its search when a value is found.
 * </p>
 * <p>
 * Example: if the <tt>AdressDef</tt> definition interface defines the
 * following property:
 * </p>
 * 
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
 * <p>
 * Calling:
 * <code>getClassMetaValue(Adress.class, "myproject.myclassmetaproperty")</code>
 * returns: <code>mymetavalue</code>
 * </p>
 * <p>
 * Calling:
 * <code>getPropertyMetaValue(Adress.class, "cityName", "highway.vo.property.min")</code>
 * returns: <code>5</code>
 * </p>
 */
public class StandardBeanMetadataManager implements BeanMetadataManager
{
	public <A extends Annotation> A getBeanAnnotation(Class beanClass,
			Class<A> annotationType)
	{
		// first search the class
		A annotation = (A) beanClass.getAnnotation(annotationType);

		// if not found, search the definition class
		if (annotation == null)
		{
			Class defClass = getDefinitionClass(beanClass);

			if (defClass != null)
			{
				annotation = (A) defClass.getAnnotation(annotationType);
			}
		}

		// if not found, search the superclass
		if ((annotation == null) && (beanClass != Object.class))
		{
			annotation = getBeanAnnotation(beanClass.getSuperclass(),
					annotationType);
		}

		return annotation;
	}

	public <T extends Annotation> T getPropertyAnnotation(Class beanClass,
			String propertyName, Class<T> annotationType)
	{
		T annotation = null;

		// first search in the class for the property getter
		Method getter = ReflectHelper.getDeclaredGetter(beanClass,
				propertyName);
		
		if (getter != null)
		{
			annotation = getter.getAnnotation(annotationType);
		}

		// if not found, search in the definition class tree
		if (annotation == null)
		{
			Class defClass = getDefinitionClass(beanClass);

			if (defClass != null)
			{
				getter = ReflectHelper.getDeclaredGetter(defClass,
						propertyName);

				if (getter != null)
				{
					annotation = getter.getAnnotation(annotationType);
				}
			}
		}

		// if not found, search the superclass
		if ((annotation == null) && (beanClass != Object.class))
		{
			annotation = getPropertyAnnotation(beanClass.getSuperclass(),
					propertyName, annotationType);
		}

		return annotation;
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
}
