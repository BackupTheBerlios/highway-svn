package org.highway.cache;

import java.util.Set;

/**
 * Defines a set of data that may be refreshed.
 * 
 * @see org.highway.cache.DataCache
 * @see org.highway.cache.RefreshableData
 * 
 * @author David Attias
 * @author Christian de Bevotte
 * @since 1.4.3
 */
public interface RefreshableDataSet
{
	/**
	 * Returns a set containing data ids.
	 */
	Set getIdSet();

	/**
	 * Refreshes the data set.
	 * @return the up-to-date data set
	 */
	Object[] refresh(Object[] ids);

	/**
	 * Returns <tt>true</tt> if the data associated with the given version
	 * should be refreshed, <tt>false</tt> otherwise.
	 */
	boolean[] isObsolete(DataVersion[] checksums);

	/**
	 * Refreshes this data if necessary according to the given version.
	 * 
	 * @return the up-to-date data, or <tt>null</tt> if the given version is
	 *         not obsolete
	 * @see #isObsolete(DataVersion)
	 */
	Object[] refresh(DataVersion[] checksums);

	/**
	 * Returns the interval in minutes that should separate successive refresh
	 * executions of this data set.
	 */
	int getRefreshPeriod();
}
