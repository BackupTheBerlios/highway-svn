/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.database;

import org.highway.lifecycle.Closeable;
import java.sql.Connection;
import java.util.List;

/**
 * Defines a SQL database session.<br>
 * Provides database access through select, insert, update and delete methods.<br>
 * All selected objects have a object/relational mapping.<br>
 * Mapping format is specific to the implementation.<br>
 * Objects of this type can be closed since it extends Closeable.<br>
 * All methods throw an IllegalStateException if called when the session is
 * closed.
 */
public interface DatabaseSession extends Closeable
{
	/**
	 * Returns the database object this session is connected to.
	 */
	public Database getDatabase();

	/**
	 * Returns the JDBC connection used by this database session.<br>
	 * <br>
	 * WARNING: do not directly close the connection you get from this method.
	 * It will be closed when this DatabaseSession is closed.
	 */
	public Connection getConnection();

	/**
	 * Loads from the database the specified object.<br>
	 * Developers only need to set the properties that are part of the object
	 * identifier. The other properties are set by this method if the data is
	 * found in the database.<br>
	 * This method must be used when the specified object uses a composite id
	 * (the id is formed by multiple properties).<br>
	 * Returns null if not found.
	 * 
	 * @param object the object to load with the id properties set
	 * @return the specified object with all properties loaded
	 */
	public Object select(Object object);

	/**
	 * Loads from the database the object of the specified type identified by
	 * the specified identifier. Returns null if not found.
	 * 
	 * @param type type of the object to load
	 * @param id identifier of the object to load
	 * @return loaded object
	 */
	public Object select(Class type, Object id);

	/**
	 * Loads from the database the object of the specified type identified by
	 * the specified identifier. Returns null if not found.
	 * 
	 * @param type type of the object to load
	 * @param id identifier of the object to load
	 * @return loaded object
	 */
	public Object select(Class type, long id);

	/**
	 * Loads from the database the specified objects.<br>
	 * Developers only need to set the properties that are part of the object
	 * identifier. The other properties are set by this method if the data is
	 * found in the database.<br>
	 * This method must be used when the specified objects use composite ids
	 * (the id is formed by multiple properties).<br>
	 * Sets an object in the list to null if the object is not found.
	 * 
	 * @param objects the list of objects with the set id properties to load
	 * @return the specified list of objects with all properties set
	 */
	public List select(List objects);

	/**
	 * Loads from the database all objects of the specified type identified by
	 * the specified identifiers.<br>
	 * The returned list contains a null value for each not found value. The
	 * list order is the same than the identifier array order.
	 * 
	 * @param type type of the objects to load
	 * @param ids identifiers of the objects to load
	 * @return list of loaded objects
	 */
	public List select(Class type, Object[] ids);

	/**
	 * Loads from database all the objects selected by the specified query. The
	 * list is empty if no object is selected.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query select query
	 * @return list of loaded objects
	 */
	public List select(String query);

	/**
	 * Loads from database all the objects selected by the specified query. The
	 * list is empty if no object is selected.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query select query
	 * @param parameter query parameter
	 * @return list of loaded objects
	 */
	public List select(String query, Object parameter);

	/**
	 * Loads from database all the objects selected by the specified query. The
	 * list is empty if no object is selected.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query select query
	 * @param parameters query parameters
	 * @return list of loaded objects
	 */
	public List select(String query, Object[] parameters);

	/**
	 * Creates a new <tt>SelectQuery</tt> instance.
	 * 
	 * @return a <tt>SelectQuery</tt> instance.
	 */
	public SelectQuery createSelectQuery();

	/**
	 * Inserts an object in the database.<br>
	 * <br>
	 * If sucessfully saved and of type ValueObject, object <code>dirty</code>
	 * and <code>new</code> properties are both set to false.
	 * 
	 * @param object object to insert
	 */
	public void insert(Object object);

	/**
	 * Inserts objects in the database.<br>
	 * <br>
	 * If sucessfully saved and of type ValueObject, object <code>dirty</code>
	 * and <code>new</code> properties are both set to false.
	 * 
	 * @param objects objects to insert
	 */
	public void insert(Object[] objects);

	/**
	 * Inserts or updates the specified object.<br>
	 * <br>
	 * If of type ValueObject, uses its <code>dirty</code> and
	 * <code>new</code> properties to decide if it must be inserted or
	 * updated. Otherwise the insert or update decision is specific to the
	 * implementation.<br>
	 * <br>
	 * If sucessfully saved and of type ValueObject, object <code>dirty</code>
	 * and <code>new</code> properties are both set to false.
	 * 
	 * @param object object to insert or update
	 */
	public void insertOrUpdate(Object object);

	/**
	 * Inserts or updates the specified objects.<br>
	 * <br>
	 * If of type ValueObject, uses its <code>dirty</code> and
	 * <code>new</code> properties to decide if it must be inserted or
	 * updated. Otherwise the insert or update decision is specific to the
	 * implementation.<br>
	 * <br>
	 * If sucessfully saved and of type ValueObject, object <code>dirty</code>
	 * and <code>new</code> properties are both set to false.
	 * 
	 * @param objects objects to insert or update
	 */
	public void insertOrUpdate(Object[] objects);

	/**
	 * Updates the specified object.<br>
	 * <br>
	 * If sucessfully saved and of type ValueObject, object <code>dirty</code>
	 * and <code>new</code> properties are both set to false.
	 * 
	 * @param object object to update
	 */
	public void update(Object object);

	/**
	 * Updates the specified objects.<br>
	 * <br>
	 * If sucessfully saved and of type ValueObject, object <code>dirty</code>
	 * and <code>new</code> properties are both set to false.
	 * 
	 * @param objects objects to update
	 */
	public void update(Object[] objects);

	/**
	 * Updates the database.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query update query.
	 */
	public void update(String query);

	/**
	 * Updates the database.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query update query.
	 * @param parameter query parameter
	 */
	public void update(String query, Object parameter);

	/**
	 * Updates the database.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query update query.
	 * @param parameters query parameters
	 */
	public void update(String query, Object[] parameters);

	/**
	 * Deletes from the database the object of the specified type identified by
	 * the specified identifier.
	 * 
	 * @param type type of object to delete
	 * @param id identifier of the object to delete
	 */
	public void delete(Class type, Object id);

	/**
	 * Deletes from the database thes objects of the specified type identified
	 * by the specified identifiers.
	 * 
	 * @param type type of object to delete
	 * @param ids identifiers of the objects to delete
	 */
	public void delete(Class type, Object[] ids);

	/**
	 * Deletes from the database the specified object.
	 * 
	 * @param object object to delete
	 */
	public void delete(Object object);

	/**
	 * Deletes from the database the specified objects.
	 * 
	 * @param objects objects to delete
	 */
	public void delete(Object[] objects);

	/**
	 * Deletes from the database all the objects selected by the specified
	 * query.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query delete query
	 */
	public void delete(String query);

	/**
	 * Deletes from the database all the objects selected by the specified
	 * query.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query delete query
	 * @param parameter query parameter
	 */
	public void delete(String query, Object parameter);

	/**
	 * Deletes from the database all the objects selected by the specified
	 * query.<br>
	 * The query string format is specific to implementation.
	 * 
	 * @param query delete query
	 * @param parameters query parameters
	 */
	public void delete(String query, Object[] parameters);
}
