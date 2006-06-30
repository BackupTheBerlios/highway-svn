/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.context;

import org.highway.lifecycle.Closeable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.transaction.UserTransaction;

/**
 * Abstract implementation of the RequestContext interface.<br>
 * Manages mainly the caller context and the resources.
 *
 * 
 */
public abstract class RequestContextAbstract implements RequestContext
{
	/**
	 * Field resources
	 */
	private Map resources;

	/**
	 * Field callerContext
	 */
	private RequestContext callerContext;

	/**
	 * Constructs a RequestContextAbstract.
	 *
	 * @param callerContext the context of the calling service if any
	 */
	protected RequestContextAbstract(RequestContext includingContext)
	{
		this.callerContext = includingContext;
	}

	public RequestContext getCallerContext()
	{
		return callerContext;
	}

	public void setResource(Object key, Object resource)
	{
		if (callerContext == null)
		{
			if (resources == null)
			{
				resources = new HashMap(2);
			}

			resources.put(key, resource);
		}
		else
		{
			callerContext.setResource(key, resource);
		}
	}

	public Object getResource(Object key)
	{
		if (callerContext == null)
		{
			if (resources == null)
			{
				return null;
			}

			return resources.get(key);
		}

		return callerContext.getResource(key);
	}

	public void close()
	{
		if (resources != null)
		{
			for (Iterator iter = resources.values().iterator(); iter.hasNext();)
			{
				Object resource = iter.next();

				if (resource instanceof Closeable)
				{
					((Closeable) resource).close();
				}
			}

			resources.clear();
			resources = null;
		}
	}

	public abstract UserTransaction getUserTransaction();

	public abstract Principal getCallerPrincipal();

	public abstract boolean getRollbackOnly();

	public abstract void setRollbackOnly();
}
