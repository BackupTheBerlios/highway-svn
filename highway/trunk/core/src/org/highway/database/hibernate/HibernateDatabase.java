/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.exception.TechnicalException;


/**
 * Implementation of Database interface to use the Hibernate persistance
 * framework.
 *
 * 
 */
public class HibernateDatabase implements Database
{
	/**
	 * Hibernate session factory.
	 */
	private final SessionFactory factory;

	/**
	 * Constructs a HibernateDatabase object.
	 *
	 * @param configuration an Hibernate Configuration instance
	 * 
	 */
	public HibernateDatabase(Configuration configuration)
	{
		try
		{
			factory = configuration.buildSessionFactory();
		}
		catch (Exception exc)
		{
			throw new TechnicalException("Failed to configure Hibernate", exc);
		}
	}

	/**
	 * Opens and returns a new database session based on the Hibernate
	 * persistance framework.
	 *
	 * @return a database session using the Hibernate framework
	 */
	public DatabaseSession openSession()
	{
		try
		{
			return new HibernateSession(this, factory.openSession());
		}
		catch (HibernateException exc)
		{
			throw new TechnicalException(
				"Failed to open new Hibernate database session", exc);
		}
	}
}
