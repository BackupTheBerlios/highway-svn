/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.highway.bean.ValueObject;
import org.highway.bean.ValueObjectHelper;
import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.database.SelectQuery;
import org.highway.exception.TechnicalException;
import org.highway.helper.CollectionHelper;

/**
 * Implementation of the DatabaseSession interface for Hibernate.
 */
class HibernateSession implements DatabaseSession
{
	/**
	 * The real Hibernate session.
	 */
	private Session session;

	/**
	 * The Hibernate database.
	 */
	private HibernateDatabase database;

	/**
	 * Constructs a HibernateSession object.
	 * 
	 * @param session the real Hibernate session
	 */
	HibernateSession(HibernateDatabase database, Session session)
	{
		if (database == null)
		{
			throw new IllegalArgumentException("Hibernate database is null");
		}

		if (session == null)
		{
			throw new IllegalArgumentException("Hibernate session is null");
		}

		this.database = database;
		this.session = session;

		/*
		 * The SimpleHibernateTransactionManager can be used when no transaction
		 * manager is available in a development environment. It needs the
		 * hibernate session to create and manage hibernate transactions. If the
		 * SimpleHibernateTransactionManager is not transaction manager set in
		 * the transaction home, this method does nothing.
		 */
		SimpleHibernateTransactionManager.setCurrentSession(session);
	}

	public Database getDatabase()
	{
		return this.database;
	}

	public Connection getConnection()
	{
		try
		{
			return session.connection();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public Object select(Object object)
	{
		if (object == null)
		{
			return null;
		}

		return select(object.getClass(), object);
	}

	public Object select(Class type, long id)
	{
		return select(type, new Long(id));
	}

	public Object select(Class type, Object id)
	{
		checkIfClosed();

		if (!(id instanceof Serializable))
		{
			throw new IllegalArgumentException(
					"Hibernate forces identifiers to be serializable");
		}

		try
		{
			// we use get instead of load to return null if not found
			Object object = session.get(type, (Serializable) id);
			ValueObjectHelper.setSaved(object);

			// evict => changes are not automaticaly saved
			evict(object);

			return object;
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public List select(List objects)
	{
		if (!CollectionHelper.isNullOrEmpty(objects))
		{
			for (int i = 0; i < objects.size(); i++)
			{
				// select each object to load its properties
				Object object = select(objects.get(i));

				// if object not found, replace it by null in the list
				if (object == null)
				{
					objects.set(i, null);
				}
			}
		}

		return objects;
	}

	public List select(Class type, Object[] ids)
	{
		ArrayList objects = new ArrayList(ids.length);

		for (int i = 0; i < ids.length; i++)
		{
			objects.set(i, select(type, ids[i]));
		}

		return objects;
	}

	public List select(String query)
	{
		checkIfClosed();

		try
		{
			List result = session.createQuery(query).list();

			ValueObjectHelper.setSaved(result);
			session.clear();

			return result;
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public List select(String query, Object parameter)
	{
		checkIfClosed();

		try
		{
			List result = session.createQuery(query).setParameter(
					parameter.toString(),
					HibernateTypes.getParameterType(parameter)).list();
			ValueObjectHelper.setSaved(result);
			session.clear();

			return result;
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public List select(String query, Object[] parameters)
	{
		checkIfClosed();

		try
		{
			List result = session.createQuery(query).setParameters(parameters,
					HibernateTypes.getParameterTypes(parameters)).list();
			ValueObjectHelper.setSaved(result);
			session.clear();

			return result;
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public SelectQuery createSelectQuery()
	{
		checkIfClosed();

		return new HibernateSelectQuery(this);
	}

	public void insert(Object object)
	{
		checkIfClosed();

		try
		{
			session.save(object);
			session.flush();
			ValueObjectHelper.setSaved(object);
			session.evict(object);
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void insert(Object[] objects)
	{
		checkIfClosed();

		try
		{
			for (int i = 0; i < objects.length; i++)
			{
				session.save(objects[i]);
			}

			session.flush();
			ValueObjectHelper.setSaved(objects);
			session.clear();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void insertOrUpdate(Object object)
	{
		checkIfClosed();

		try
		{
			insertOrUpdate0(object);
			session.flush();
			session.evict(object);
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void insertOrUpdate(Object[] objects)
	{
		checkIfClosed();

		try
		{
			for (int i = 0; i < objects.length; i++)
			{
				insertOrUpdate0(objects[i]);
			}

			session.flush();
			session.clear();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	private void insertOrUpdate0(Object object) throws HibernateException
	{
		if (object instanceof ValueObject)
		{
			ValueObject vo = (ValueObject) object;

			if (vo.isNew())
			{
				session.save(vo);
			}
			else if (vo.isDirty())
			{
				session.update(vo);
			}

			vo.setSaved();
		}
		else
		{
			session.saveOrUpdate(object);
		}
	}

	public void update(Object object)
	{
		checkIfClosed();

		try
		{
			session.update(object);
			session.flush();
			ValueObjectHelper.setSaved(object);
			session.evict(object);
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void update(Object[] objects)
	{
		checkIfClosed();

		try
		{
			for (int i = 0; i < objects.length; i++)
			{
				session.update(objects[i]);
			}

			session.flush();

			for (int i = 0; i < objects.length; i++)
			{
				ValueObjectHelper.setSaved(objects[i]);
			}

			session.clear();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void update(String query)
	{
		throw new UnsupportedOperationException();
	}

	public void update(String query, Object parameter)
	{
		throw new UnsupportedOperationException();
	}

	public void update(String query, Object[] parameters)
	{
		throw new UnsupportedOperationException();
	}

	public void delete(Class type, Object id)
	{
		checkIfClosed();

		try
		{
			if (!(id instanceof Serializable))
			{
				throw new IllegalArgumentException(
						"Hibernate forces identifiers to be serializable");
			}

			Object object = session.load(type, (Serializable) id);
			session.delete(object);
			session.flush();
			session.evict(object);
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void delete(Class type, Object[] ids)
	{
		checkIfClosed();

		try
		{
			for (int i = 0; i < ids.length; i++)
			{
				if (!(ids[i] instanceof Serializable))
				{
					throw new IllegalArgumentException(
							"Hibernate forces identifiers to be serializable");
				}

				session.delete(session.load(type, (Serializable) ids[i]));
			}

			session.flush();
			session.clear();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void delete(Object object)
	{
		checkIfClosed();

		try
		{
			session.delete(object);
			session.flush();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void delete(Object[] objects)
	{
		checkIfClosed();

		try
		{
			for (int i = 0; i < objects.length; i++)
			{
				session.delete(objects[i]);
			}

			session.flush();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void delete(String query)
	{
		checkIfClosed();

		try
		{
			session.delete(query);
			session.flush();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void delete(String queryString, Object parameter)
	{
		checkIfClosed();

		try
		{
			Query query = session.createQuery(queryString);
			query.setParameter(0, parameter, HibernateTypes
					.getParameterType(parameter));
			query.executeUpdate();
			session.flush();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void delete(String queryString, Object[] parameters)
	{
		checkIfClosed();

		try
		{
			Query query = session.createQuery(queryString);
			query.setParameters(parameters, HibernateTypes
					.getParameterTypes(parameters));
			query.executeUpdate();
			session.flush();
		}
		catch (HibernateException e)
		{
			throw new TechnicalException(e);
		}
	}

	public void close()
	{
		try
		{
			session.close();
			session = null;
		}
		catch (HibernateException exc)
		{
			throw new TechnicalException("Failed to close hibernate session",
					exc);
		}
	}

	Session getSession()
	{
		return session;
	}

	/**
	 * Evicts the specified persistent object from the session cache.<br>
	 * Does nothing if the specified object is null.
	 * 
	 * @param object the object to evict
	 */
	void evict(Object object) throws HibernateException
	{
		if (object == null)
		{
			return;
		}

		session.evict(object);
	}

	/**
	 * @throws IllegalStateException
	 */
	private void checkIfClosed() throws IllegalStateException
	{
		if (session == null)
		{
			throw new IllegalStateException("Hibernate session is closed");
		}
	}
}
