/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import org.highway.mail.TransactionalTransport;
import javax.mail.MessagingException;
import javax.resource.ResourceException;
import javax.resource.spi.LocalTransaction;

/**
 * Provides support for transactions that are managed internal to the
 * Transactional Mail Resource Manager, and do not require an external
 * transaction manager.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailLocalTransaction implements LocalTransaction
{
	private TransactionalTransport transport;

	/**
	 * Default constructor.
	 *
	 * @param transport
	 */
	public MailLocalTransaction(TransactionalTransport transport)
	{
		this.transport = transport;
	}

	/**
	 * Begins a local transaction.
	 *
	 * @throws ResourceException generic exception if operation fails.
	 */
	public void begin() throws ResourceException
	{
		transport.startTransaction();
	}

	/**
	 * Commits a local transaction.
	 *
	 * @throws ResourceException generic exception if operation fails.
	 */
	public void commit() throws ResourceException
	{
		try
		{
			transport.commitTransaction();
		}
		catch (MessagingException e)
		{
			throw new ResourceException(e.getMessage());
		}
	}

	/**
	 * Rollbacks a local transaction.
	 *
	 * @throws ResourceException generic exception if operation fails.
	 */
	public void rollback() throws ResourceException
	{
		transport.rollbackTransaction();
	}
}
