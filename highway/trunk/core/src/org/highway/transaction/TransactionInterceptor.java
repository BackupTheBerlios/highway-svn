package org.highway.transaction;

import java.lang.reflect.Method;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionRequiredException;

import org.highway.annotation.TransactionOption;
import org.highway.annotation.TransactionTimeout;
import org.highway.annotation.TransactionOption.TransactionOptions;
import org.highway.debug.DebugHome;
import org.highway.exception.TechnicalException;
import org.highway.helper.MethodHelper;
import org.highway.service.ServiceInterceptor;
import org.highway.service.ServiceRequest;


public class TransactionInterceptor implements ServiceInterceptor
{
	/**
	 * Method invoke
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 * @see org.highway.service.ServiceInterceptor#invoke(ServiceRequest)
	 */
	public Object invoke(ServiceRequest request) throws Throwable
	{
		TransactionOptions option = getTransactionOption(request.getMethod());

		if (option == null)
		{
			throw new TechnicalException(
				"No transaction option found for service "
				+ MethodHelper.getClassAndMethodName(request.getMethod(), true));
		}

		if (option.equals(TransactionOptions.REQUIRED))
		{
			return invokeRequired(request);
		}

		if (option.equals(TransactionOptions.REQUIRES_NEW))
		{
			return invokeRequiresNew(request);
		}

		if (option.equals(TransactionOptions.SUPPORTS))
		{
			return invokeSupports(request);
		}

		if (option.equals(TransactionOptions.NOT_SUPPORTED))
		{
			return invokeNotSupported(request);
		}

		if (option.equals(TransactionOptions.NEVER))
		{
			return invokeNever(request);
		}

		if (option.equals(TransactionOptions.MANDATORY))
		{
			return invokeMandatory(request);
		}

		throw new TechnicalException(
			"Invalid service transaction option, option = " + option
			+ " service = "
			+ MethodHelper.getClassAndMethodName(request.getMethod(), true));
	}

	/**
	 * Method invokeSupports
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeSupports(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();

		return request.invoke();
	}

	/**
	 * Method invokeRequired
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeRequired(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();

		if (getStatus() == Status.STATUS_NO_TRANSACTION)
		{
			return invokeInNewTransaction(request);
		}
		else
		{
			return invokeSupports(request);
		}
	}

	/**
	 * Method invokeMandatory
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeMandatory(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();

		if (getStatus() == Status.STATUS_NO_TRANSACTION)
		{
			throw new TransactionRequiredException(
				"Transaction context mandatory for service "
				+ MethodHelper.getClassAndMethodName(request.getMethod(), true));
		}

		return invokeSupports(request);
	}

	/**
	 * Method invokeNever
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeNever(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();

		if (getStatus() != Status.STATUS_NO_TRANSACTION)
		{
			throw new TechnicalException(
				"Transaction context is forbidden for service "
				+ MethodHelper.getClassAndMethodName(request.getMethod(), true));
		}

		return invokeNotSupported(request);
	}

	/**
	 * Method invokeNotSupported
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeNotSupported(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();

		Transaction callingTransaction = null;

		if (getStatus() != Status.STATUS_NO_TRANSACTION)
		{
			callingTransaction = suspend();
		}

		try
		{
			return invokeSupports(request);
		}
		finally
		{
			resume(callingTransaction);
		}
	}

	/**
	 * Method invokeRequiresNew
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeRequiresNew(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();

		Transaction callingTransaction = null;

		if (getStatus() != Status.STATUS_NO_TRANSACTION)
		{
			callingTransaction = suspend();
		}

		try
		{
			return invokeInNewTransaction(request);
		}
		finally
		{
			resume(callingTransaction);
		}
	}

	/**
	 * Method invokeInNewTransaction
	 * @param request ServiceRequest
	 * @return Object
	 * @throws Throwable
	 */
	private Object invokeInNewTransaction(ServiceRequest request)
		throws Throwable
	{
		DebugHome.getDebugLog().debugEnter();
		setTransactionTimeout(request);
		begin();

		try
		{
			return request.invoke();
		}
		catch (Throwable throwable)
		{
			boolean declared = false;
			Class[] declaredExceptionTypes =
				request.getMethod().getExceptionTypes();

			for (int i = 0; i < declaredExceptionTypes.length; i++)
			{
				if (throwable.getClass().equals(declaredExceptionTypes[i]))
				{
					declared = true;

					break;
				}
			}

			if (! declared)
			{
				setRollbackOnly();
			}

			throw throwable;
		}
		finally
		{
			commit();
		}
	}

	/**
	 * Method getStatus
	 * @return int
	 */
	private int getStatus()
	{
		try
		{
			return TransactionHome.getStatus();
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method setTransactionTimeout
	 * @param request ServiceRequest
	 */
	private void setTransactionTimeout(ServiceRequest request)
	{
		int timeout= request.getMethod().getAnnotation(TransactionTimeout.class).value();

		try
		{
			TransactionHome.setTransactionTimeout(timeout);
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method begin
	 */
	private void begin()
	{
		DebugHome.getDebugLog().debugEnter();

		try
		{
			TransactionHome.begin();
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (NotSupportedException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method suspend
	 * @return Transaction
	 */
	private Transaction suspend()
	{
		DebugHome.getDebugLog().debugEnter();

		try
		{
			return TransactionHome.suspend();
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * @todo (attias) pass eventualy the technical exceptions already thrown
	 * @param callingTransaction Transaction
	 */
	private void resume(Transaction callingTransaction)
	{
		DebugHome.getDebugLog().debugEnter();

		try
		{
			if (callingTransaction != null)
			{
				TransactionHome.resume(callingTransaction);
			}
		}
		catch (InvalidTransactionException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (IllegalStateException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method commit
	 */
	private void commit()
	{
		DebugHome.getDebugLog().debugEnter();

		boolean marked = (getStatus() == Status.STATUS_MARKED_ROLLBACK);

		try
		{
			TransactionHome.commit();
		}
		catch (SecurityException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (IllegalStateException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (RollbackException exc)
		{
			// throw this exception only if the transaction
			// has not been marked for rollback
			if (! marked)
			{
				throw new TechnicalException(exc);
			}
		}
		catch (HeuristicMixedException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (HeuristicRollbackException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method setRollbackOnly
	 */
	private void setRollbackOnly()
	{
		try
		{
			TransactionHome.setRollbackOnly();
		}
		catch (SystemException exc)
		{
			// @todo (attias) change to the right exc to throw
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method getTransactionOption
	 * @param method Method
	 * @return String
	 */
	private TransactionOptions getTransactionOption(Method method)
	{
		return method.getAnnotation(TransactionOption.class).value();
	}
}
