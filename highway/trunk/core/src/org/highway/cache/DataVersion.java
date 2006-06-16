package org.highway.cache;

import java.io.Serializable;

/**
 * A <tt>DataVersion</tt> instance holds the version of a data ; it gathers
 * the data id and the data checksum.
 * 
 * @see org.highway.cache.RefreshableData
 * @see org.highway.cache.RefreshableDataSet
 * @see org.highway.io.ChecksumHelper
 * 
 * @author David Attias
 * @author Christian de Bevotte
 * @since 1.4.3
 */
public class DataVersion implements Serializable
{
	private static final long serialVersionUID = 9055249866135584220L;
	
	private Object dataId;
	
	private byte[] version;
	
	/**
	 * Returns the data id.
	 */
	public Object getDataId()
	{
		return dataId;
	}

	/**
	 * Sets the data id.
	 */
	public void setDataId(Object dataId)
	{
		this.dataId = dataId;
	}

	/**
	 * Returns the data checksum.
	 */
	public byte[] getVersion()
	{
		return version;
	}

	/**
	 * Sets the data checksum.
	 * @see org.highway.io.ChecksumHelper#checksum(byte[])
	 */
	public void setVersion(byte[] version)
	{
		this.version = version;
	}
}
