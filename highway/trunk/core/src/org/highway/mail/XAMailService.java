/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail;

import org.highway.exception.TechnicalException;
import org.highway.mail.jca.MailConnection;
import org.highway.mail.jca.MailConnectionFactory;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * <p>Implementation of {@link MailService} that supports
 * <tt>sendInTransaction</tt> operations.<br>
 * XA transactions are supported. The connection to the mail server is checked
 * during the prepare phase of the transaction, but non-recoverable failures
 * still can happen later at commit time - due to invalid recipient addresses for
 * instance.<br>
 * Unmanaged environments are not currently supported.</p>
 *
 * <p>This implementation is based on a resource adapter, which must be deployed
 * in the application server. The code of the resource adapter is contained within
 * the Socle API jar file. To deploy it with the application, the application.xml
 * file should be completed this way :
 * <pre>
 *     &lt;module&gt;
 *         &lt;connector&gt;socle-j2ee-core.jar&lt;/connector&gt;
 *     &lt;/module&gt;
 * </pre>
 * </p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class XAMailService extends AbstractMailService
{
	/**
	 * Simple identifier constant for the J2C connection factory JNDI name
	 * property (value is {@value #CONNECTION_FACTORY}).
	 */
	public static final String CONNECTION_FACTORY =
		"highway.mail.connection.factory";
	
	private MailConnectionFactory connectionFactory;

	/**
	 * Default constructor.
	 *
	 * @param properties <tt>Properties</tt> object.<br>
	 * A value for the {@link XAMailService#CONNECTION_FACTORY} property is
	 * required.<br>
	 * If a value for the {@link MailService#SESSION} property is found, the
	 * mail session is retrieved through JNDI with this value. Otherwise, values
	 * for the properties needed to construct a mail session are expected
	 * (particularly <tt>mail.transport.protocol</tt>, <tt>mail.host</tt>,
	 * <tt>mail.user</tt> and <tt>mail.from</tt>).
	 */
	public XAMailService(Properties properties)
	{
		super(properties);

		String connectionFactoryJndi =
			properties.getProperty(CONNECTION_FACTORY);

		if (connectionFactoryJndi == null)
		{
			throw new TechnicalException(
				"Property not found : " + CONNECTION_FACTORY);
		}

		connectionFactory =
			(MailConnectionFactory) lookup(connectionFactoryJndi);
	}

	public void sendInTransaction(Message message) throws MessagingException
	{
		sendInTransaction(message, message.getAllRecipients());
	}

	public void sendInTransaction(Message message, Address[] addresses)
		throws MessagingException
	{
		MailConnection connection =
			connectionFactory.getConnection(session.getProperties());

		try
		{
			connection.send(message, addresses);
		}
		finally
		{
			if (connection != null)
			{
				connection.close();
			}
		}
	}
}
