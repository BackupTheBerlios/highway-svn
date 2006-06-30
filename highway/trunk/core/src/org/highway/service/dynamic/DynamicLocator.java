/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 29 sept. 2004
 */
package org.highway.service.dynamic;

import org.highway.exception.TechnicalException;
import org.highway.service.InterceptorFactory;
import org.highway.service.Service;
import org.highway.service.ServiceHome;
import org.highway.service.ServiceLocator;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dynamic proxy based implementation of <code>ServiceLocator</code> interface.<br>
 * Returns <code>java.lang.reflect.Proxy</code> objects as proxy to the implementation objects.<br>
 * The proxy objects are cached and can be used by multiple threads
 * at the same time.
 *
 * 
 */
public class DynamicLocator implements ServiceLocator
{
	/**
	 * Field locatorInterceptors
	 */
	private List locatorInterceptors = null;

	/**
	 * Field proxies
	 */
	private Map proxies = new HashMap();

	/**
	 * Constructs a DynamicLocator.
	 */
	public DynamicLocator()
	{
		this(Collections.EMPTY_LIST);
	}

	/**
	 * Constructs a <code>DynamicLocator</code>.<br>
	 * <br>
	 * The specified list of interceptors are used as client side interceptors
	 * and added the the list of interceptors directly assigned to the accessed
	 * services.
	 *
	 * @param interceptors the client side interceptors
	 * @see org.highway.service.ServiceInterceptor
	 */
	public DynamicLocator(List interceptors)
	{
		this.locatorInterceptors = interceptors;
	}

	public synchronized Service getService(Class serviceClass)
	{
		if (! DynamicService.class.isAssignableFrom(serviceClass))
		{
			throw new TechnicalException(
				serviceClass + " can not be located by " + DynamicLocator.class
				+ " since it does not extend " + DynamicService.class);
		}

		Object proxy = proxies.get(serviceClass);

		if (proxy == null)
		{
			proxy = createDynamicProxy(serviceClass);
			proxies.put(serviceClass, proxy);
		}

		return (DynamicService) proxy;
	}

	/**
	 * Method createDynamicProxy
	 * @param serviceClass Class
	 * @return Object
	 */
	private Object createDynamicProxy(Class serviceClass)
	{
		try
		{
			// create an implementation
			DynamicService serviceImpl =
				(DynamicService) ServiceHome.newImplementation(serviceClass);

			// create a list of interceptors from the ones from the
			// locator and from the ones from the service
			List serviceInterceptors =
				InterceptorFactory.getInterceptors(serviceClass);
			List interceptors =
				new ArrayList(
					locatorInterceptors.size() + serviceInterceptors.size());
			interceptors.addAll(locatorInterceptors);
			interceptors.addAll(serviceInterceptors);

			// create the handler
			DynamicHandler handler =
				new DynamicHandler(serviceImpl, interceptors);

			// create and return the proxy
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			return Proxy.newProxyInstance(
				loader, new Class[] { serviceClass }, handler);
		}
		catch (Exception e)
		{
			throw new TechnicalException(
				"Failed to create dynamic proxy for service "
				+ serviceClass.getName(), e);
		}
	}
}
