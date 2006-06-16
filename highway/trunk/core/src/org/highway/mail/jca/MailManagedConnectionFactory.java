/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import org.highway.debug.DebugHome;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

/**
 * <p>The <tt>MailManagedConnectionFactory</tt> class acts as a factory for both
 * {@link MailConnectionFactory} instances and {@link MailManagedConnection}
 * instances.</p>
 *
 * <p>Does not currently support unmanaged environments.</p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailManagedConnectionFactory implements ManagedConnectionFactory
{
	private PrintWriter out;

	/**
	 * Unmanaged environments are not currently supported.
	 *
	 * @throws NotSupportedException not supported.
	 */
	public Object createConnectionFactory() throws ResourceException
	{
		throw new NotSupportedException(
			"Resource Adapter does not support an unmanaged environment");
	}

	/**
	 * Creates a {@link MailConnectionFactory} instance.
	 * <tt>ConnectionManager</tt> is provided by the application server.
	 *
	 * @param connectionManager <tt>ConnectionManager</tt> to be associated with
	 * created connection factory instance.
	 * @return a {@link MailConnectionFactory} instance.
	 * @throws ResourceException cannot create connection factory.
	 */
	public Object createConnectionFactory(ConnectionManager connectionManager)
		throws ResourceException
	{
		DebugHome.getDebugLog().debugEnter();

		return new MailConnectionFactoryImpl(this, connectionManager);
	}

	/**
	 * Creates a {@link MailManagedConnection} instance.
	 *
	 * @param subject caller's security information.
	 * @param connectionRequestInfo additional connection request information.<br>
	 * Must be a {@link MailConnectionRequestInfo} instance.
	 * @return a {@link MailManagedConnection} instance.
	 * @throws ResourceException cannot create connection.
	 */
	public ManagedConnection createManagedConnection(
		Subject subject, ConnectionRequestInfo connectionRequestInfo)
		throws ResourceException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue(
			"connectionRequestInfo", connectionRequestInfo);

		if (! (connectionRequestInfo instanceof MailConnectionRequestInfo))
		{
			throw new ResourceException(
				"connectionRequestInfo is not of type MailConnectionRequestInfo");
		}

		return new MailManagedConnection(
			(MailConnectionRequestInfo) connectionRequestInfo);
	}

	/**
	 * Returns a matched connection from the candidate set of connections.
	 *
	 * @param connectionSet candidate connection set.
	 * @param subject caller's security information.
	 * @param connectionRequestInfo additional connection request information.<br>
	 * @return a {@link MailManagedConnection} instance if resource adapter finds
	 * an acceptable match, <tt>null</tt> otherwise.
	 * @throws ResourceException generic exception.
	 */
	public ManagedConnection matchManagedConnections(
		Set connectionSet, Subject subject,
		ConnectionRequestInfo connectionRequestInfo) throws ResourceException
	{
		Iterator iterator = connectionSet.iterator();

		while (iterator.hasNext())
		{
			MailManagedConnection connection =
				(MailManagedConnection) iterator.next();

			if (
				connectionRequestInfo.equals(
						connection.getConnectionRequestInfo()))
			{
				return connection;
			}
		}

		return null;
	}

	/**
	 * Gets the log writer.
	 *
	 * @return the log writer.
	 * @throws ResourceException generic exception.
	 */
	public PrintWriter getLogWriter() throws ResourceException
	{
		return out;
	}

	/**
	 * Sets the log writer.
	 *
	 * @param out an out stream for error logging and tracing.
	 * @throws ResourceException generic exception.
	 */
	public void setLogWriter(PrintWriter out) throws ResourceException
	{
		this.out = out;
	}

	/**
	 * It is required that the <tt>ManagedConnectionFactory</tt> implementation
	 * class extend the implementation of the <tt>hashCode</tt> and
	 * <tt>equals</tt> methods defined in the <tt>java.lang.Object</tt> class
	 * (cf JCA 1.0, 5.5.3).
	 *
	 * @param object the reference object with which to compare.
	 * @return <tt>true</tt> if this object is the same as the object argument,
	 * <tt>false</tt> otherwise.
	 */
	public boolean equals(Object object)
	{
		return object instanceof MailManagedConnectionFactory;
	}

	/**
	 * It is required that the <tt>ManagedConnectionFactory</tt> implementation
	 * class extend the implementation of the <tt>hashCode</tt> and
	 * <tt>equals</tt> methods defined in the <tt>java.lang.Object</tt> class
	 * (cf JCA 1.0, 5.5.3).
	 *
	 * @return a hash code value for this object.
	 */
	public int hashCode()
	{
		return 0;
	}
}
