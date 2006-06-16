/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Simple implementation of {@link MailService}, that does not support
 * <tt>sendInTransaction</tt> operations.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class SimpleMailService extends AbstractMailService
{
	/**
	 * Default constructor.
	 *
	 * @param properties a <tt>Properties</tt> object.<br>
	 * If a value for the {@link MailService#SESSION} property is found, the
	 * mail session is retrieved through JNDI with this value. Otherwise, values
	 * for the properties needed to construct a mail session are expected
	 * (particularly <tt>mail.transport.protocol</tt>, <tt>mail.host</tt>,
	 * <tt>mail.user</tt> and <tt>mail.from</tt>).
	 */
	public SimpleMailService(Properties properties)
	{
		super(properties);
	}

	/**
	 * The <tt>sendInTransaction</tt> method is not supported by this mail service.
	 * @throws UnsupportedOperationException not supported.
	 */
	public void sendInTransaction(Message message) throws MessagingException
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * The <tt>sendInTransaction</tt> method is not supported by this mail service.
	 * @throws UnsupportedOperationException not supported.
	 */
	public void sendInTransaction(Message message, Address[] addresses)
		throws MessagingException
	{
		throw new UnsupportedOperationException();
	}
}
