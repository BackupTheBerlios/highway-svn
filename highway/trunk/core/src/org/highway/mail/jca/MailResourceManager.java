/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.mail.TransactionalTransport;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Session;

/**
 * Manages {@link TransactionalTransport} instances.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailResourceManager
{
	private static final Map transports = new HashMap();

	/**
	 * Returns a <tt>TransactionalTransport</tt> instance that matches the given
	 * mail session properties.
	 *
	 * @param properties mail session properties
	 * @return a <tt>TransactionalTransport</tt> instance.
	 */
	public static synchronized TransactionalTransport getTransport(
		Properties properties)
	{
		TransactionalTransport result =
			(TransactionalTransport) transports.get(properties);

		if (result == null)
		{
			DebugHome.getDebugLog().debug(
				"Creating a TransactionalTransport instance");
			DebugHome.getDebugLog().debugValue("properties", properties);

			Session session = Session.getDefaultInstance(properties);
			result = new TransactionalTransport(session);
			transports.put(properties, result);
		}

		return result;
	}

	private MailResourceManager()
	{
		throw new DoNotInstantiateException();
	}
}
