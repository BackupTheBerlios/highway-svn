package org.highway.database.hibernate;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.xa.XAResource;

import org.hibernate.Transaction;
import org.highway.exception.BugException;

class SimpleHibernateTransactionHolder implements
		javax.transaction.Transaction, Status
{
	private int status = STATUS_ACTIVE;

	private Transaction hibernateTransaction;

	public void commit() throws RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException
	{
		if (status == STATUS_MARKED_ROLLBACK)
		{
			rollback();
		}
		else if (status == STATUS_ACTIVE)
		{
			status = STATUS_COMMITTING;
			if (hibernateTransaction != null) hibernateTransaction.commit();
			status = STATUS_COMMITTED;
		}
		else
		{
			throw new IllegalStateException(
					"commit denied with transaction status = " + status);
		}
	}

	public int getStatus() throws SystemException
	{
		return status;
	}

	public void rollback() throws IllegalStateException, SystemException
	{
		if (status == STATUS_ACTIVE || status == STATUS_MARKED_ROLLBACK)
		{
			status = STATUS_ROLLING_BACK;
			if (hibernateTransaction != null) hibernateTransaction.rollback();
			status = STATUS_ROLLEDBACK;
		}
		else
		{
			throw new IllegalStateException(
					"rollback denied with transaction status = " + status);
		}
	}

	public void setRollbackOnly() throws IllegalStateException, SystemException
	{
		if (status == STATUS_ACTIVE || status == STATUS_MARKED_ROLLBACK)
		{
			status = STATUS_MARKED_ROLLBACK;
		}
		else
		{
			throw new IllegalStateException(
					"setRollbackOnly denied with transaction status = "
							+ status);
		}
	}

	public boolean delistResource(XAResource arg0, int arg1)
			throws IllegalStateException, SystemException
	{
		throw new UnsupportedOperationException();
	}

	public boolean enlistResource(XAResource arg0) throws RollbackException,
			IllegalStateException, SystemException
	{
		throw new UnsupportedOperationException();
	}

	public void registerSynchronization(Synchronization synchronization)
			throws RollbackException, IllegalStateException, SystemException
	{
		throw new UnsupportedOperationException();
	}

	void setTimeout(int timeout)
	{
	}

	void setHibernateTransaction(Transaction transaction)
	{
		if (hibernateTransaction != null)
			throw new BugException("hibernate transaction already set");
		
		this.hibernateTransaction = transaction;
	}
}
