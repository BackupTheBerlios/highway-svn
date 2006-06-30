/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;


/**
 * Complex identifier generation interface.<br>
 * The type of generated identifiers is specific to each implementation.<br>
 * Different instances can be used at the same time.<br>
 * The default complex generator must be set and used through the <code>IdgenHome</code>
 * class.
 *
 * 
 */
public interface ComplexIdGenerator
{
	/**
	 * Returns the next unique complex identifier of this generator.<br>
	 * This method must be equivalent to <code>getNextId(null)</code>.
	 *
	 * @return a complex identifier
	 */
	public Object getNextId();

	/**
	 * Returns the next unique complex identifier of this generator.
	 *
	 * @param argument argument specific to implementation
	 * @return a complex identifier
	 */
	public Object getNextId(Object argument);
}
