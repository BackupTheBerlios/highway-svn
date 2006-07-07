package org.highway.database.hibernate;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.xa.XAResource;

import org.hibernate.Session;
import org.hibernate.Transaction;

class SimpleHibernateTransactionHolder implements javax.transaction.Transaction,
		Status
{
	private int status = STATUS_NO_TRANSACTION;

	private Transaction hibernateTransaction;

	private Session hibernateSession;

	public void commit() throws RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException
	{
		if (status == STATUS_NO_TRANSACTION)
			throw new IllegalStateException("no transaction begun");
		
		if (status == STATUS_MARKED_ROLLBACK) rollback();
		else
		{
			status = STATUS_COMMITTING;
			hibernateTransaction.commit();
			status = STATUS_COMMITTED;
		}
	}

	public boolean delistResource(XAResource arg0, int arg1)
			throws IllegalStateException, SystemException
	{
		return false;
	}

	public boolean enlistResource(XAResource arg0) throws RollbackException,
			IllegalStateException, SystemException
	{
		return false;
	}

	public int getStatus() throws SystemException
	{
		return status;
	}

	public void registerSynchronization(Synchronization arg0)
			throws RollbackException, IllegalStateException, SystemException
	{
		throw new UnsupportedOperationException();
	}

	public void rollback() throws IllegalStateException, SystemException
	{
		if (status == STATUS_NO_TRANSACTION)
			throw new IllegalStateException("no transaction begun");
		
		status = STATUS_ROLLING_BACK;
		hibernateTransaction.rollback();
		status = STATUS_ROLLEDBACK;
	}

	public void setRollbackOnly() throws IllegalStateException, SystemException
	{
		if (status != STATUS_NO_TRANSACTION) status = STATUS_MARKED_ROLLBACK;
	}

	public void setTimeout(int timeout)
	{
	}

	void activate()
	{
		if (status == STATUS_ACTIVE)
			throw new IllegalStateException("transaction already active");
		
		hibernateTransaction = hibernateSession.beginTransaction();
		status = STATUS_ACTIVE;
	}
	
	void enlistHibernateSession(Session hibernateSession)
	{
		this.hibernateSession = hibernateSession;
		
		if (status == STATUS_ACTIVE)
			hibernateTransaction = hibernateSession.beginTransaction();
	}

}
