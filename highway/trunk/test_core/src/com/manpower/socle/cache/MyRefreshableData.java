package com.manpower.socle.cache;

import org.highway.cache.DataVersion;
import org.highway.cache.RefreshableData;
import org.highway.debug.DebugHome;
import org.highway.io.ChecksumHelper;

/**
 * <tt>RefreshableData</tt> test implementation based on an array of string
 * values. The next value in the array is taken as current data value each time
 * a refresh occurs. In case the value changed, a data transfer is simulated by
 * waiting for a given time.
 * 
 * @see com.manpower.socle.cache.FileDataCacheTest
 * 
 * @author Christian de Bevotte
 * @since 1.4.5
 */
public class MyRefreshableData implements RefreshableData
{
	private String id;

	private int refreshPeriod;

	private int transferTime;

	private String[] values;

	int refreshCount;

	/**
	 * <tt>true</tt> if data transfer occurred during last refresh,
	 * <tt>false</tt> otherwise.
	 */
	boolean transfered;

	public MyRefreshableData(String id, int refreshPeriod, int transferTime,
		String[] values)
	{
		this.id = id;
		this.refreshPeriod = refreshPeriod;
		this.transferTime = transferTime;
		this.values = values;
	}

	public Object getId()
	{
		return id;
	}

	public int getRefreshPeriod()
	{
		return refreshPeriod;
	}

	public boolean isObsolete(DataVersion checksum)
	{
		return (checksum == null || checksum.getVersion() == null || !equals(
			checksum.getVersion(), ChecksumHelper.checksum(getValue()
				.getBytes())));
	}

	public Object refresh()
	{
		return refresh(null);
	}

	public Object refresh(DataVersion checksum)
	{
		refreshCount++;
		transfered = false;

		if (isObsolete(checksum))
		{
			try
			{
				Thread.sleep(transferTime);
			}
			catch (InterruptedException exc)
			{
			}

			transfered = true;
			DebugHome.debug(id, " --> ", getValue());
			return getValue().getBytes();
		}
		else
		{
			DebugHome.debug(id, " --> ", null);
			return null;
		}
	}

	public String toString()
	{
		return id;
	}

	private String getValue()
	{
		return values[(refreshCount - 1) % values.length];
	}

	private boolean equals(byte[] bytes1, byte[] bytes2)
	{
		for (int i = 0; i < bytes1.length; i++)
		{
			if (bytes1[i] != bytes2[i])
			{
				return false;
			}
		}
		return true;
	}
}
