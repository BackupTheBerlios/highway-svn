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

public class SimpleHibernateTransactionManager implements TransactionManager
{
	private static final ThreadLocal<SimpleHibernateTransactionHolder> localTransaction = new ThreadLocal<SimpleHibernateTransactionHolder>();

	public void begin() throws NotSupportedException, SystemException
	{
		SimpleHibernateTransactionHolder transaction = localTransaction.get();
		if (transaction == null) localTransaction.set(new SimpleHibernateTransactionHolder());
		transaction.activate();
	}

	public void commit() throws RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException
	{
		try
		{
			SimpleHibernateTransactionHolder transaction = localTransaction.get();
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

	public void resume(javax.transaction.Transaction arg0)
			throws InvalidTransactionException, IllegalStateException,
			SystemException
	{
	}

	public void rollback() throws IllegalStateException, SecurityException,
			SystemException
	{
		try
		{
			SimpleHibernateTransactionHolder transaction = localTransaction.get();
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
		localTransaction.get().setTimeout(timeout);
	}

	public javax.transaction.Transaction suspend() throws SystemException
	{
		return localTransaction.get();
	}

	void enlistHibernateSession(Session session)
	{
		SimpleHibernateTransactionHolder transaction = localTransaction.get();
		if (transaction == null) localTransaction.set(new SimpleHibernateTransactionHolder());
		transaction.enlistHibernateSession(session);
	}
}
