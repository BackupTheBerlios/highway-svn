/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction.xa;

import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;

/**
 * Interface for something that makes up a transactional resource.
 *
 * @see AbstractXAResource
 * @author Christian de Bevotte
 * @since 1.1
 */
public interface TransactionalResource
{
	/**
	 * Gets the current status of this transactional resource.
	 *
	 * @return the current status of this resource as defined by
	 * {@link Status}.
	 */
	int getStatus();

	/**
	 * Sets the status of this transctional resource.
	 *
	 * @param status the status to be set
	 */
	void setStatus(int status);

	/**
	 * Returns the <tt>Xid</tt> this transactional resource is associated with.
	 * This might have been set in the constructor of implementing classes.
	 *
	 * @return the xid this transactional resource is associated with.
	 */
	Xid getXid();

	/**
	 * Starts work on behalf of this transactional resource.
	 *
	 * @throws XAException an error has occurred.<br>
	 * The error must be described in XA notation.
	 */
	void begin() throws XAException;

	/**
	 * Commits the changes done inside this transactional resource.
	 *
	 * @throws XAException an error has occurred.<br>
	 * The error must be described in XA notation.
	 */
	void commit() throws XAException;

	/**
	 * Prepares the changes done inside this transactional resource. Same
	 * semantics as {@link XAResource.prepare(Xid)}.
	 *
	 * @throws XAException an error has occurred.<br>
	 * The error must be described in XA notation.
	 */
	int prepare() throws XAException;

	/**
	 * Resumes the changes done inside this transactional resource, that has
	 * previously been suspended.
	 *
	 * @throws XAException an error has occurred.<br>
	 * The error must be described in XA notation.
	 */
	void resume() throws XAException;

	/**
	 * Rolls back the changes done inside this transactional resource.
	 *
	 * @throws XAException an error has occurred.<br>
	 * The error must be described in XA notation.
	 */
	void rollback() throws XAException;

	/**
	 * The transaction branch is temporarily suspended in incomplete state.
	 *
	 * @throws XAException an error has occurred.<br>
	 * The error must be described in XA notation.
	 */
	void suspend() throws XAException;
}
