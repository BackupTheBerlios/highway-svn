/*
 * Created on 13 oct. 2004
 */
package org.highway.sample;

import org.highway.sample.application.common.ApplicationService;
import org.highway.service.ServiceLocator;
import org.highway.service.ejb.EjbLocator;

public class ApplicationLocator
{
	private static ServiceLocator applicationLocator = new EjbLocator();

	public static ApplicationService getApplicationService(Class serviceClass)
	{
		return (ApplicationService) applicationLocator.getService(serviceClass);
	}
}
