/*
 * Created on 13 oct. 2004
 */
package org.highway.sample.application.common;

import org.highway.sample.access.common.AccessService;
import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;

public class AccessLocator
{
	private static ServiceLocator accessLocator = new DynamicLocator();

	public static AccessService getAccessService(Class serviceClass)
	{
		return (AccessService) accessLocator.getService(serviceClass);
	}
}
