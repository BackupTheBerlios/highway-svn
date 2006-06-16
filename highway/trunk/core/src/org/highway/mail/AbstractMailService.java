/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail;

import org.highway.exception.TechnicalException;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Rudimentary abstract implementation of {@link MailService} for specific
 * implementations to base upon.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public abstract class AbstractMailService implements MailService
{
	protected Session session;

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
	public AbstractMailService(Properties properties)
	{
		String sessionJndi = properties.getProperty(SESSION);
		session =
			(sessionJndi == null) ? Session.getInstance(properties)
								  : (Session) lookup(sessionJndi);
	}

	public MimeMessage createMimeMessage()
	{
		return new MimeMessage(session);
	}

	public Session getSession()
	{
		return session;
	}

	/**
	 * Helper method that retrieves the named object from JNDI namespace.
	 *
	 * @param name the name of the object to look up.
	 * @return the object bound to <tt>name</tt>.
	 */
	protected Object lookup(String name)
	{
		Context context = null;

		try
		{
			context = new InitialContext();

			return context.lookup(name);
		}
		catch (NamingException e)
		{
			throw new TechnicalException(
				"Cannot locate jndi resource : " + name, e);
		}
		finally
		{
			if (context != null)
			{
				try
				{
					context.close();
				}
				catch (NamingException ignored)
				{
				}
			}
		}
	}
}
