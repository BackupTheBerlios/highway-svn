/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;


/**
 * Simple identifier generation interface.<br>
 * Simple generators returns long identifiers.<br>
 * The default simple generator must be set and used through the <code>IdgenHome</code> class.
 *
 * 
 */
public interface SimpleIdGenerator
{
	/**
	 * Returns the next simple identifier of this generator.<br>
	 * This method must be equivalent to <code>getNextId(null)</code>.
	 *
	 * @return an identifier
	 */
	public long getNextId();

	/**
	 * Returns the next simple identifier of this generator.
	 *
	 * @param argument argument specific to implementation
	 * @return an identifier
	 */
	public long getNextId(Object argument);
}
