/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database;

import org.highway.lifecycle.Closeable;
import java.util.Iterator;
import java.util.List;

/**
 * <p>An object-oriented representation of a select query. This interface
 * exposes some extra functionality beyond that provided by
 * {@link DatabaseSession#select(String, Object[])} :
 * <ul>
 * <li>a particular page of the result set may be selected by calling
 * <tt>setFetchMax()</tt>, <tt>setFetchFirst()</tt></li>
 * <li>the size of the result set may be checked by calling
 * <tt>setFetchMax()</tt>, <tt>setCheckTooManyResults()</tt>, or by
 * calling <tt>getUnique()</tt> in case a single object is expected</li>
 * <li>the result set may contain identifiers only by calling
 * <tt>setReturnIdOnly()</tt>
 * <li>the results may be returned as an instance of {@link Iterator}</li>
 * </ul></p>
 *
 * <p>A <tt>SelectQuery</tt> instance is obtained by calling
 * {@link DatabaseSession#createSelectQuery()}. It is executed by calling
 * <tt>list()</tt> or <tt>iterate()</tt>. A <tt>SelectQuery</tt> may be
 * re-executed by subsequent invocations. Its lifespan is, however, bounded by
 * the lifespan of the <tt>DatabaseSession</tt> that created it.</p>
 *
 * 
 * @since 1.1
 */
public interface SelectQuery extends Closeable
{
	/**
	 * Gets the query text.
	 *
	 * @return the query text.
	 */
	String getQueryText();

	/**
	 * Appends the <tt>String</tt> argument to the query text.
	 *
	 * @param text a <tt>String</tt>.
	 */
	void addQueryText(String text);

	/**
	 * Sets the parameters of this query.
	 *
	 * @param parameters a {@link List} of parameters.
	 */
	void setParameters(List parameters);

	/**
	 * Sets the maximum number of rows to retrieve. If not set, there is no
	 * limit to the number of rows retrieved.
	 *
	 * @param max the maximum number of rows.
	 * @see #setCheckTooManyResults(boolean)
	 */
	void setFetchMax(int max);

	/**
	 * Sets the first row to retrieve. If not set, rows will be retrieved
	 * beginnning from row 0.
	 *
	 * @param first a row number, numbered from 0.
	 */
	void setFetchFirst(int first);

	/**
	 * Indicates if the {@link #list()} method should throw a
	 * {@link TooManyResultsExeption} when there are more matching results than
	 * the maximum fetch size.
	 *
	 * @param check <tt>true</tt> if the number of rows should be checked,
	 *        <tt>false</tt> otherwise.
	 * @see #setFetchMax(int)
	 */
	void setCheckTooManyResults(boolean check);

	/**
	 * Indicates if the query should return identifiers only.
	 *
	 * @param idOnly <tt>true</tt> if the query should return identifiers only,
	 *        <tt>false</tt> otherwise (the default).
	 */
	void setReturnIdOnly(boolean idOnly);

	/**
	 * Convenience method to return a single instance that matches the query,
	 * or <tt>null</tt> if the query returns no results.
	 *
	 * @return the single result or <tt>null</tt>.
	 * @throws TooManyResultsExeption if there is more than one matching result.
	 */
	Object getUnique() throws TooManyResultsExeption;

	/**
	 * Returns the query results as a {@link List}.
	 *
	 * @return the result list.
	 * @throws TooManyResultsExeption if there are more matching results than
	 *         expected.
	 * @see #setCheckTooManyResults(boolean)
	 * @see #setFetchMax(int)
	 */
	List list() throws TooManyResultsExeption;

	/**
	 * Returns the query results as an {@link Iterator}.
	 *
	 * @return the result iterator.
	 */
	Iterator iterate();
}
