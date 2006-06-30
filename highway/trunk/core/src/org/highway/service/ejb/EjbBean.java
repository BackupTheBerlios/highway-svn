/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.ejb;

import org.highway.service.InterceptorFactory;
import org.highway.service.ServiceHome;
import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextHome;
import java.util.List;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Abstract bean implementation class used for EJB based service access.<br>
 * Developers should not use this class.
 *
 * 
 */
public abstract class EjbBean implements SessionBean
{
	/**
	 * Field implementation
	 */
	private Object implementation;

	/**
	 * Field sessionContext
	 */
	private SessionContext sessionContext;

	/**
	 * Method getServiceClass
	 * @return Class
	 */
	protected abstract Class getServiceClass();

	/**
	 * Method getServiceImpl
	 * @return Object
	 */
	protected Object getServiceImpl()
	{
		if (implementation == null)
		{
			implementation = ServiceHome.newImplementation(getServiceClass());
		}

		return implementation;
	}

	/**
	 * Method init
	 * @param methodName String
	 * @return EjbRequest
	 */
	protected EjbRequest init(String methodName)
	{
		initRequestContext();

		return initEjbRequest(methodName);
	}

	/**
	 * Method initRequestContext
	 */
	private void initRequestContext()
	{
		RequestContext oldContext = RequestContextHome.getRequestContext();
		EjbRequestContext newContext =
			new EjbRequestContext(sessionContext, oldContext);
		RequestContextHome.setRequestContext(newContext);
	}

	/**
	 * Method initEjbRequest
	 * @param methodName String
	 * @return EjbRequest
	 */
	private EjbRequest initEjbRequest(String methodName)
	{
		Class serviceClass = getServiceClass();
		Object impl = getServiceImpl();
		List interceptors = InterceptorFactory.getInterceptors(serviceClass);

		return new EjbRequest(
			serviceClass, this, impl, methodName, interceptors);
	}

	/**
	 * Method reset
	 */
	protected void reset()
	{
		// reset context
		RequestContextHome.closeContext();
	}

	////////////////////////////////////
	//// EJB spec mandatory methods ////
	////////////////////////////////////

	public void setSessionContext(SessionContext sessionContext)
	{
		this.sessionContext = sessionContext;
	}

	public void ejbActivate()
	{
	}

	public void ejbCreate()
	{
	}

	public void ejbPassivate()
	{
	}

	public void ejbRemove()
	{
	}
}
