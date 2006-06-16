/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.lifecycle;


/**
 * Defines an object that can be closed.
 *
 * @author attias
 */
public interface Closeable
{
	/**
	 * Closes this object.
	 */
	void close();
}
