/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.domain;

import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.init.InitException;
import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextHome;

/**
 * Superclass of access services component implementation. Usage is optional. It
 * provides easy management of database sessions.
 * @author attias
 */
public class AccessImplAbstract
{
	/**
	 * Field SESSION_CONTEXT_KEY
	 */
	private static final Object SESSION_CONTEXT_KEY = new Object();

	/**
	 * Field database
	 */
	private static Database database;

	/**
	 * Sets the default Database instance to use in all the access components.
	 * @param database the Database instance to use as default
	 */
	public static void setDatabase(Database database)
	{
		AccessImplAbstract.database = database;
	}

	/**
	 * Returns the DatabaseSession associated with the current service request.<br>
	 * If no session is open yet, open a new session from the default Database
	 * instance and tie it to the current service request. The session will be
	 * available to all the access components that extend this class through
	 * this method.
	 * @return the current database session
	 * @throws IllegalStateException if no request context is set
	 * @throws InitException if no default database is set
	 */
	protected DatabaseSession getSession()
		throws IllegalStateException, InitException
	{
		RequestContext context = RequestContextHome.getRequestContext();

		if (context == null)
		{
			throw new IllegalStateException("No context found");
		}

		DatabaseSession session =
			(DatabaseSession) context.getResource(SESSION_CONTEXT_KEY);

		if (session == null)
		{
			if (database == null)
			{
				throw new InitException("No database set");
			}

			session = database.openSession();
			context.setResource(SESSION_CONTEXT_KEY, session);
		}

		return session;
	}
}
