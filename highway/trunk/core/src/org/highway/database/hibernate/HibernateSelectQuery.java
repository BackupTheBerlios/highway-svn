/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database.hibernate;

import org.highway.bean.ValueObjectHelper;
import org.highway.database.SelectQuery;
import org.highway.database.TooManyResultsExeption;
import org.highway.exception.TechnicalException;
import org.highway.helper.CollectionHelper;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of <tt>SelectQuery</tt> interface to use the Hibernate
 * persistance framework.
 *
 * 
 * 
 */
class HibernateSelectQuery implements SelectQuery
{
	private int max;
	private int first;
	private boolean check;
	private List parameters;
	private boolean returnIdOnly;
	private StringBuffer buffer = new StringBuffer();
	private HibernateSession session;

	/**
	 * Default constructor.
	 *
	 * @param session an <tt>HibernateSession</tt> instance.
	 */
	HibernateSelectQuery(HibernateSession session)
	{
		this.session = session;
	}

	public String getQueryText()
	{
		return buffer.toString();
	}

	public void addQueryText(String text)
	{
		buffer.append(text);
	}

	public void setParameters(List parameters)
	{
		this.parameters = parameters;
	}

	public void setFetchMax(int max)
	{
		this.max = max;
	}

	public void setFetchFirst(int first)
	{
		this.first = first;
	}

	public void setCheckTooManyResults(boolean check)
	{
		this.check = check;
	}

	public void setReturnIdOnly(boolean returnId)
	{
		this.returnIdOnly = returnId;
	}

	public Object getUnique() throws TooManyResultsExeption
	{
		setFetchMax(1);
		setFetchFirst(0);
		setCheckTooManyResults(true);

		List list = list();

		return list.isEmpty() ? null : list.get(0);
	}

	public List list() throws TooManyResultsExeption
	{
		try
		{
			Query query = createQuery();

			if (max > 0)
			{
				query.setMaxResults(check ? (max + 1) : max);
			}

			List list = query.list();

			if ((max > 0) && check && (list.size() == (max + 1)))
			{
				throw new TooManyResultsExeption();
			}

			if (returnIdOnly)
			{
				list = extractIdentifiers(list);
			}
			else
			{
				ValueObjectHelper.setSaved(list);
			}

			session.getSession().clear();

			return list;
		}
		catch (HibernateException exc)
		{
			throw new TechnicalException(exc);
		}
		finally
		{
			close();
		}
	}

	public Iterator iterate()
	{
		try
		{
			Query query = createQuery();

			if (max > 0)
			{
				query.setMaxResults(max);
			}

			return new HibernateIterator(query.iterate());
		}
		catch (HibernateException exc)
		{
			throw new TechnicalException(exc);
		}
		finally
		{
			close();
		}
	}

	public void close()
	{
	}

	/**
	 * Returns the <tt>HibernateSession</tt> instance.
	 *
	 * @return the <tt>HibernateSession</tt> instance.
	 */
	HibernateSession getSession()
	{
		return session;
	}

	private Query createQuery() throws HibernateException
	{
		Query query = session.getSession().createQuery(getQueryText());

		query.setFirstResult(first);

		if (! CollectionHelper.isNullOrEmpty(parameters))
		{
			for (int i = 0; i < parameters.size(); i++)
			{
				query.setParameter(i, parameters.get(i));
			}
		}

		return query;
	}

	private List extractIdentifiers(List list) throws HibernateException
	{
		if (list == null)
		{
			return null;
		}

		List identifiers = new ArrayList(list.size());

		for (Iterator iter = list.iterator(); iter.hasNext();)
		{
			identifiers.add(extractIdentifier(iter.next()));
		}

		return identifiers;
	}

	private Object extractIdentifier(Object object) throws HibernateException
	{
		if (object == null)
		{
			return null;
		}

		return session.getSession().getIdentifier(object);
	}

	class HibernateIterator implements Iterator
	{
		private Iterator iterator;

		HibernateIterator(Iterator iterator)
		{
			this.iterator = iterator;
		}

		public boolean hasNext()
		{
			return iterator.hasNext();
		}

		public Object next()
		{
			try
			{
				Object next = iterator.next();
				Object identifier = null;

				if (returnIdOnly)
				{
					identifier = session.getSession().getIdentifier(next);
				}

				ValueObjectHelper.setSaved(next);
				session.getSession().evict(next);

				return returnIdOnly ? identifier : next;
			}
			catch (HibernateException exc)
			{
				throw new TechnicalException(exc);
			}
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
