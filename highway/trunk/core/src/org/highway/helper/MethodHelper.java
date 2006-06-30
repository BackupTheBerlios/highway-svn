/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.exception.Assert;
import org.highway.exception.TechnicalException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 */
public class MethodHelper
{
	/**
	 * Method getClassAndMethodName
	 * 
	 * @param method Method
	 * @param qualified boolean
	 * @return String
	 */
	public static String getClassAndMethodName(Method method, boolean qualified)
	{
		return dumpClassAndMethodName(new StringBuffer(60), method, qualified)
				.toString();
	}

	/**
	 * Method dumpClassAndMethodName
	 * 
	 * @param buffer StringBuffer
	 * @param method Method
	 * @param qualified boolean
	 * @return StringBuffer
	 */
	public static StringBuffer dumpClassAndMethodName(StringBuffer buffer,
			Method method, boolean qualified)
	{
		ClassHelper
				.dumpClassName(buffer, method.getDeclaringClass(), qualified);
		buffer.append('.').append(method.getName()).append('(');

		Class[] params = method.getParameterTypes();

		for (int j = 0; j < params.length; j++)
		{
			ClassHelper.dumpClassName(buffer, params[j], qualified);

			if (j < (params.length - 1))
			{
				buffer.append(',');
			}
		}

		return buffer.append(')');
	}

	/**
	 * Method set
	 * 
	 * @param object Object
	 * @param setMethod Method
	 * @param value Object
	 */
	public static void set(Object object, Method setMethod, Object value)
	{
		Assert.checkNotNull(object);
		Assert.checkNotNull(setMethod);

		try
		{
			setMethod.invoke(object, new Object[] {value});
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException("Illegal access to method "
					+ getClassAndMethodName(setMethod, true), exc);
		}
		catch (InvocationTargetException exc)
		{
			throw new TechnicalException("Error in method "
					+ getClassAndMethodName(setMethod, true), exc);
		}
	}

	/**
	 * Method get
	 * 
	 * @param object Object
	 * @param getMethod Method
	 * @return Object
	 */
	public static Object get(Object object, Method getMethod)
	{
		Assert.checkNotNull(object);
		Assert.checkNotNull(getMethod);

		try
		{
			return getMethod.invoke(object, null);
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException("Illegal access to method "
					+ getClassAndMethodName(getMethod, true), exc);
		}
		catch (InvocationTargetException exc)
		{
			throw new TechnicalException("Error in method "
					+ getClassAndMethodName(getMethod, true), exc);
		}
	}

	/**
	 * Returns the method object identified by the specified class, name and
	 * paramater types. This method recursively check all the specified class
	 * superclasses to find the method. Returns null if not found.
	 * 
	 * @param klass the class which method is searched in
	 * @param methodName the method name
	 * @param parameterTypes the method parameter types
	 * @since 1.4.1
	 */
	public static Method getMethod(Class klass, String methodName,
			Class[] parameterTypes)
	{
		try
		{
			return klass.getDeclaredMethod(methodName, parameterTypes);

		}
		catch (NoSuchMethodException e)
		{
			if (klass.equals(Object.class)) return null;

			return getMethod(klass.getSuperclass(), methodName, parameterTypes);
		}
	}

	/**
	 * Invokes the specified method and wraps the execption that may be thrown
	 * in a TechnicalException.
	 * 
	 * @param method the method to execute
	 * @param target the object on which the method is executed
	 * @param parameters the method parameters
	 * @return the result of the method execution
	 * @since 1.4.3
	 */
	public static Object safeInvoke(Method method, Object target,
			Object[] parameters)
	{
		try
		{
			method.setAccessible(true);
			return method.invoke(target, parameters);
		}
		catch (InvocationTargetException exc)
		{
			throw new TechnicalException("execption thrown in method = "
					+ method, exc.getTargetException());
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException("failed to invoke method = " + method,
					exc);
		}
	}
}
