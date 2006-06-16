/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction.xa;

import javax.transaction.Status;
import javax.transaction.xa.Xid;

/**
 * Rudimentary abstract implementation of {@link TransactionalResource} for
 * specific implementations to base upon.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public abstract class AbstractTransactionalResource
	implements TransactionalResource, Status
{
	/**
	 * The current status of this transactional resource as defined by
	 * {@link Status}.
	 */
	protected int status;

	/**
	 * The <tt>Xid</tt> this transctional resource is associated with.
	 */
	protected Xid xid;

	/**
	 * Default constructor.
	 *
	 * @param xid The <tt>Xid</tt> this transctional resource is associated
	 * with.
	 */
	public AbstractTransactionalResource(Xid xid)
	{
		this.xid = xid;
		status = STATUS_ACTIVE;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Xid getXid()
	{
		return xid;
	}
}
