/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * <p>A <tt>MailConnection</tt> represents an application-level handle that is
 * used by a client to access the underlying physical connection.
 * The actual physical connection associated with a <tt>MailConnection</tt>
 * instance is represented by a {@link MailManagedConnection} instance.</p>
 *
 * <p>A client gets a <tt>MailConnection</tt> instance by using the
 * <tt>getConnection</tt> method on a {@link MailConnectionFactory}
 * instance.</p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public interface MailConnection
{
	/**
	 * Sends the message to the specified list of addresses.
	 *
	 * @param message the message to be sent.
	 * @param addresses list of addresses to send this message to.
	 * @throws MessagingException if the message could not be sent.
	 */
	void send(Message message, Address[] addresses) throws MessagingException;

	/**
	 * Initiates close of the connection handle at the application level.
	 * This method must delegate the close to the {@link MailManagedConnection}
	 * that created the connection handle. Closing a connection handle should
	 * not close the physical connection to the resource manager.
	 */
	void close();
}
