/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.exception.TechnicalException;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * Implementation of Database interface to use the Hibernate persistance
 * framework.
 *
 * @author attias
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
	 * @param resource path of the Hibernate properties file in the classpath
	 * @deprecated replaced by {@link #HibernateDatabase(net.sf.hibernate.cfg.Configuration)}
	 */
	public HibernateDatabase(String resource)
	{
		try
		{
			factory =
				new Configuration().configure(resource).buildSessionFactory();
		}
		catch (Exception exc)
		{
			throw new TechnicalException(
				"Failed to configure Hibernate with resource file " + resource,
				exc);
		}
	}

	/**
	 * Constructs a HibernateDatabase object.
	 *
	 * @param configuration an Hibernate Configuration instance
	 * @since 1.1
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
