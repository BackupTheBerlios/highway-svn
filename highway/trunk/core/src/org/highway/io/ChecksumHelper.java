package org.highway.io;

import gnu.crypto.hash.Sha256;

/**
 * @author Christian de Bevotte
 * @since 1.4.5
 */
public abstract class ChecksumHelper
{
	/**
	 * @param bytes
	 * @return
	 */
	public static byte[] checksum(byte[] bytes)
	{
		Sha256 checksumEngine = new Sha256();
		checksumEngine.update(bytes, 0, bytes.length);
		return checksumEngine.digest();
	}
}
