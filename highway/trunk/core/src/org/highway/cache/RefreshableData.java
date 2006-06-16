package org.highway.cache;

/**
 * Defines a data that may be refreshed.
 * 
 * @see org.highway.cache.DataCache
 * @see org.highway.cache.RefreshableDataSet
 * 
 * @author David Attias
 * @author Christian de Bevotte
 * @since 1.4.3
 */
public interface RefreshableData
{
	/**
	 * Returns the data id.
	 */
	Object getId();

	/**
	 * Refreshes the data.
	 * @return the up-to-date data
	 */
	Object refresh();

	/**
	 * Returns <tt>true</tt> if the data associated with the given version
	 * should be refreshed, <tt>false</tt> otherwise.
	 */
	boolean isObsolete(DataVersion checksum);

	/**
	 * Refreshes this data if necessary according to the given version.
	 * 
	 * @return the up-to-date data, or <tt>null</tt> if the given version is
	 *         not obsolete
	 * @see #isObsolete(DataVersion)
	 */
	Object refresh(DataVersion checksum);

	/**
	 * Returns the interval in minutes that should separate successive refresh
	 * executions of this data.
	 */
	int getRefreshPeriod();
}
