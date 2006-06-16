/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.ejb;

import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextAbstract;
import java.security.Principal;
import javax.ejb.SessionContext;
import javax.transaction.UserTransaction;

/**
 * EJB implementation of RequestContext.<br>
 * Developers should not use this class.
 */
class EjbRequestContext extends RequestContextAbstract
{
	/**
	 * Field ejbSessionContext
	 */
	private SessionContext ejbSessionContext;

	/**
	 * Constructs an EjbRequestContext.
	 *
	 * @param ejbSessionContext the ejb session context
	 * @param includingContext the calling context
	 */
	EjbRequestContext(
		SessionContext ejbSessionContext, RequestContext includingContext)
	{
		super(includingContext);
		this.ejbSessionContext = ejbSessionContext;
	}

	public Principal getCallerPrincipal()
	{
		return ejbSessionContext.getCallerPrincipal();
	}

	public boolean getRollbackOnly()
	{
		return ejbSessionContext.getRollbackOnly();
	}

	public UserTransaction getUserTransaction()
	{
		return ejbSessionContext.getUserTransaction();
	}

	public boolean isCallerInRole(String roleName)
	{
		return ejbSessionContext.isCallerInRole(roleName);
	}

	public void setRollbackOnly()
	{
		ejbSessionContext.setRollbackOnly();
	}
}
