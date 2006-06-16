/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import org.highway.mail.TransactionalTransport;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

/**
 * <p>A <tt>MailManagedConnection</tt> instance represents a physical
 * connection to a Transactional Mail Resource Adapter instance.</p>
 *
 * <p>A <tt>MailManagedConnection</tt> instance provides access to a pair of
 * interfaces : <tt>javax.transaction.xa.XAResource</tt> and
 * <tt>javax.resource.spi.LocalTransaction</tt>.</p>
 *
 * <tt>XAResource</tt> interface is used by the transaction manager to
 * associate and dissociate a transaction with the underlying resource manager
 * and to perform two-phase commit protocol. The <tt>ManagedConnection</tt>
 * interface is not directly used by the transaction manager.</p>

 * <p>The <tt>LocalTransaction</tt> interface is used by the application server
 * to manage local transactions.</p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailManagedConnection implements ManagedConnection
{
	private MailConnectionRequestInfo connectionRequestInfo;
	private MailXAResource xaResource;
	private MailLocalTransaction localTransaction;
	private MailConnectionImpl connection;
	private List listeners = new ArrayList();
	private PrintWriter out;

	/**
	 * Default constructor.
	 *
	 * @param connectionRequestInfo additional connection request information.
	 */
	public MailManagedConnection(
		MailConnectionRequestInfo connectionRequestInfo)
	{
		this.connectionRequestInfo = connectionRequestInfo;

		TransactionalTransport transport =
			MailResourceManager.getTransport(connectionRequestInfo);
		xaResource = new MailXAResource(transport);
		localTransaction = new MailLocalTransaction(transport);
	}

	/**
	 * Close the application-level connection handle associated with this
	 * <tt>MailManagedConnection</tt> instance.
	 *
	 * @see MailConnection#close()
	 */
	public void close()
	{
		ConnectionEvent event =
			new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
		event.setConnectionHandle(connection);

		Iterator iterator = listeners.iterator();

		while (iterator.hasNext())
		{
			ConnectionEventListener listener =
				(ConnectionEventListener) iterator.next();
			listener.connectionClosed(event);
		}

		connection.invalidate();
		connection = null;
	}

	/**
	 * Used by the container to change the association of an application-level
	 * connection handle with a <tt>MailManagedConnection</tt> instance.
	 * The container should find the right <tt>MailManagedConnection</tt>
	 * instance and call the <tt>associateConnection</tt> method.
	 *
	 * @param connection an application-level connection handle.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public void associateConnection(Object connection)
		throws ResourceException
	{
		if (! (connection instanceof MailConnectionImpl))
		{
			throw new ResourceException(
				"Connection is not of type MailConnectionImpl");
		}

		this.connection = (MailConnectionImpl) connection;
		this.connection.setManagedConnection(this);
	}

	/**
	 * Application server calls this method to force any cleanup on the
	 * <tt>MailManagedConnection</tt> instance.
	 *
	 * @throws ResourceException generic exception if operation failed.
	 */
	public void cleanup() throws ResourceException
	{
		if (connection != null)
		{
			connection.invalidate();
		}
	}

	/**
	 * Destroys the physical connection to the underlying resource manager.
	 * This method is called when the server no longer needs the
	 * <tt>MailManagedConnection</tt> instance in the pool.
	 *
	 * @throws ResourceException generic exception if operation failed.
	 */
	public void destroy() throws ResourceException
	{
		if (connection != null)
		{
			connection.invalidate();
			connection = null;
		}

		listeners = null;
		xaResource = null;
		localTransaction = null;
		connectionRequestInfo = null;
	}

	/**
	 * Creates a new connection handle for the underlying physical connection
	 * represented by the <tt>MailManagedConnection</tt> instance.
	 *
	 * @param subject caller's security information.
	 * @param connectionRequestInfo additional connection request information.
	 * @return a {@link MailConnection} instance representing the connection handle.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public Object getConnection(
		Subject subject, ConnectionRequestInfo connectionRequestInfo)
		throws ResourceException
	{
		if (connection == null)
		{
			connection = new MailConnectionImpl(this);
		}

		return connection;
	}

	/**
	 * Returns a <tt>LocalTransaction</tt> instance.
	 * The <tt>LocalTransaction</tt> interface is used by the container to
	 * manage local transactions for a resource manager instance.
	 *
	 * @return a <tt>LocalTransaction</tt> instance.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public LocalTransaction getLocalTransaction() throws ResourceException
	{
		return localTransaction;
	}

	/**
	 * Gets the metadata information for this connection's underlying resource
	 * manager instance.
	 *
	 * @return a <tt>ManagedConnectionMetaData</tt> instance.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public ManagedConnectionMetaData getMetaData() throws ResourceException
	{
		return null;
	}

	/**
	 * Returns an <tt>XAresource</tt> instance.
	 * An application server enlists this <tt>XAResource</tt> instance with the
	 * transaction manager if the <tt>MailManagedConnection</tt> instance is
	 * being used in a JTA transaction that is being coordinated by the
	 * transaction manager.
	 *
	 * @return a <tt>XAResource</tt> instance.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public XAResource getXAResource() throws ResourceException
	{
		return xaResource;
	}

	/**
	 * Adds a connection event listener to the <tt>MailManagedConnection</tt>
	 * instance.
	 *
	 * @param listener a new connection event listener to be registered.
	 */
	public void addConnectionEventListener(ConnectionEventListener listener)
	{
		listeners.add(listener);
	}

	/**
	 * Removes an already registered connection event listener from the
	 * <tt>MailManagedConnection</tt> instance.
	 *
	 * @param listener an already registered connection event listener to be
	 * removed.
	 */
	public void removeConnectionEventListener(ConnectionEventListener listener)
	{
		listeners.remove(listener);
	}

	/**
	 * Gets the log writer.
	 *
	 * @return the log writer.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public PrintWriter getLogWriter() throws ResourceException
	{
		return out;
	}

	/**
	 * Sets the log writer.
	 *
	 * @param out an out stream for error logging and tracing.
	 * @throws ResourceException generic exception if operation failed.
	 */
	public void setLogWriter(PrintWriter out) throws ResourceException
	{
		this.out = out;
	}

	protected MailConnectionRequestInfo getConnectionRequestInfo()
	{
		return connectionRequestInfo;
	}

	protected void send(Message message, Address[] addresses)
		throws MessagingException
	{
		TransactionalTransport transport =
			MailResourceManager.getTransport(connectionRequestInfo);
		transport.sendMessage(message, addresses);
	}
}
