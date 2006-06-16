package org.highway.cache;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.init.InitException;

public class DataCacheHome
{
	/**
	 * Default DataCache.
	 */
	private static DataCache dataCache;
	
	/**
	 * Do not instantiate this class.
	 */
	private DataCacheHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default DataCache.
	 */
	public static DataCache getDataCache()
	{
		return dataCache;
	}

	/**
	 * Returns the default DataCache.
	 * @throws InitException if no default DataCache is set
	 */
	private static DataCache getSafeDataCache()
	{
		if (dataCache == null)
		{
			throw new InitException("No default DataCache set");
		}

		return dataCache;
	}

	/**
	 * Sets the dataCache oject to be used as default
	 */
	public static synchronized void setDataCache(DataCache newDataCache)
	{
		dataCache = newDataCache;
		DebugHome.getDebugLog().info(
			"Default DataCache is set to " + newDataCache);
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#register(RefreshableData)
	 */
	public static void register(RefreshableData refreshableData)
	{
		getSafeDataCache().register(refreshableData);
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#register(RefreshableDataSet)
	 */
	public static void register(RefreshableDataSet refreshableDataSet)
	{
		getSafeDataCache().register(refreshableDataSet);
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#forceRefresh(RefreshableData)
	 */
	public static void forceRefresh(RefreshableData refreshableData)
	{
		getSafeDataCache().forceRefresh(refreshableData);
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#forceRefresh(RefreshableDataSet)
	 */
	public static void forceRefresh(RefreshableDataSet refreshableDataSet)
	{
		getSafeDataCache().forceRefresh(refreshableDataSet);
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#getData(Object)
	 */
	public static Object getData(Object id)
	{
		return getSafeDataCache().getData(id);
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#clean()
	 */
	public static void clean()
	{
		getSafeDataCache().clean();
	}
	
	/**
	 * Delegates to the default DataCache.
	 *
	 * @throws InitException if no default DataCache is set.
	 * @see DataCache#unregisterAll()
	 */
	public static void unregisterAll()
	{
		getSafeDataCache().unregisterAll();
	}
}
