package org.highway.servicetest;

import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;
import org.highway.servicetest.access.AccessService;
import org.highway.servicetest.application.ApplicationService;

public class ServiceTestServices
{
	private static final ServiceLocator applicationLocator = new DynamicLocator();

	private static final ServiceLocator accessLocator = new DynamicLocator();

	public static AccessService getAccessService(Class serviceClass)
	{
		return (AccessService) accessLocator.getService(serviceClass);
	}

	public static ApplicationService getApplicationService(Class serviceClass)
	{
		return (ApplicationService) applicationLocator.getService(serviceClass);
	}
}
