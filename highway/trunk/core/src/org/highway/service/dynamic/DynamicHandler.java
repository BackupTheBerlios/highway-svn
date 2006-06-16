/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.dynamic;

import org.highway.debug.DebugHome;
import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextHome;
import org.highway.service.context.SimpleRequestContext;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author David Attias
 */
class DynamicHandler implements InvocationHandler
{
	private DynamicService serviceImpl;

	private List interceptors;

	DynamicHandler(DynamicService serviceImpl, List interceptors)
	{
		this.serviceImpl = serviceImpl;
		this.interceptors = interceptors;
	}

	public Object invoke(Object proxy, Method method, Object[] parameters)
		throws Throwable
	{
		RequestContext context = RequestContextHome.getRequestContext();

		if (context == null)
		{
			/**
			 * if no context, it means this is the first service
			 * this thread is entering, it means i need to create
			 * a simple context and it means i need to close this
			 * simple context when this invocation returns.
			 * 
			 * if there is already a context, the service which has
			 * created the context will close it.
			 * 
			 * TODO (attias) add code to manage the user login
			 * transfer between layers
			 */
			context = new SimpleRequestContext(null);
			RequestContextHome.setRequestContext(context);

			try
			{
				return doInvoke(proxy, method, parameters);
			}
			finally
			{
				/**
				 * TODO (attias) better manage context close exceptions :
				 * 1) all the resources should be closed even if the first
				 * resource close throw an exception
				 * 2) have an exception able to collect all the errors
				 * 3) why do the close after the interceptors invocation?
				 * if there is an error in the close, the transaction interceptor
				 * could for example rollback the transaction
				 * 4) if no error from doInvoke, let close exception propagate
				 * 5) check this in the ejb implem
				 */
				try
				{
					RequestContextHome.closeContext();				
				}
				catch (Throwable throwable)
				{
					DebugHome.getDebugLog().error(
						"Failed to close context of service " + method,
						throwable);
				}
			}
		}
		else
		{
			return doInvoke(proxy, method, parameters);
		}
	}

	private Object doInvoke(Object proxy, Method method, Object[] parameters)
		throws Throwable
	{
		DynamicRequest request =
			new DynamicRequest(
				proxy, serviceImpl, method, parameters, interceptors);

		return request.invoke();
	}
}
