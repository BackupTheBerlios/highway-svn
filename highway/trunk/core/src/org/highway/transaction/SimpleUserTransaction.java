/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @author attias
 */
class SimpleUserTransaction implements UserTransaction
{
	/**
	 * Field transactionManager
	 */
	private TransactionManager transactionManager;

	/**
	 * Constructor for SimpleUserTransaction
	 * @param transactionManager TransactionManager
	 */
	public SimpleUserTransaction(TransactionManager transactionManager)
	{
		this.transactionManager = transactionManager;
	}

	/*
	 * Implements or overrides an already defined method.
	 */

	/**
	 * Method begin
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @see javax.transaction.UserTransaction#begin()
	 */
	public void begin() throws NotSupportedException, SystemException
	{
		transactionManager.begin();
	}

	/*
	 * Implements or overrides an already defined method.
	 */

	/**
	 * Method commit
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws SystemException
	 * @see javax.transaction.UserTransaction#commit()
	 */
	public void commit()
		throws RollbackException, HeuristicMixedException, 
			HeuristicRollbackException, SecurityException, 
			IllegalStateException, SystemException
	{
		transactionManager.commit();
	}

	/*
	 * Implements or overrides an already defined method.
	 */

	/**
	 * Method rollback
	 * @throws IllegalStateException
	 * @throws SecurityException
	 * @throws SystemException
	 * @see javax.transaction.UserTransaction#rollback()
	 */
	public void rollback()
		throws IllegalStateException, SecurityException, SystemException
	{
		transactionManager.rollback();
	}

	/*
	 * Implements or overrides an already defined method.
	 */

	/**
	 * Method setRollbackOnly
	 * @throws IllegalStateException
	 * @throws SystemException
	 * @see javax.transaction.UserTransaction#setRollbackOnly()
	 */
	public void setRollbackOnly() throws IllegalStateException, SystemException
	{
		transactionManager.setRollbackOnly();
	}

	/*
	 * Implements or overrides an already defined method.
	 */

	/**
	 * Method getStatus
	 * @return int
	 * @throws SystemException
	 * @see javax.transaction.UserTransaction#getStatus()
	 */
	public int getStatus() throws SystemException
	{
		return transactionManager.getStatus();
	}

	/*
	 * Implements or overrides an already defined method.
	 */

	/**
	 * Method setTransactionTimeout
	 * @param timeout int
	 * @throws SystemException
	 * @see javax.transaction.UserTransaction#setTransactionTimeout(int)
	 */
	public void setTransactionTimeout(int timeout) throws SystemException
	{
		transactionManager.setTransactionTimeout(timeout);
	}
}
