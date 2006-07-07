package org.highway.database.hibernate;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.hibernate.Session;
import org.highway.service.context.RequestContextHome;
import org.highway.transaction.TransactionHome;

public class SimpleHibernateTransactionManager implements TransactionManager
{
	private static final Object SESSION_CONTEXT_KEY = new Object();

	private static final ThreadLocal<SimpleHibernateTransactionHolder> localTransaction = new ThreadLocal<SimpleHibernateTransactionHolder>();

	public void begin() throws NotSupportedException, SystemException
	{
		if (localTransaction.get() != null)
			throw new SystemException("already active transaction");

		SimpleHibernateTransactionHolder transaction = new SimpleHibernateTransactionHolder();
		localTransaction.set(transaction);

		Session session = getCurrentSession();
		if (session != null)
			transaction.setHibernateTransaction(session.beginTransaction());
	}

	public void commit() throws RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException
	{
		try
		{
			SimpleHibernateTransactionHolder transaction = localTransaction
					.get();
			if (transaction != null) transaction.commit();
		}
		finally
		{
			localTransaction.set(null);
		}
	}

	public int getStatus() throws SystemException
	{
		SimpleHibernateTransactionHolder transaction = localTransaction.get();
		if (transaction != null) return transaction.getStatus();
		else return Status.STATUS_NO_TRANSACTION;
	}

	public javax.transaction.Transaction getTransaction()
			throws SystemException
	{
		return localTransaction.get();
	}

	public javax.transaction.Transaction suspend() throws SystemException
	{
		throw new UnsupportedOperationException(
				"nested transaction not supported in this transaction manager");
	}

	public void resume(javax.transaction.Transaction transaction)
			throws InvalidTransactionException, IllegalStateException,
			SystemException
	{
		throw new UnsupportedOperationException(
				"nested transaction not supported in this transaction manager");
	}

	public void rollback() throws IllegalStateException, SecurityException,
			SystemException
	{
		try
		{
			SimpleHibernateTransactionHolder transaction = localTransaction
					.get();
			if (transaction != null) transaction.rollback();
		}
		finally
		{
			localTransaction.set(null);
		}
	}

	public void setRollbackOnly() throws IllegalStateException, SystemException
	{
		SimpleHibernateTransactionHolder transaction = localTransaction.get();
		if (transaction != null) transaction.setRollbackOnly();
	}

	public void setTransactionTimeout(int timeout) throws SystemException
	{
		SimpleHibernateTransactionHolder transaction = localTransaction.get();
		if (transaction != null) transaction.setTimeout(timeout);
	}

	private static Session getCurrentSession()
	{
		return (Session) RequestContextHome.getRequestContext().getResource(
				SESSION_CONTEXT_KEY);
	}

	/**
	 * This method must only be used by the HibernateSession to pass the session
	 * to the SimpleHibernateTransactionManager when this transaction manager is
	 * the main transaction manager set in the transaction home. This method is
	 * testing if the main transaction manager is a
	 * SimpleHibernateTransactionManager and does nothing if not.
	 */
	static void setCurrentSession(Session session)
	{
		if (TransactionHome.getTransactionManager() instanceof SimpleHibernateTransactionManager)
		{
			if (getCurrentSession() != null)
				throw new UnsupportedOperationException(
						"use 2 hibernate session in the same service request is not supported by this transaction manager");

			RequestContextHome.getRequestContext().setResource(
					SESSION_CONTEXT_KEY, session);

			SimpleHibernateTransactionHolder transaction = localTransaction
					.get();

			if (transaction != null)
				transaction.setHibernateTransaction(session.beginTransaction());
		}

	}
}
