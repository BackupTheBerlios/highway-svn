/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;

import java.io.Serializable;

/**
 * Page of available unique ids
 *
 * 
 *
 */
public class IdPage implements Serializable
{
	private long nextId;
	private long lastId;

	/**
	 * Constructs a <tt>IdPage</tt> with the specified first and last ids.
	 *
	 * @param firstId first id to be available
	 * @param lastId last id to be available
	 */
	public IdPage(long firstId, long lastId)
	{
		this.nextId = firstId;
		this.lastId = lastId;
	}

	/**
	 * Indicates if the IdPage has any more available id
	 *
	 * @return true or false
	 */
	public boolean hasNext()
	{
		return nextId <= lastId;
	}

	/**
	 * Provides the next available id
	 *
	 * @throws IllegalStateException if the <tt>IdPage</tt> is empty
	 * @return next available id
	 */
	public long next()
	{
		if (hasNext())
		{
			return nextId++;
		}

		throw new IllegalStateException("This page of id is empty");
	}

	/**
	 * Converts to String
	 */
	public String toString()
	{
		return "[IdPage, nextId=" + nextId + ", lastId=" + lastId + ']';
	}
}
