/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction.xa;

import org.highway.debug.DebugHome;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Status;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 * Abstract <tt>XAResource</tt> doing all the tedious tasks shared by many
 * <tt>XAResouce</tt> implementations.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public abstract class AbstractXAResource implements XAResource, Status
{
	private Map transactionBranches = new HashMap();

	/**
	 * Commits the transaction branch specified by xid.
	 *
	 * @param xid a transaction identifier.
	 * @param onePhase if <tt>true</tt>, the resource manager should use a
	 * one-phase commit protocol to commit the work done on behalf of xid.
	 * @throws XAException an error has occurred.
	 */
	public void commit(Xid xid, boolean onePhase) throws XAException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("xid", xid);
		DebugHome.getDebugLog().debugValue("onePhase", onePhase);

		TransactionalResource transactionBranch = getTransactionBranch(xid);

		if (transactionBranch == null)
		{
			// The XID is not valid
			throw new XAException(XAException.XAER_NOTA);
		}

		if (transactionBranch.getStatus() == STATUS_MARKED_ROLLBACK)
		{
			// Indicates that the rollback was caused by an unspecified reason
			throw new XAException(XAException.XA_RBROLLBACK);
		}

		if (transactionBranch.getStatus() != STATUS_PREPARED)
		{
			if (onePhase)
			{
				transactionBranch.prepare();
			}
			else
			{
				// Routine was invoked in an improper context
				throw new XAException(XAException.XAER_PROTO);
			}
		}

		transactionBranch.commit();
		removeTransactionBranch(xid);
	}

	/**
	 * Ends the work performed on behalf of a transaction branch.
	 *
	 * @param xid a transaction identifier.
	 * @param flags one of <tt>TMSUCCESS</tt>, <tt>TMFAIL</tt>, or <tt>TMSUSPEND</tt>.
	 * @throws XAException an error has occurred.
	 */
	public void end(Xid xid, int flags) throws XAException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("xid", xid);
		DebugHome.getDebugLog().debugValue(
			"flags",
			(flags == TMSUCCESS) ? "TMSUCCESS"
								 : ((flags == TMFAIL) ? "TMFAIL"
													  : ((flags == TMSUSPEND)
			? "TMSUSPEND" : "unknown")));

		TransactionalResource transactionBranch = getTransactionBranch(xid);

		if (transactionBranch == null)
		{
			// The XID is not valid
			throw new XAException(XAException.XAER_NOTA);
		}

		switch (flags)
		{
			case TMSUSPEND :
				transactionBranch.suspend();

				break;

			case TMFAIL :
				transactionBranch.setStatus(STATUS_MARKED_ROLLBACK);

				break;

			case TMSUCCESS :
				break;

			default :
				throw new XAException("Invalid flags : " + flags);
		}
	}

	/**
	 * Tells the resource manager to forget about a heuristically completed
	 * transaction branch.
	 *
	 * @param xid a transaction identifier.
	 * @throws XAException an error has occurred.
	 */
	public void forget(Xid xid) throws XAException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("xid", xid);

		TransactionalResource transactionBranch = getTransactionBranch(xid);

		if (transactionBranch == null)
		{
			// The XID is not valid
			throw new XAException(XAException.XAER_NOTA);
		}

		removeTransactionBranch(xid);
	}

	/**
	 * Asks the resource manager to prepare for a transaction commit of the
	 * transaction specified in xid.
	 *
	 * @param xid a transaction identifier.
	 * @return a value indicating the resource manager's vote on the outcome of
	 * the transaction.
	 * @throws XAException an error has occurred.
	 */
	public int prepare(Xid xid) throws XAException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("xid", xid);

		TransactionalResource transactionBranch = getTransactionBranch(xid);

		if (transactionBranch == null)
		{
			// The XID is not valid
			throw new XAException(XAException.XAER_NOTA);
		}

		if (transactionBranch.getStatus() == STATUS_MARKED_ROLLBACK)
		{
			// Indicates that the rollback was caused by an unspecified reason
			throw new XAException(XAException.XA_RBROLLBACK);
		}

		int result = transactionBranch.prepare();
		transactionBranch.setStatus(STATUS_PREPARED);

		return result;
	}

	/**
	 * Informs the resource manager to roll back work done on behalf of a
	 * transaction branch.
	 *
	 * @param xid a transaction identifier.
	 * @throws XAException an error has occurred.
	 */
	public void rollback(Xid xid) throws XAException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("xid", xid);

		TransactionalResource transactionBranch = getTransactionBranch(xid);

		if (transactionBranch == null)
		{
			// The XID is not valid
			throw new XAException(XAException.XAER_NOTA);
		}

		transactionBranch.rollback();
		removeTransactionBranch(xid);
	}

	/**
	 * Starts work on behalf of a transaction branch specified in xid.
	 *
	 * @param xid a transaction identifier.
	 * @param flags one of <tt>TMNOFLAGS</tt>, <tt>TMJOIN</tt> or <tt>TMRESUME</tt>.
	 * @throws XAException an error has occurred.
	 */
	public void start(Xid xid, int flags) throws XAException
	{
		DebugHome.getDebugLog().debugEnter();
		DebugHome.getDebugLog().debugValue("xid", xid);
		DebugHome.getDebugLog().debugValue(
			"flags",
			(flags == TMNOFLAGS) ? "TMNOFLAGS"
								 : ((flags == TMJOIN) ? "TMJOIN"
													  : ((flags == TMRESUME)
			? "TMRESUME" : "unknown")));

		TransactionalResource transactionBranch = null;

		switch (flags)
		{
			case TMNOFLAGS :

				if (getTransactionBranch(xid) != null)
				{
					// The XID already exists
					throw new XAException(XAException.XAER_DUPID);
				}

				try
				{
					transactionBranch = createTransactionBranch(xid);
					transactionBranch.begin();
					addTransactionBranch(xid, transactionBranch);
				}
				catch (Exception e)
				{
					DebugHome.getDebugLog().error(
						"Could not create transaction branch", e);
					throw new XAException(e.getMessage());
				}

				break;

			case TMJOIN :
				transactionBranch = getTransactionBranch(xid);

				if (transactionBranch == null)
				{
					// The XID is not valid
					throw new XAException(XAException.XAER_NOTA);
				}

				break;

			case TMRESUME :
				transactionBranch = getTransactionBranch(xid);

				if (transactionBranch == null)
				{
					// The XID is not valid
					throw new XAException(XAException.XAER_NOTA);
				}

				transactionBranch.resume();

				break;

			default :
				throw new XAException("Invalid flags : " + flags);
		}
	}

	/**
	 * Gets a <tt>TransactionalResource</tt> instance.
	 *
	 * @param xid a transaction identifier.
	 * @return a <tt>TransactionalResource</tt> instance.
	 */
	protected TransactionalResource getTransactionBranch(Xid xid)
	{
		return (TransactionalResource) transactionBranches.get(xid);
	}

	/**
	 * Adds a <tt>TransactionalResource</tt> instance.
	 *
	 * @param xid a transaction identifier.
	 * @param transactionBranch a <tt>TransactionalResource</tt> instance.
	 */
	protected void addTransactionBranch(
		Xid xid, TransactionalResource transactionBranch)
	{
		transactionBranches.put(xid, transactionBranch);
	}

	/**
	 * Removes a <tt>TransactionalResource</tt> instance.
	 *
	 * @param xid a transaction identifier.
	 */
	protected void removeTransactionBranch(Xid xid)
	{
		transactionBranches.remove(xid);
	}

	/**
	 * Creates a <tt>TransactionalResource</tt> instance.
	 *
	 * @param xid a transaction identifier.
	 * @return a <tt>TransactionalResource</tt> instance.
	 * @throws Exception an error has occurred.
	 */
	protected abstract TransactionalResource createTransactionBranch(Xid xid)
		throws Exception;
}
