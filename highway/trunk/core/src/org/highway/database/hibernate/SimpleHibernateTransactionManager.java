package org.highway.database.hibernate;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.hibernate.Transaction;

public class SimpleHibernateTransactionManager implements TransactionManager
{
	// private static final ThreadLocal<Session> localSession = new
	// ThreadLocal<Session>();

	private static final ThreadLocal<Transaction> localTransaction = new ThreadLocal<Transaction>();

	private static final ThreadLocal<Boolean> localSetRollbackOnly = new ThreadLocal<Boolean>();

	// static void registerSession(Session session)
	// {
	// Object registered = localSession.get();
	//
	// if (registered != null)
	// throw new UnsupportedOperationException(
	// "more than one hibernate session per request is not supported yet");
	//
	// localSession.set(session);
	//
	// localTransaction.set(session.beginTransaction());
	// }
	//
	// static void unregisterSession(Session session)
	// {
	// Session registered = localSession.get();
	//
	// if (registered == null || registered != session)
	// throw new IllegalStateException(
	// "the specified session wasn't registered");
	//
	// localSession.set(null);
	// }

	public void begin() throws NotSupportedException, SystemException
	{
	}

	public void commit() throws RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException
	{
		try
		{
			if (localSetRollbackOnly.get() == null)
				localTransaction.get().commit();
			else
				localTransaction.get().rollback();			
		}
		finally
		{
			clean();
		}
	}

	private void clean()
	{
		localSetRollbackOnly.set(null);
		localTransaction.set(null);
	}

	public int getStatus() throws SystemException
	{
		throw new UnsupportedOperationException(
				"simple hibernate transactions are not JTA compatible");
	}

	public javax.transaction.Transaction getTransaction()
			throws SystemException
	{
		throw new UnsupportedOperationException(
				"simple hibernate transactions are not JTA compatible");
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
			localTransaction.get().rollback();			
		}
		finally
		{
			clean();
		}
	}

	public void setRollbackOnly() throws IllegalStateException, SystemException
	{
		localSetRollbackOnly.set(Boolean.TRUE);
	}

	/**
	 * This transaction manager does not support transaction timeout.
	 */
	public void setTransactionTimeout(int arg0) throws SystemException
	{
		localTransaction.get();
	}

	public javax.transaction.Transaction suspend() throws SystemException
	{
		return null;
	}

	void registerHibernateTransaction(Transaction transaction)
	{
		localTransaction.set(transaction);
	}
}

/**
 * Use the following registerSession, unregisterSession and getLocalSessions
 * implementations can be used in a multiple session env
 */

// static void registerSession(Session session)
// {
// Object object = localSessions.get();
//
// if (object == null)
// {
// localSessions.set(session);
// return;
// }
//
// if (object == session) return;
//
// if (object instanceof Session)
// {
// Set sessionSet = new HashSet();
// sessionSet.add(object);
// sessionSet.add(session);
// localSessions.set(sessionSet);
// return;
// }
//
// if (object instanceof Set)
// {
// Set sessionSet = (Set) object;
// sessionSet.add(session);
// return;
// }
//
// throw new BugException("invalid thread local content = " + object);
// }
//
// static void unregisterSession(Session session)
// {
// Object object = localSessions.get();
//
// if (object == null) return;
//
// if (object == session)
// {
// localSessions.set(null);
// return;
// }
//
// if (object instanceof Session) return;
//
// if (object instanceof Set)
// {
// Set sessionSet = (Set) object;
// sessionSet.remove(session);
// return;
// }
//
// throw new BugException("invalid thread local content: " + object);
// }
//
// private Iterator getLocalSessions()
// {
// Object object = localSessions.get();
//	
// if (object == null) return Collections.EMPTY_LIST.iterator();
//	
// if (object instanceof Session)
// return Collections.singleton(object).iterator();
//	
// if (object instanceof Set) return ((Set) object).iterator();
//	
// throw new BugException("invalid thread local content: " + object);
// }
//
