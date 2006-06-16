/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service;


/**
 * Defines the javadoc tags the service component framework uses.
 *
 * @author attias
 */
public interface ServiceTagNames
{
	/**
	 * This tag is used in the service component interface to indicate
	 * the transactional behavior of the component (if the tag is used as
	 * a class tag) or of the service (if the tag is used as a method tag).<br>
	 * <br>
	 * Correct values are: <code>NotSupported</code>, <code>Required</code>, <code>Supports</code>,
	 * <code>RequiresNew</code>, <code>Mandatory</code>, <code>Never</code>.<br>
	 * <br>
	 * Example :<br>
	 * <pre>
	 *     <code>@</code>highway.service.transaction Required
	 * </pre>
	 * They are all defined as constants in the TransactionOptions interface.
	 *
	 * @see org.highway.transaction.TransactionOptions
	 */
	String TRANSACTION_OPTION = "highway.service.transaction";

	/**
	 * This tag is used in service component interface to indicate
	 * the transaction timeout. It is a class or method tag. It is used
	 * only if a transaction is started by the service or the component
	 * it is associated to. The timeout is in milliseconds.<br>
	 * <br>
	 * Example:<br>
	 * <pre>
	 *     <code>@</code>highway.service.transaction.timeout 60000
	 * </pre>
	 */
	String TRANSACTION_TIMEOUT = "highway.service.transaction.timeout";

	/**
	 * This tag is used in service component interface to indicate the
	 * interceptors to assign to the component services. It is a class tag.<br>
	 * The service interceptor framework looks for this tag in the component
	 * service interface and all its super interfaces. The first tag found
	 * is the tag used. The value of the tag is a list of fully qualified
	 * class names of interceptor classes.<br>
	 * <br>
	 * Example:<br>
	 * <pre>
	 *     <code>@</code>highway.service.interceptors
	 *         org.highway.debug.DebugInterceptor
	 *         org.highway.transaction.TransactionInterceptor
	 * </pre>
	 */
	String INTERCEPTOR_CLASS_NAMES = "highway.service.interceptors";
}
