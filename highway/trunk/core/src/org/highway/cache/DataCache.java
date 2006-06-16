package org.highway.cache;

/**
 * Provides a basic caching solution, based on two other interfaces :
 * {@link org.highway.cache.RefreshableData} and
 * {@link org.highway.cache.RefreshableDataSet}.
 * 
 * @author David Attias
 * @author Christian de Bevotte
 * @since 1.4.3
 */
public interface DataCache
{
	/**
	 * Registers a <tt>RefreshableData</tt> in this cache.
	 */
	void register(RefreshableData refreshableData);
	
	/**
	 * Registers a <tt>RefreshableDataSet</tt> in this cache.
	 */
	void register(RefreshableDataSet refreshableDataSet);
	
	/**
	 * Forces this cache to refresh the given <tt>RefreshableData</tt>. This
	 * method does not return the data to avoid any misuse. Use
	 * {@link #getData(Object)} to get the data.
	 */
	void forceRefresh(RefreshableData refreshableData);
	
	/**
	 * Forces this cache to refresh the given <tt>RefreshableDataSet</tt>.
	 * This method does not return the data to avoid any misuse. Use
	 * {@link #getData(Object)} to get the data.
	 */
	void forceRefresh(RefreshableDataSet refreshableDataSet);
	
	/**
	 * Returns the data identified by the given id.
	 * 
	 * @see RefreshableData#getId()
	 * @see RefreshableDataSet#getIdSet()
	 */
	Object getData(Object id);
	
	/**
	 * Removes from this cache all non-registered data.
	 */
	void clean();
	
	/**
	 * Unregisters all data previously registered in this cache.
	 * 
	 * @see #register(RefreshableData)
	 * @see #register(RefreshableDataSet)
	 */
	void unregisterAll();
}