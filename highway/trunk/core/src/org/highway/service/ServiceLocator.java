/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 29 sept. 2004
 */
package org.highway.service;


/**
 * Defines a service component locator.<br>
 * The locator contains the locate and access technical logic.<br>
 * The implementation highly depends on the technology used.<br>
 * For EJB based access, use the <code>EjbLocator</code> class.<br>
 * For dynamic proxy access, use the <code>DynamicLocator</code> class.
 *
 * @author attias
 */
public interface ServiceLocator
{
	/**
	 * Locates a service component and returns a reference on it.<br>
	 * The refernce returned is usually a proxy.
	 *
	 * @param serviceClass the component service interface Class object
	 * @return reference (or proxy) on the specified service
	 */
	public Service getService(Class serviceClass);
}
