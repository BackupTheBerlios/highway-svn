/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 15 oct. 2004
 */
package org.highway.service.dynamic;

import java.lang.reflect.Method;
import java.util.List;

import org.highway.service.ServiceRequestAbstract;

/**
 * Implementation of the ServiceRequest class based on the dynamic proxy
 * technology.
 *
 * @author David Attias
 */
class DynamicRequest extends ServiceRequestAbstract
{
	/**
	 * 
	 */
	private static final String PARAMETER_NAME_PREFIX = "arg";

	/**
	 * 
	 */
	private Method method;

	/**
	 * 
	 */
	private Object[] parameterValues;
	
	/**
	 * Constructs a DynamicRequest.
	 *
	 * @param proxy the component proxy
	 * @param implementation the component services implementation
	 * @param method the component interface Method object that represents
	 * the service
	 * @param parameterValues the parameter values of the service request
	 * @param interceptors the list of interceptors associated with the component
	 */
	DynamicRequest(
		Object proxy, Object implementation, Method method,
		Object[] parameterValues, List interceptors)
	{
		super(proxy, implementation, interceptors);
		this.method = method;
		this.parameterValues = parameterValues;
	}

	public Method getMethod()
	{
		return method;
	}

	public Object[] getParameterValues()
	{
		return parameterValues;
	}

	/**
	 * In the dynamic proxy world, it is difficult to get the parameter names.<br>
	 * This returns generated names of the form "argn" with n equal to
	 * the parameter place number (0, 1, 2, ...).
	 */
	public String[] getParameterNames()
	{
		if (parameterValues == null) return null;
		
		String[] parameterNames = new String[parameterValues.length];

		for (int i = 0; i < parameterNames.length; i++)
		{
			parameterNames[i] = PARAMETER_NAME_PREFIX + i;
		}

		return parameterNames;
	}
}
