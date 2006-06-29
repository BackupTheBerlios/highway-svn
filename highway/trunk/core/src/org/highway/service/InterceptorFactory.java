package org.highway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;

/**
 * Manages the interceptor instances.<br>
 * Contains the logic that look for tag in the component service interface,
 * create the interceptor instances and cache them.
 * 
 */
public class InterceptorFactory {
	/**
	 * Field NOT_FOUND
	 */
	private static final List<ServiceInterceptor> NOT_FOUND = new ArrayList<ServiceInterceptor>(
			0);

	/**
	 * Field FOUND_BUT_EMPTY
	 */
	private static final List<ServiceInterceptor> FOUND_BUT_EMPTY = new ArrayList<ServiceInterceptor>(
			0);

	private static final Map<Class, ServiceInterceptor> interceptorMap = new HashMap<Class, ServiceInterceptor>();

	/**
	 * Field serviceMap
	 */
	private static final Map<Class, List<ServiceInterceptor>> serviceMap = new HashMap<Class, List<ServiceInterceptor>>();

	/**
	 * Do not instantiate this class.
	 */
	private InterceptorFactory() {
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the list of interceptors assigned to the specified component
	 * service interface.<br>
	 * <br>
	 * This method is synchronized because used in thread intensive environment
	 * and the interceptors are created only once and cached.
	 * 
	 * @param serviceClass
	 *            the component service interface
	 * @return a list of interceptors
	 */
	public static synchronized List getInterceptors(Class serviceClass) {
		List interceptors = serviceMap.get(serviceClass);

		if (interceptors == null) {
			DebugHome.debug("Loading interceptors for service ", serviceClass
					.getName());
			interceptors = loadInterceptors(serviceClass);
			DebugHome.debugValue("Interceptors", interceptors);
			serviceMap.put(serviceClass, interceptors);
		}

		return interceptors;
	}

	private static List<ServiceInterceptor> loadInterceptors(Class serviceClass) {
		try {
			Class[] interceptorClasses = ((ServiceInterceptors) serviceClass
					.getAnnotation(ServiceInterceptors.class)).value();

			if (interceptorClasses == null) {
				return getUpperInterceptors(serviceClass);
			}

			if (interceptorClasses.length == 0) {
				return FOUND_BUT_EMPTY;
			}

			List<ServiceInterceptor> interceptors = new ArrayList<ServiceInterceptor>(
					interceptorClasses.length);

			for (int i = 0; i < interceptorClasses.length; i++) {
				interceptors.add(getInterceptor(interceptorClasses[i]));
			}
			return interceptors;
		} catch (ClassNotFoundException exc) {
			throw new TechnicalException(
					"Failed to create interceptors for service "
							+ serviceClass, exc);
		}

	}

	private static ServiceInterceptor getInterceptor(Class interceptorClass)
			throws ClassNotFoundException {
		ServiceInterceptor interceptor = interceptorMap.get(interceptorClass);

		if (interceptor == null) {
			interceptor = (ServiceInterceptor) ClassHelper
					.newInstance(interceptorClass);
			interceptorMap.put(interceptorClass, interceptor);
		}

		return (ServiceInterceptor) interceptor;
	}

	private static List getUpperInterceptors(Class serviceClass) {
		Class[] interfaces = serviceClass.getInterfaces();

		for (int i = 0; i < interfaces.length; i++) {
			List interceptors = getInterceptors(interfaces[i]);

			if (interceptors != NOT_FOUND) {
				return interceptors;
			}
		}

		return NOT_FOUND;
	}
}
