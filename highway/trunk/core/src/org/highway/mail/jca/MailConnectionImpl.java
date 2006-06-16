/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Implementation of <tt>MailConnection</tt>.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailConnectionImpl implements MailConnection
{
	private MailManagedConnection managedConnection;

	/**
	 * Default constructor.
	 *
	 * @param managedConnection a managed connection.
	 */
	public MailConnectionImpl(MailManagedConnection managedConnection)
	{
		this.managedConnection = managedConnection;
	}

	public void send(Message message, Address[] addresses)
		throws MessagingException
	{
		managedConnection.send(message, addresses);
	}

	public void close()
	{
		managedConnection.close();
	}

	/**
	 * Invalidates this connection.
	 */
	protected void invalidate()
	{
		managedConnection = null;
	}

	/**
	 * Sets the managed connection.
	 *
	 * @param managedConnection a managed connection.
	 */
	protected void setManagedConnection(
		MailManagedConnection managedConnection)
	{
		this.managedConnection = managedConnection;
	}
}
