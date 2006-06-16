/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * This interface provides mail services.
 * Different implementations are available.
 * The default <tt>MailService</tt> must be set with the package home class
 * {@link MailHome}.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public interface MailService
{
	/**
	 * Simple identifier constant for the mail session JNDI name property
	 * (value is {@value #SESSION}).
	 */
	static final String SESSION = "highway.mail.session";

	/**
	 * Creates an empty <tt>MimeMessage</tt> object.
	 *
	 * @return an empty <tt>MimeMessage</tt> object.
	 */
	MimeMessage createMimeMessage();

	/**
	 * Gets the <tt>Session</tt> object.
	 *
	 * @return a <tt>Session</tt> object.
	 */
	Session getSession();

	/**
	 * <p>Sends a message in transaction (optional operation).</p>
	 * <p>The message will be sent to all recipient addresses specified in the
	 * message (as returned from the <tt>Message</tt> method
	 * <tt>getAllRecipients</tt>).
	 * The <tt>saveChanges</tt> method is called on the message before it is
	 * sent.</p>
	 * <p>In a transactional context, the message is not sent when the method
	 * is called but when the transaction is committed. If a MessagingException
	 * is thrown, the current transaction is NOT rollacked since
	 * MessagingException is a checked exception. An unchecked exception such
	 * as {@link org.highway.exception.TechnicalException} should be
	 * thrown by the user to rollback the transaction if necessary.</p>
	 *
	 * @param message the message to be sent.
	 * @throws UnsupportedOperationException if the <tt>sendInTransaction</tt>
	 *         method is not supported by this mail service.
	 * @throws MessagingException for other failures.
	 */
	void sendInTransaction(Message message) throws MessagingException;

	/**
	 * <p>Sends in transaction the message to the specified addresses, ignoring any
	 * recipients specified in the message itself (optional operation).
	 * The <tt>saveChanges</tt> method is called on the message before it is
	 * sent.</p>
	 * <p>In a transactional context, the message is not sent when the method
	 * is called but when the transaction is committed. If a MessagingException
	 * is thrown, the current transaction is NOT rollacked since
	 * MessagingException is a checked exception. An unchecked exception such
	 * as {@link org.highway.exception.TechnicalException} should be
	 * thrown by the user to rollback the transaction if necessary.</p>
	 *
	 * @param message the message to be sent.
	 * @param addresses list of addresses to send this message to.
	 * @throws UnsupportedOperationException if the <tt>sendInTransaction</tt>
	 *         method is not supported by this mail service.
	 * @throws MessagingException for other failures.
	 */
	void sendInTransaction(Message message, Address[] addresses)
		throws MessagingException;
}
