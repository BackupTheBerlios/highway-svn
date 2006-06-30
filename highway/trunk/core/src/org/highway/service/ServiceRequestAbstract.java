/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 25 oct. 2004
 */
package org.highway.service;

import org.highway.exception.TechnicalException;
import org.highway.helper.CollectionHelper;
import org.highway.helper.MethodHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract implementation of <code>ServiceRequest</code> class.<br>
 * Manages the proxy reference, the implementation reference and interceptor list.
 *
 * 
 */
public abstract class ServiceRequestAbstract implements ServiceRequest
{
	/**
	 * 
	 */
	private Object proxy;

	/**
	 * 
	 */
	private Object implementation;

	/**
	 * 
	 */
	private Iterator interceptors;

	/**
	 * Constructs a <code>ServiceRequestAbstract</code> object.
	 *
	 * @param proxy the service proxy
	 * @param implementation the service implementation
	 * @param interceptors the list of interceptors used to intercept the
	 * specified service
	 */
	public ServiceRequestAbstract(
		Object proxy, Object implementation, List interceptors)
	{
		this.proxy = proxy;
		this.implementation = implementation;
		this.interceptors = CollectionHelper.iterator(interceptors);
	}

	public Object getProxy()
	{
		return proxy;
	}

	public Object getImplementation()
	{
		return implementation;
	}

	public final Object invoke() throws Throwable
	{
		if (interceptors.hasNext())
		{
			ServiceInterceptor interceptor =
				(ServiceInterceptor) interceptors.next();

			return interceptor.invoke(this);
		}
		else
		{
			return invokeImplementation();
		}
	}

	protected Object invokeImplementation() throws Throwable
	{
		try
		{
			return getImplementationMethod().invoke(implementation, getParameterValues());
		}
		catch (IllegalArgumentException exc)
		{
			throw new TechnicalException("failed to invoke service "
				+ MethodHelper.getClassAndMethodName(getMethod(), true), exc);
		}
		catch (IllegalAccessException exc)
		{
			throw new TechnicalException("failed to invoke service "
				+ MethodHelper.getClassAndMethodName(getMethod(), true), exc);
		}
		catch (InvocationTargetException exc)
		{
			throw exc.getTargetException();
		}
	}

	/**
	 * Returns the <code>Method</code> object that implements the service.<br>
	 * The declaring class of this <code>Method</code> object must be the class of
	 * the implementation object.<br>
	 * <br>
	 * This method is used in the <code>invoke()</code> method and
	 * must be implemented by subclasses.
	 *
	 * @return the Method object that implements the sercice called
	 */
	private Method getImplementationMethod()
	{
		// TODO see how we could use the MethodHelper class to share
		// this kind of technical logic
		
		Method serviceMethod = getMethod();
		Class implClass = getImplementation().getClass();
		
		try
		{
			if (serviceMethod.getDeclaringClass().isAssignableFrom(implClass))
			{
				return serviceMethod;
			}
			else
			{
				// TODO why do we only get the declared method?
				// Should we care if the method is directly declared in the
				// implementation class and not in super classes?
				return implClass.getDeclaredMethod(
					serviceMethod.getName(), serviceMethod.getParameterTypes());
			}
		}
		catch (SecurityException exc)
		{
			throw new TechnicalException("failed to get method = " + serviceMethod
				+ " on implementation class = " + implClass.getName(), exc);
		}
		catch (NoSuchMethodException exc)
		{
			throw new TechnicalException("failed to get method = " + serviceMethod
					+ " on implementation class = " + implClass.getName(), exc);
		}
	}

}
