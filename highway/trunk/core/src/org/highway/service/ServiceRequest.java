/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 15 oct. 2004
 */
package org.highway.service;

import java.lang.reflect.Method;

/**
 * Defines a service request in the service interceptor framework.
 * Objects of this class have a lifespan of a request.
 * Use <pre>getMethod().getDeclaringClass()</pre> to get the service class.
 *
 * @author David Attias
 */
public interface ServiceRequest
{
	/**
	 * Returns a reference on the service proxy object.
	 */
	public Object getProxy();

	/**
	 * Returns a reference on the service implementation object.
	 */
	public Object getImplementation();

	/**
	 * Returns the <code>Method</code> object defining the service called.<br>
	 * The declaring class of this <code>Method</code> object is the component interface class.<br>
	 * Through the <code>Method</code> object, you can get the service type
	 * and the method parameter types :<br>
	 * <pre>
	 * Class serviceClass = request.getMethod().getDeclaringClass();
	 * Class[] parameterClasses = request.getMethod().getParameterTypes();
	 * </pre>
	 */
	public Method getMethod();

	/**
	 * Returns the parameter values of the service method called.
	 */
	public Object[] getParameterValues();

	/**
	 * Returns the parameter names of the service method called.
	 */
	public String[] getParameterNames();

	/**
	 * Continues the interception process. If no other interceptor found
	 * the service implementation is finally called.
	 *
	 * @return the value returned by the service
	 * @throws Throwable the throwable thrown by the service
	 * or a TechnicalException if an error has occured in the interception process
	 */
	public Object invoke() throws Throwable;
}
