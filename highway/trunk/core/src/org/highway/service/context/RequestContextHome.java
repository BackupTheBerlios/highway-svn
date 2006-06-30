/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.context;


/**
 * Home class of this package.<br>
 * Access point of the service request context.
 *
 * 
 */
public abstract class RequestContextHome
{
	/**
	 * Field requestContextThreadLocal
	 */
	private static ThreadLocal requestContextThreadLocal = new ThreadLocal();

	/**
	 * Sets the service request context to the specified context object.<br>
	 * Developers should not use this method.<br>
	 * How the request context is set depends on the chosen service access technology.
	 *
	 * @param context the context to set
	 */
	public static void setRequestContext(RequestContext context)
	{
		requestContextThreadLocal.set(context);
	}

	/**
	 * Returns the service request context attached to the current thread.<br>
	 * Returns null if no context is set. A context must exists when
	 * the current thread is processing a service request.
	 *
	 * @return the current context
	 */
	public static RequestContext getRequestContext()
	{
		return (RequestContext) requestContextThreadLocal.get();
	}

	/**
	 * Checks if the context is set for the current thread.
	 */
	public static boolean isContextSet()
	{
		return getRequestContext() != null;
	}

	/**
	 * Closes the context if set for the current thread.
	 */
	public static void closeContext()
	{
		RequestContext context = getRequestContext();

		if (context != null)
		{
			context.close();
			setRequestContext(context.getCallerContext());
		}
	}

	/**
	 * Returns the user login.
	 *
	 * @throws IllegalStateException if no context is set
	 */
	public static String getUserLogin() throws IllegalStateException
	{
		RequestContext context = getRequestContext();

		return (context == null) ? null : context.getCallerPrincipal().getName();
	}
}
