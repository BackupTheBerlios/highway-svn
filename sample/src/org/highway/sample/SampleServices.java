/*
 * Created on 13 oct. 2004
 */
package org.highway.sample;

import org.highway.sample.application.common.ApplicationService;
import org.highway.sample.domain.common.AccessService;
import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;


public class SampleServices
{
	private static ServiceLocator accessLocator;
	private static ServiceLocator applicationLocator;
	
	static {
		//List interceptors = Wrapper.toList(new DebugInterceptor());
		accessLocator = new DynamicLocator();//interceptors);
		applicationLocator = new DynamicLocator();//interceptors);
	}
	
	public static ApplicationService getApplicationService(Class serviceClass) {
		return (ApplicationService) applicationLocator.getService(serviceClass);
	}
	
	public static AccessService getAccessService(Class serviceClass) {
		return (AccessService) accessLocator.getService(serviceClass);
	}
}
