/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 29 sept. 2004
 */
package org.highway.debug;

import org.highway.service.ServiceInterceptor;
import org.highway.service.ServiceRequest;
import java.lang.reflect.Method;

/**
 * This interceptor uses the default DebugLog object to log
 * all access to the services this interceptor is assigned to.<br>
 * It works only if the default debug log object implements
 * the interface ServiceDebugLog. If it does not, no messages
 * are logged.<br>
 * A service access will result in the following log messages :<br>
 * <ul>
 *      <li>the entering</li>
 *         <li>the names and values of the parameters</li>
 *         <li>the exiting of a service</li>
 *         <li>the returned value</li>
 *         <li>the exception thrown</li>
 * </ul>
 *
 * @author attias
 * @see org.highway.debug.DebugLog
 * @see org.highway.debug.ServiceDebugLog
 */
public class DebugInterceptor implements ServiceInterceptor
{
	public Object invoke(ServiceRequest request) throws Throwable
	{
		Method method = request.getMethod();
		ServiceDebugLog log = null;

		if (
			DebugHome.getDebugLog().isEnabled()
				&& DebugHome.getDebugLog() instanceof ServiceDebugLog)
		{
			log = (ServiceDebugLog) DebugHome.getDebugLog();
		}

		if (log != null)
		{
			log.debugServiceEnter(method);

			Object[] parameterValues = request.getParameterValues();
			String[] parameterNames = request.getParameterNames();

			if (parameterValues != null)
			{
				for (int i = 0; i < parameterValues.length; i++)
				{
					log.debugServiceParameter(
						method, parameterNames[i], parameterValues[i]);
				}
			}
		}

		Object returnValue = null;

		try
		{
			returnValue = request.invoke();
		}
		catch (Throwable throwable)
		{
			if (log != null)
			{
				log.debugServiceError(method, throwable);
			}

			throw throwable;
		}

		if (log != null)
		{
			if (method.getReturnType().equals(void.class))
			{
				log.debugServiceExit(method);
			}
			else
			{
				log.debugServiceExit(method, returnValue);
			}
		}

		return returnValue;
	}
}
