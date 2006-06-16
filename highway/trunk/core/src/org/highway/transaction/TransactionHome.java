/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.init.InitException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * <p>Home class of the transaction package.
 * It provides static methods to set and access the default TransactionManager.
 * Do not instantiate this class.
 * </p>
 * <p>Applications should set the default TransactionManager at init time.
 * Use the <tt>setTransactionManager</tt> to set the default TransactionManager.
 * </p>
 * 
 * @author David Attias
 */
public class TransactionHome
{
	/**
	 * Default TransactionManager.
	 */
	private static TransactionManager transactionManager;

	/**
	 * Do not instantiate this class.
	 */
	private TransactionHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default TransactionManager.
	 * Returns null if no default manager is set.
	 */
	public static TransactionManager getTransactionManager()
	{
		return transactionManager;
	}

	/**
	 * Returns the default TransactionManager.
	 * @throws InitException if no default manager is set.
	 */
	private static TransactionManager getSafeTransactionManager()
	{
		if (transactionManager == null)
		{
			throw new InitException("no default TransactionManager set");
		}

		return transactionManager;
	}

	/**
	 * Sets the default TransactionManager.
	 */
	public static synchronized void setTransactionManager(
		TransactionManager manager)
	{
		transactionManager = manager;
		DebugHome.getDebugLog().info(
			"Default TransactionManager is set to " + manager);
	}

	/**
	 * Returns a UserTransaction.
	 * @throws InitException if no default TransactionManager is set
	 */
	public static UserTransaction getUserTransaction()
	{
		return new SimpleUserTransaction(getSafeTransactionManager());
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#begin()
	 */
	public static void begin() throws NotSupportedException, SystemException
	{
		getSafeTransactionManager().begin();
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#commit()
	 */
	public static void commit()
		throws RollbackException, HeuristicMixedException, 
			HeuristicRollbackException, SecurityException, 
			IllegalStateException, SystemException
	{
		getSafeTransactionManager().commit();
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#getStatus()
	 */
	public static int getStatus() throws SystemException
	{
		return getSafeTransactionManager().getStatus();
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#getTransaction()
	 */
	public static Transaction getTransaction() throws SystemException
	{
		return getSafeTransactionManager().getTransaction();
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#resume(javax.transaction.Transaction)
	 */
	public static void resume(Transaction transaction)
		throws InvalidTransactionException, IllegalStateException, 
			SystemException
	{
		getSafeTransactionManager().resume(transaction);
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#rollback()
	 */
	public static void rollback()
		throws IllegalStateException, SecurityException, SystemException
	{
		getSafeTransactionManager().rollback();
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#setRollbackOnly()
	 */
	public static void setRollbackOnly()
		throws IllegalStateException, SystemException
	{
		getSafeTransactionManager().setRollbackOnly();
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#setTransactionTimeout(int)
	 */
	public static void setTransactionTimeout(int timeout)
		throws SystemException
	{
		getSafeTransactionManager().setTransactionTimeout(timeout);
	}

	/**
	 * Delegates to the default TransactionManager.
	 *
	 * @throws InitException if no default TransactionManager is set
	 * @see javax.transaction.TransactionManager#suspend()
	 */
	public static Transaction suspend() throws SystemException
	{
		return getSafeTransactionManager().suspend();
	}
}
