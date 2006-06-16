/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database;


/**
 * Defines access to a SQL database.<br>
 * Implementation is responsible for access configuration.
 *
 * @author attias
 */
public interface Database
{
	/**
	 * Opens a new database session.
	 *
	 * @return a database session
	 */
	DatabaseSession openSession();
}
