/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction;

/**
 * @author attias
 */
public interface TransactionOptions
{
	/**
	 * The MANDATORY setting (value is {@value #MANDATORY}) indicates that the
	 * service must always execute within the context of the client's
	 * transaction. If the client does not have a transaction when it calls the
	 * service, a TransactionRequiredException is thrown and the request fails.
	 */
	String MANDATORY = "Mandatory";

	/**
	 * The NEVER setting (value is {@value #NEVER}) indicates that the service
	 * cannot execute within the context of a transaction. If a client has a
	 * transaction when it calls the service, an exception is thrown.
	 */
	String NEVER = "Never";

	/**
	 * The NOT_SUPPORTED setting (value is {@value #NOT_SUPPORTED}) indicates
	 * that the service cannot execute within the context of a transaction. If a
	 * client has a transaction when it calls the service, the transaction is
	 * suspended for the duration of the method call.
	 */
	String NOT_SUPPORTED = "NotSupported";

	/**
	 * The REQUIRED setting (value is {@value #REQUIRED}) indicates that the
	 * service must execute within the context of a transaction. If a client has
	 * a transaction when it calls the service, the method will join the
	 * client's transaction context. If the client does not have a transaction,
	 * a new transaction is automatically started for the method.
	 */
	String REQUIRED = "Required";

	/**
	 * The REQUIRES_NEW setting (value is {@value #REQUIRES_NEW}) indicates
	 * that the service must execute within the context of a new transaction. A
	 * new transaction is always started for the method. If the client has a
	 * transaction when it calls the service, the client's transaction is
	 * suspended for the duration of the method call.
	 */
	String REQUIRES_NEW = "RequiresNew";

	/**
	 * The SUPPORTS setting (value is {@value #SUPPORTS}) indicates that the
	 * service can run with or without a transaction context. If a client has a
	 * transaction when it calls the service, the method will join the client's
	 * transaction context. If the client does not have a transaction, the
	 * method will run without a transaction.
	 */
	String SUPPORTS = "Supports";
}
