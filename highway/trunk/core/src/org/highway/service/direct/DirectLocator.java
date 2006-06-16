/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.direct;

import org.highway.exception.TechnicalException;
import org.highway.service.Service;
import org.highway.service.ServiceHome;
import org.highway.service.ServiceLocator;
import java.util.HashMap;
import java.util.Map;

/**
 * Direct implementation of the ServiceLocator interface.<br>
 * This locator directly returns a reference to the component implementation.
 *
 * @author David Attias
 */
public class DirectLocator implements ServiceLocator
{
	/**
	 * Field services
	 */
	private Map services = new HashMap();

	public synchronized Service getService(Class serviceClass)
	{
		if (! DirectService.class.isAssignableFrom(serviceClass))
		{
			throw new TechnicalException(
				serviceClass.getName() + " can not be located by "
				+ DirectLocator.class.getName() + " since it does not extend "
				+ DirectService.class.getName());
		}

		Object service = services.get(serviceClass);

		if (service == null)
		{
			service = ServiceHome.newImplementation(serviceClass);
			services.put(serviceClass, service);
		}

		return (DirectService) service;
	}
}
