package org.highway.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.highway.cache.DataVersion;
import org.highway.cache.RefreshableData;
import org.highway.cache.RefreshableDataSet;

/**
 * <tt>RefreshableDataSet</tt> test implementation based on a map of
 * {@link org.highway.cache.RefreshableData}.
 * 
 * @see org.highway.cache.FileDataCacheTest
 * 
 * @author Christian de Bevotte
 * @since 1.4.5
 */
public class MyRefreshableDataSet implements RefreshableDataSet
{
	private Map dataMap;

	private String name;

	private int refreshPeriod;

	public MyRefreshableDataSet(String name, int refreshPeriod)
	{
		this.dataMap = new HashMap();
		this.name = name;
		this.refreshPeriod = refreshPeriod;
	}

	public void add(RefreshableData data)
	{
		dataMap.put(data.getId(), data);
	}

	public Set getIdSet()
	{
		return dataMap.keySet();
	}

	public int getRefreshPeriod()
	{
		return refreshPeriod;
	}

	public boolean[] isObsolete(DataVersion[] checksums)
	{
		boolean[] result = new boolean[checksums.length];
		for (int i = 0; i < checksums.length; i++)
		{
			RefreshableData data = (RefreshableData) dataMap.get(checksums[i]
				.getDataId());
			result[i] = data.isObsolete(checksums[i]);
		}
		return result;
	}

	public Object[] refresh(DataVersion[] checksums)
	{
		Object[] result = new Object[checksums.length];
		for (int i = 0; i < checksums.length; i++)
		{
			RefreshableData data = (RefreshableData) dataMap.get(checksums[i]
				.getDataId());
			result[i] = data.refresh(checksums[i]);
		}
		return result;
	}

	public Object[] refresh(Object[] ids)
	{
		throw new UnsupportedOperationException();
	}

	public String toString()
	{
		return name;
	}
}
