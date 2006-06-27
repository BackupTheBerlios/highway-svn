/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * Home class of the mail package.
 * Provides static help methods and access the default <tt>MailService</tt>
 * object.
 * The default mail service must be instantiated at init time and set with
 * the <tt>setMailService</tt> method before used.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailHome
{
	/**
	 * Default <tt>MailService</tt> object.
	 */
	private static MailService mailService;

	/**
	 * Do not instantiate this class.
	 */
	private MailHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default <tt>MailService</tt>.
	 * Returns null if no <tt>MailService</tt> is set.
	 */
	public static MailService getMailService()
	{
		return mailService;
	}

	/**
	 * Returns the default <tt>MailService</tt>.
	 *
	 * @throws InitException if no default <tt>MailService</tt> is set.
	 */
	private static MailService getSafeMailService()
	{
		if (mailService == null)
		{
			throw new InitException("no default MailService set");
		}

		return mailService;
	}

	/**
	 * Sets the <tt>MailService</tt> object to be used as default.
	 * This method must be called at init stage before the <tt>getMailService</tt>
	 * method can be called.
	 *
	 * @param mailService the new default <tt>MailService</tt> object.
	 */
	public static synchronized void setMailService(MailService mailService)
	{
		MailHome.mailService = mailService;
		DebugHome.getDebugLog().info(
			"Default MailService is set to " + mailService);
	}

	/**
	 * Creates an empty <tt>MimeMessage</tt> object from the default mail service.
	 *
	 * @return an empty <tt>MimeMessage</tt> object.
	 * @throws InitException if no default <tt>MailService</tt> is set.
	 * @see MailService#createMimeMessage()
	 */
	public static MimeMessage createMimeMessage()
	{
		return getSafeMailService().createMimeMessage();
	}

	/**
	 * Gets the <tt>Session</tt> object from the default mail service.
	 *
	 * @return a <tt>Session</tt> object.
	 * @throws InitException if no default <tt>MailService</tt> is set.
	 * @see MailService#getSession()
	 */
	public static Session getSession()
	{
		return getSafeMailService().getSession();
	}

	/**
	 * Sends in transaction a message using the default mail service
	 * (optional operation).
	 *
	 * @param message the message to be sent.
	 * @throws InitException if no default <tt>MailService</tt> is set.
	 * @throws UnsupportedOperationException if the <tt>sendInTransaction</tt>
	 * method is not supported by the default mail service.
	 * @throws MessagingException for other failures.
	 * @see MailService#sendInTransaction(Message)
	 */
	public static void sendInTransaction(Message message)
		throws MessagingException
	{
		getSafeMailService().sendInTransaction(message);
	}

	/**
	 * Sends in transaction the message to the specified addresses, using the
	 * default mail service (optional operation).
	 *
	 * @param message the message to be sent.
	 * @param addresses list of addresses to send this message to.
	 * @throws InitException if no default <tt>MailService</tt> is set.
	 * @throws UnsupportedOperationException if the <tt>sendInTransaction</tt>
	 * method is not supported by the default mail service.
	 * @throws MessagingException for other failures.
	 * @see MailService#sendInTransaction(Message, Address[])
	 */
	public static void sendInTransaction(Message message, Address[] addresses)
		throws MessagingException
	{
		getSafeMailService().sendInTransaction(message, addresses);
	}
}
