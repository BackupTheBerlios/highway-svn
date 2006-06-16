/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import org.highway.mail.TransactionalTransport;
import org.highway.transaction.xa.AbstractTransactionalResource;
import org.highway.transaction.xa.AbstractXAResource;
import org.highway.transaction.xa.TransactionalResource;
import javax.mail.MessagingException;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 * Implementation of {@link XAResource}</tt> that provides XA support to a
 * {@link TransactionalTransport} instance.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailXAResource extends AbstractXAResource
{
	private TransactionalTransport transport;

	/**
	 * Default constructor.
	 *
	 * @param transport the <tt>TransactionalTransport</tt> instance this
	 * <tt>MailXAResource</tt> is associated with.
	 */
	public MailXAResource(TransactionalTransport transport)
	{
		this.transport = transport;
	}

	/**
	 * Determines if the <tt>TransactionalTransport</tt> instance represented
	 * by the target object is the same as the <tt>TransactionalTransport</tt>
	 * instance represented by the parameter xares.
	 *
	 * @param xares an <tt>XAResource</tt> instance.
	 * @return <tt>true</tt> if it is the same resource manager instance,
	 * <tt>false</tt> otherwise.
	 * @throws XAException an error has occurred.
	 */
	public boolean isSameRM(XAResource xares) throws XAException
	{
		return (xares instanceof MailXAResource
		&& transport.equals(((MailXAResource) xares).transport));
	}

	/**
	 * <p>Obtains the current transaction timeout value set for this
	 * <tt>MailXAResource</tt> instance.</p>
	 *
	 * <p>Currently this method is not fully implemented and returns zero.</p>
	 *
	 * @throws XAException an error has occurred.
	 */
	public int getTransactionTimeout() throws XAException
	{
		return 0;
	}

	/**
	 * <p>The transaction manager calls this method during recovery to obtain
	 * the list of transaction branches that are currently in prepared or
	 * heuristically completed states.</p>
	 *
	 * <p>Currently this method is not fully implemented and returns
	 * <tt>null</tt>.</p>
	 *
	 * @param flag one of <tt>TMSTARTRSCAN</tt>, <tt>TMENDRSCAN</tt> or
	 * <tt>TMNOFLAGS</tt>.
	 * @return zero or more XIDs for the transaction branches that are
	 * currently in a prepared or heuristically completed state.
	 * @throws XAException an error has occurred.
	 */
	public Xid[] recover(int flag) throws XAException
	{
		return null;
	}

	/**
	 * <p>Sets the current transaction timeout value for this
	 * <tt>MailXAResource</tt> instance.</p>
	 *
	 * <p>Currently this method is not fully implemented and returns
	 * <tt>false</tt>.</p>
	 *
	 * @param seconds the transaction timeout value in seconds.
	 * @return <tt>true</tt> if transaction timeout value is set successfully,
	 * <tt>false</tt> otherwise.
	 * @throws XAException an error has occurred.
	 */
	public boolean setTransactionTimeout(int seconds) throws XAException
	{
		return false;
	}

	protected TransactionalResource createTransactionBranch(Xid xid)
	{
		return new MailTransactionBranch(xid, transport);
	}

	/**
	 * Transaction branch implementation that is associated with a
	 * {@link TransactionalTransport} instance.
	 */
	protected class MailTransactionBranch extends AbstractTransactionalResource
	{
		private TransactionalTransport transport;
		private TransactionalTransport.Context context;

		/**
		 * Default constructor.
		 *
		 * @param xid the xid this transactional resource is associated with.
		 * @param transport the <tt>TransactionalTransport</tt> this
		 * transaction branch is associated with.
		 */
		public MailTransactionBranch(Xid xid, TransactionalTransport transport)
		{
			super(xid);
			this.transport = transport;
		}

		public void begin() throws XAException
		{
			if (isSuspended())
			{
				throw new XAException(XAException.XAER_PROTO);
			}

			try
			{
				transport.startTransaction();
			}
			catch (IllegalStateException e)
			{
				throw new XAException(e.getMessage());
			}
		}

		public void commit() throws XAException
		{
			try
			{
				transport.commitTransaction();
			}
			catch (IllegalStateException e)
			{
				throw new XAException(e.getMessage());
			}
			catch (MessagingException e)
			{
				throw new XAException(e.getMessage());
			}
		}

		public int prepare() throws XAException
		{
			if (isSuspended())
			{
				resume();
			}

			if (transport.isTransactionMarkedForRollback())
			{
				throw new XAException(XAException.XA_RBROLLBACK);
			}

			try
			{
				transport.connect();
			}
			catch (MessagingException e)
			{
				throw new XAException(e.getMessage());
			}

			return XAResource.XA_OK;
		}

		public void resume() throws XAException
		{
			if (! isSuspended())
			{
				throw new XAException(XAException.XAER_PROTO);
			}

			try
			{
				transport.resumeTransaction(context);
			}
			catch (IllegalStateException e)
			{
				throw new XAException(e.getMessage());
			}

			context = null;
		}

		public void rollback() throws XAException
		{
			if (isSuspended())
			{
				resume();
			}

			try
			{
				transport.rollbackTransaction();
			}
			catch (IllegalStateException e)
			{
				throw new XAException(e.getMessage());
			}
		}

		public void suspend() throws XAException
		{
			if (isSuspended())
			{
				throw new XAException(XAException.XAER_PROTO);
			}

			try
			{
				context = transport.suspendTransaction();
			}
			catch (IllegalStateException e)
			{
				throw new XAException(e.getMessage());
			}
		}

		private boolean isSuspended()
		{
			return context != null;
		}
	}
}
