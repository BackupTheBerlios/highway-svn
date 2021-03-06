package org.highway.servicetest.presentation;

import org.highway.service.ServiceLocator;
import org.highway.service.ejb.EjbLocator;
import org.highway.servicetest.application.ApplicationService;

public class PresentationServices {

	private static final ServiceLocator applicationLocator;

	static {
		applicationLocator = new EjbLocator();
	}

	public static ApplicationService getApplicationService(Class serviceClass) {
		return (ApplicationService) applicationLocator.getService(serviceClass);
	}

}
