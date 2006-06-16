/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 25 oct. 2004
 */
package org.highway.service.ejb;

import org.highway.exception.Assert;
import org.highway.exception.TechnicalException;
import org.highway.service.ServiceRequestAbstract;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * EJB implementation of ServiceRequest.
 * Developers should not use this class.
 *
 * @author David Attias
 */
public class EjbRequest extends ServiceRequestAbstract
{
	/**
	 * 
	 */
	private Class serviceClass;

	/**
	 * 
	 */
	private String methodName;

	/**
	 * 
	 */
	private Method method;

	/**
	 * 
	 */
	private List parameters;

	/**
	 * 
	 */
	private static final int AVERAGE_PARAMETER_NUMBER = 3;

	/**
	 * Constructs an EjbRequest.
	 *
	 * @param serviceClass the service component interface
	 * @param proxy the service component proxy
	 * @param implementation the service component implementation
	 * @param methodName the service method name
	 * @param interceptors the list of interceptors to use in this request
	 */
	EjbRequest(
		Class serviceClass, Object proxy, Object implementation,
		String methodName, List interceptors)
	{
		super(proxy, implementation, interceptors);
		this.serviceClass = serviceClass;
		this.methodName = methodName;
	}

	public Method getMethod()
	{
		if (method == null)
		{
			try
			{
				method = serviceClass.getDeclaredMethod(
						methodName,	getParameterTypes());
			}
			catch (Exception exc)
			{
				throw new TechnicalException("failed to get method " + methodName
						+ " on service class " + serviceClass.getName(), exc);
			}
		}

		return method;
	}

	/**
	 * Returns the request method parameter types.<br>
	 * <br>
	 * WARNING: do not add this method to the ServiceRequest
	 * interface since the user can already access the paramter
	 * types from the method.
	 *
	 * @return Class[]
	 */
	private Class[] getParameterTypes()
	{
		if (parameters == null) return null;

		Class[] types = new Class[getParameterNumber()];

		for (int i = 0; i < types.length; i++)
		{
			types[i] = getParameterClass(i+(2*i));
		}
		
		return types;
	}

	public Object[] getParameterValues()
	{
		if (parameters == null) return null;

		Object[] values = new Object[getParameterNumber()];

		for (int i = 0; i < values.length; i++)
		{
			values[i] = getParameterValue(3*i);
		}
		
		return values;
	}

	public String[] getParameterNames()
	{
		if (parameters == null) return null;

		String[] names = new String[getParameterNumber()];

		for (int i = 0; i < names.length; i++)
		{
			names[i] = getParameterName(3*i);
		}
		
		return names;
	}

	/**
	 * Returns the specified parameter class.
	 */
	protected Class getParameterClass(int i)
	{
		return (Class) parameters.get(i);
	}

	/**
	 * Returns the specified parameter name.
	 */
	protected String getParameterName(int i)
	{
		return (String) parameters.get(i + 1);
	}

	/**
	 * Returns the specified parameter value.
	 */
	protected Object getParameterValue(int i)
	{
		return (Object) parameters.get(i + 2);
	}

	/**
	 * Returns the number of parameters of the service request.
	 */
	protected int getParameterNumber()
	{
		if (parameters == null) return 0;
		return parameters.size() / 3;
	}

	/**
	 * Adds a service parameter to the request.
	 *
	 * @param name the parameter name
	 * @param type the parameter class
	 * @param value the parameter value
	 */
	public void addParameter(String name, Class type, Object value)
	{
		// a null value is allowed
		// but the name and the type must be known
		Assert.checkNotNull(name, "parameter name is null");
		Assert.checkNotNull(type, "parameter type is null");

		if (parameters == null)
		{
			parameters = new ArrayList(AVERAGE_PARAMETER_NUMBER * 3);
		}
		
		parameters.add(type);
		parameters.add(name);
		parameters.add(value);
	}
}
