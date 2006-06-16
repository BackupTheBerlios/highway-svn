/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 29 sept. 2004
 */
package org.highway.service.ejb;

import java.util.List;

/**
 * Abstract proxy class used for EJB based service access.<br>
 * Developers should not use this class.
 *
 * @author David Attias
 */
public abstract class EjbProxy
{
	/**
	 *
	 */
	private Object remote;

	/**
	 *
	 */
	private List interceptors;

	/**
	 *
	 */
	protected EjbProxy()
	{
	}

	/**
	 * Method createEjbRequest
	 * @param methodName String
	 * @return EjbRequest
	 */
	protected EjbRequest createEjbRequest(String methodName)
	{
		return new EjbRequest(
			getServiceClass(), this, remote, methodName, interceptors);
	}

	/**
	 * Returns the proxy interceptor list.
	 */
	List getInterceptors()
	{
		return interceptors;
	}

	/**
	 * Sets the proxy interceptor list.
	 */
	void setInterceptors(List interceptors)
	{
		this.interceptors = interceptors;
	}

	/**
	 * Sets the ejb remote object.
	 */
	void setRemote(Object remote)
	{
		this.remote = remote;
	}

	/**
	 * Returns the ejb remote object.
	 */
	Object getRemote()
	{
		return this.remote;
	}
	
	/**
	 * Method getServiceClass
	 * @return Class
	 */
	protected abstract Class getServiceClass();
}
