/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.context;

import org.highway.exception.TechnicalException;
import org.highway.transaction.TransactionHome;
import java.security.Principal;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Simple RequestContext implementation based on the SimplePrincipal class.<br>
 * Developers should not use this class.
 */
public class SimpleRequestContext extends RequestContextAbstract
{
	/**
	 * Field principal
	 */
	private Principal principal;

	/**
	 * Constructs a SimpleRequestContext.
	 *
	 * @param userLogin the user login
	 */
	public SimpleRequestContext(String userLogin)
	{
		super(null);
		principal = new SimplePrincipal(userLogin);
	}

	public Principal getCallerPrincipal()
	{
		return principal;
	}

	public UserTransaction getUserTransaction()
	{
		return TransactionHome.getUserTransaction();
	}

	public boolean getRollbackOnly()
	{
		try
		{
			int status = TransactionHome.getStatus();

			return status == Status.STATUS_MARKED_ROLLBACK;
		}
		catch (SystemException exc)
		{
			throw new TechnicalException(
				"Failed to access transaction status", exc);
		}
	}

	public void setRollbackOnly()
	{
		try
		{
			TransactionHome.setRollbackOnly();
		}
		catch (SystemException exc)
		{
			throw new TechnicalException(
				"Failed to mark transaction to rollback", exc);
		}
	}
}
