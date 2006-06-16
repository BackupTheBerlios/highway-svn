/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail;

import org.highway.debug.DebugHome;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.transaction.Status;

/**
 * <p>This class adds transactional control to {@link javax.mail.Transport}.</p>
 *
 * <p>Start a transaction by calling {@link #startTransaction()}. Then send
 * messages by calling {@link #sendMessage(Message, Address[])} and finally
 * either call {@link #commitTransaction()} to make your changes permanent or
 * {@link #rollbackTransaction()} to undo them.</p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class TransactionalTransport implements Status
{
	private ThreadLocal contexts = new ThreadLocal();
	private Session session;

	/**
	 * Default constructor.
	 *
	 * @param session a mail session.
	 */
	public TransactionalTransport(Session session)
	{
		this.session = session;
	}

	/**
	 * Closes this service and terminates its connection.
	 */
	public void close()
	{
		Context context = getContext();

		if (context != null)
		{
			if (context.transport != null)
			{
				try
				{
					context.transport.close();
				}
				catch (MessagingException e)
				{
					DebugHome.getDebugLog().warn("Problem while closing connection", e);
				}
			}

			context.connected = false;
		}
	}

	/**
	 * Connects to the service.
	 *
	 * @throws AuthenticationFailedException for authentication failures.
	 * @throws MessagingException for other failures.
	 * @throws java.lang.IllegalStateException if the service is already connected.
	 */
	public void connect() throws MessagingException
	{
		Context context = getContext();

		if (context == null)
		{
			context = new Context();
			setContext(context);
		}

		if (context.transport == null)
		{
			context.transport = session.getTransport();
		}

		context.transport.connect();
		context.connected = true;
	}

	/**
	 * Is this service currently connected ?<br>
	 * This implementation uses a private boolean field to store the connection
	 * state. This method returns the value of that field.
	 *
	 * @return <tt>true</tt> if the service is connected, <tt>false</tt> otherwise.
	 */
	public boolean isConnected()
	{
		Context context = getContext();

		return ((context != null) && context.connected);
	}

	/**
	 * Sends the message to the specified list of addresses.
	 * If this method is called in a transactional context, the message is
	 * just memorized and will be sent when the transaction is committed.
	 *
	 * @param message the message to be sent.
	 * @param addresses list of addresses to send this message to.
	 * @throws MessagingException if the message could not be sent.
	 */
	public void sendMessage(Message message, Address[] addresses)
		throws MessagingException
	{
		Context context = getContext();

		if (
			(context == null)
				|| (context.status == Status.STATUS_NO_TRANSACTION))
		{
			try
			{
				send(message, addresses);
			}
			finally
			{
				close();
			}
		}
		else
		{
			context.addMessage(message, addresses);
		}
	}

	/**
	 * Commits all changes made in the current transaction and deletes the association
	 * between the current thread and the transaction.
	 * The messages memorized by calling the {@link #sendMessage(Message, Address[])}
	 * method are now really sent.
	 *
	 * @throws MessagingException if the messages could not be sent.
	 */
	public void commitTransaction() throws MessagingException
	{
		Context context = checkTransaction();

		if (context.status == Status.STATUS_MARKED_ROLLBACK)
		{
			throw new IllegalStateException(
				"Active thread " + Thread.currentThread()
				+ " is marked for rollback");
		}

		try
		{
			if (context.messages != null)
			{
				Iterator iterator = context.messages.iterator();
				while (iterator.hasNext())
				{
					MessageWrapper messageWrapper =
						(MessageWrapper) iterator.next();
					send(
						messageWrapper.getMessage(), messageWrapper.getAddresses());
				}
			}
		}
		finally
		{
			dispose();
		}
	}

	/**
	 * Checks whether this transaction has been marked to allow a rollback as the only
	 * valid outcome. This can be set my method {@link #markTransactionForRollback()} or might
	 * be set internally be any fatal error. Once a transaction is marked for rollback there
	 * is no way to undo this. A transaction that is marked for rollback can not be committed,
	 * also rolled back.
	 *
	 * @return <tt>true</tt> if this transaction has been marked for a roll back.
	 */
	public boolean isTransactionMarkedForRollback()
	{
		Context context = checkTransaction();

		return (context.status == Status.STATUS_MARKED_ROLLBACK);
	}

	/**
	 * Marks the current transaction to allow only a rollback as valid outcome.
	 */
	public void markTransactionForRollback()
	{
		Context context = checkTransaction();
		context.status = Status.STATUS_MARKED_ROLLBACK;
	}

	/**
	 * Resumes a transaction in the current thread that has previously been suspened
	 * by {@link #suspendTransaction()}.
	 *
	 * @param context the identifier for the transaction to be resumed, delivered by
	 * {@link #suspendTransaction()}.
	 */
	public void resumeTransaction(Context context)
	{
		checkNoTransaction();

		if (context == null)
		{
			throw new IllegalStateException("No transaction to resume");
		}

		if (! context.suspended)
		{
			throw new IllegalStateException(
				"Transaction to resume needs to be suspended");
		}

		context.suspended = false;
		setContext(context);
	}

	/**
	 * Discards all changes made in the current transaction and deletes the association
	 * between the current thread and the transaction.
	 */
	public void rollbackTransaction()
	{
		checkTransaction();
		dispose();
	}

	/**
	 * <p>Starts a new transaction and associates it with the current thread. All
	 * subsequent changes in the same thread made to the transport are invisible
	 * from other threads until {@link #commitTransaction()} is called.
	 * Use {@link #rollbackTransaction()} to discard your changes. After calling
	 * either method there will be no transaction associated to the current thread
	 * any longer.</p>
	 *
	 * <p><em>Caution :</em> Be careful to finally call one of those methods,
	 * as otherwise the transaction will lurk around for ever.</p>
	 */
	public void startTransaction()
	{
		Context context = getContext();

		if (context == null)
		{
			context = new Context();
			setContext(context);
		}
		else
		{
			checkNoTransaction();
		}

		context.status = Status.STATUS_ACTIVE;
	}

	/**
	 * <p>Suspends the transaction associated to the current thread. I.e. the associated
	 * between the current thread and the transaction is deleted. This is useful
	 * when you want to continue the transaction in another thread later.
	 * Call {@link #resumeTransaction(TransactionalTransport.Context)} - possibly in another thread than
	 * the current - to resume work on the transaction.</p>
	 *
	 * <p><em>Caution :</em> When calling this method the returned identifier for the
	 * transaction is the only remaining reference to the transaction, so be sure to
	 * remember it or the transaction will be eventually deleted (and thereby rolled back)
	 * as garbage.</p>
	 *
	 * @return an identifier for the suspended transaction, will be needed to later resume
	 * the transaction by {@link #resumeTransaction(TransactionalTransport.Context)}.
	 */
	public Context suspendTransaction()
	{
		Context context = checkTransaction();
		context.suspended = true;
		setContext(null);

		return context;
	}

	private Context getContext()
	{
		return (Context) contexts.get();
	}

	private void setContext(Context context)
	{
		contexts.set(context);
	}

	private Context checkNoTransaction()
	{
		Context context = getContext();

		if (
			(context != null)
				&& (context.status != Status.STATUS_NO_TRANSACTION))
		{
			throw new IllegalStateException(
				"Active thread " + Thread.currentThread()
				+ " already associated with a transaction");
		}

		return context;
	}

	private Context checkTransaction()
	{
		Context context = getContext();

		if (
			(context == null)
				|| (context.status == Status.STATUS_NO_TRANSACTION))
		{
			throw new IllegalStateException(
				"Active thread " + Thread.currentThread()
				+ " not associated with a transaction");
		}

		return context;
	}

	private void dispose()
	{
		Context context = getContext();

		if (context != null)
		{
			close();
			context.transport = null;
			context.messages = null;
			context.status = Status.STATUS_NO_TRANSACTION;
			setContext(null);
		}
	}

	private void send(Message message, Address[] addresses)
		throws MessagingException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("message", message);
		DebugHome.getDebugLog().debugValue("addresses", addresses);

		if (! isConnected())
		{
			connect();
		}

		message.saveChanges();

		Context context = getContext();
		context.transport.sendMessage(message, addresses);
	}

	/**
	 * Transaction context.
	 */
	public class Context
	{
		Transport transport = null;
		Collection messages = null;
		int status = Status.STATUS_NO_TRANSACTION;
		boolean suspended = false;
		boolean connected = false;

		Context()
		{
		}

		void addMessage(Message message, Address[] addresses)
		{
			if (messages == null)
			{
				messages = new ArrayList();
			}

			messages.add(new MessageWrapper(message, addresses));
		}
	}

	private class MessageWrapper
	{
		private Message message;
		private Address[] addresses;

		public MessageWrapper(Message message, Address[] addresses)
		{
			this.message = message;
			this.addresses = addresses;
		}

		public Message getMessage()
		{
			return message;
		}

		public Address[] getAddresses()
		{
			return addresses;
		}
	}
}
