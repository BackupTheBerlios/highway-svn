/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.context;

import org.highway.lifecycle.Closeable;
import java.security.Principal;
import javax.transaction.UserTransaction;

/**
 * Defined the context of a service request.<br>
 * The implementation of this interface depends on the access technology
 * used by the service.<br>
 * Use class RequestContextHome to get the current service context.
 *
 * 
 */
public interface RequestContext extends Closeable
{
	/**
	 * Return the UserTransaction object associated with the current service request.
	 */
	public UserTransaction getUserTransaction();

	/**
	 * Returns the caller principal of the current service request.<br>
	 * The principal defines a user of the system.
	 */
	public Principal getCallerPrincipal();

	//public boolean isCallerInRole(String roleName);

	/**
	 * Checks if the current service request transaction is marked for rollback.
	 */
	public boolean getRollbackOnly();

	/**
	 * Marks the current service request transaction for rollback.
	 */
	public void setRollbackOnly();

	/**
	 * Returns the context of the calling service if any.<br>
	 * Returns null if no caller context exixts.
	 */
	public RequestContext getCallerContext();

	/**
	 * Maps the specified resource to the specified key in this context.<br>
	 * If the specified key was already mapped to another resource,
	 * the old resource is replaced.<br>
	 * <br>
	 * If this context reference a caller context, resources are
	 * mapped in the caller context.
	 *
	 * @param key the key object
	 * @param resource the resource object
	 */
	public void setResource(Object key, Object resource);

	/**
	 * Returns the resource to which the specified key is associated.<br>
	 * <br>
	 * If this context reference a caller context, resources are
	 * mapped in the caller context.
	 *
	 * @param key the key object
	 * @return the associated resource object
	 */
	public Object getResource(Object key);

	/**
	 * Closes this context.<br>
	 * <br>
	 * Releases (and closes if of type Closeable) all the resources
	 * directly mapped in this context. If this context reference a caller
	 * context, all the resources mapped in the caller context through this
	 * context will be released and eventualy closed when the caller context
	 * closes.<br>
	 * <br>
	 * This method is defined by the Closeable interface which this interface extends.
	 */
	public void close();
}
