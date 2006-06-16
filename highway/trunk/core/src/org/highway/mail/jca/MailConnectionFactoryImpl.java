/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import org.highway.exception.TechnicalException;
import java.util.Properties;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;

/**
 * Implementation of <tt>MailConnectionFactory</tt>.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailConnectionFactoryImpl implements MailConnectionFactory
{
	private ConnectionManager connectionManager;
	private MailManagedConnectionFactory managedConnectionFactory;
	private Reference reference;

	/**
	 * Default constructor.
	 *
	 * @param managedConnectionFactory a <tt>MailManagedConnectionFactory</tt> instance.
	 * @param connectionManager a <tt>ConnectionManager</tt> instance.
	 */
	public MailConnectionFactoryImpl(
		MailManagedConnectionFactory managedConnectionFactory,
		ConnectionManager connectionManager)
	{
		this.managedConnectionFactory = managedConnectionFactory;
		this.connectionManager = connectionManager;
	}

	/**
	 * Gets a connection to a Transactional Mail Resource Adapter instance.
	 *
	 * @param properties connection parameters.
	 * @throws TechnicalException if cannot allocate connection.
	 */
	public MailConnection getConnection(Properties properties)
	{
		ConnectionRequestInfo info = new MailConnectionRequestInfo(properties);

		try
		{
			return (MailConnection) connectionManager.allocateConnection(
				managedConnectionFactory, info);
		}
		catch (ResourceException e)
		{
			throw new TechnicalException("Cannot allocate connection", e);
		}
	}

	/**
	 * Sets the <tt>Reference</tt> instance.
	 * This method is called by the deployment code to set the <tt>Reference</tt>
	 * that can be later returned by the <tt>getReference</tt> method (as defined
	 * in the <tt>Referenceable</tt> interface).
	 */
	public void setReference(Reference reference)
	{
		this.reference = reference;
	}

	/**
	 * Retrieves the <tt>Reference</tt> of this object.
	 *
	 * @return the <tt>Reference</tt> of this object.
	 */
	public Reference getReference()
	{
		return reference;
	}
}
