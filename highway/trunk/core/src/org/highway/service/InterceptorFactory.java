package org.highway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.highway.annotation.Interceptors;
import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.helper.ClassHelper;

/**
 * Manages the interceptor instances.<br>
 * Contains the logic that look for tag in the component service interface,
 * create the interceptor instances and cache them.
 *
 */
public class InterceptorFactory
{
	/**
	 * Field NOT_FOUND
	 */
	private static final List NOT_FOUND = new ArrayList(0);

	/**
	 * Field FOUND_BUT_EMPTY
	 */
	private static final List FOUND_BUT_EMPTY = new ArrayList(0);
	private static final Map<String, Object> interceptorMap = new HashMap<String, Object>();

	/**
	 * Field serviceMap
	 */
	private static final Map<Class, List> serviceMap = new HashMap<Class, List>();

	/**
	 * Do not instantiate this class.
	 */
	private InterceptorFactory()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the list of interceptors assigned to the specified component
	 * service interface.<br>
	 * <br>
	 * This method is synchronized because used in thread intensive environment
	 * and the interceptors are created only once and cached.
	 *
	 * @param serviceClass the component service interface
	 * @return a list of interceptors
	 */
	public static synchronized List getInterceptors(Class serviceClass)
	{
		List interceptors =  serviceMap.get(serviceClass);

		if (interceptors == null)
		{
			interceptors = loadInterceptors(serviceClass);
			DebugHome.getDebugLog().debug(
				"Loading interceptors for service ", serviceClass.getName());
			DebugHome.getDebugLog().debugValue("interceptors", interceptors);
			serviceMap.put(serviceClass, interceptors);
		}

		return interceptors;
	}

	/**
	 * Method loadInterceptors
	 * @param serviceClass Class
	 * @return List
	 */
	private static List loadInterceptors(Class serviceClass)
	{
			String[] interceptorsClassName = ((Interceptors)serviceClass.getAnnotation(Interceptors.class)).value();
			if (interceptorsClassName == null)
			{
				return getUpperInterceptors(serviceClass);
			}

			if (interceptorsClassName.length == 0)
			{
				return FOUND_BUT_EMPTY;
			}
			
			List<String> interceptors = new ArrayList<String>(interceptorsClassName.length);
			for (int i = 0; i < interceptorsClassName.length; i++) {
				interceptors.add(interceptorsClassName[i]);
			}
			return interceptors;

	}

	/**
	 * Method getInterceptor
	 * @param interceptorClassName String
	 * @return ServiceInterceptor
	 * @throws ClassNotFoundException
	 */
	private static ServiceInterceptor getInterceptor(
		String interceptorClassName) throws ClassNotFoundException
	{
		Object interceptor = interceptorMap.get(interceptorClassName);

		if (interceptor == null)
		{
			interceptor = ClassHelper.newInstance(interceptorClassName);
			interceptorMap.put(interceptorClassName, interceptor);
		}

		return (ServiceInterceptor) interceptor;
	}

	/**
	 * Method getUpperInterceptors
	 * @param serviceClass Class
	 * @return List
	 */
	private static List getUpperInterceptors(Class serviceClass)
	{
		Class[] interfaces = serviceClass.getInterfaces();

		for (int i = 0; i < interfaces.length; i++)
		{
			List interceptors = getInterceptors(interfaces[i]);

			if (interceptors != NOT_FOUND)
			{
				return interceptors;
			}
		}

		return NOT_FOUND;
	}
}
