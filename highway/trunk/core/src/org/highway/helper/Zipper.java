/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author attias
 * @deprecated use org.highway.io.ZipHelper
 */
public abstract class Zipper
{
	/**
	 * Field BUF_LEN
	 */
	private static int BUF_LEN = 1024;

	/**
	 * Method unzip
	 * @param content byte[]
	 * @return String
	 * @throws IOException
	 */
	public static String unzip(byte[] content) throws IOException
	{
		return new String(inflate(content));
	}

	/**
	 * Method zip
	 * @param content String
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] zip(String content) throws IOException
	{
		return deflate(content.getBytes());
	}

	public static void main(String[] args)
	{
		String str = "1234123412341234";

		try
		{
			byte[] buf = zip(str);
			System.out.println(new String(buf));
			System.out.println(unzip(buf));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method deflate
	 * @param input byte[]
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] deflate(byte[] input) throws IOException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DeflaterOutputStream dos = new DeflaterOutputStream(bos);
		byte[] buffer = new byte[BUF_LEN];
		int bytesRead = bis.read(buffer);

		while (bytesRead > 0)
		{
			dos.write(buffer, 0, bytesRead);
			bytesRead = bis.read(buffer);
		}

		dos.close();

		return bos.toByteArray();
	}

	/**
	 * Method inflate
	 * @param input byte[]
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] inflate(byte[] input) throws IOException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		InflaterInputStream iis = new InflaterInputStream(bis);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUF_LEN];
		int bytesRead = iis.read(buffer);

		while (bytesRead > 0)
		{
			bos.write(buffer, 0, bytesRead);
			bytesRead = iis.read(buffer);
		}

		bos.flush();

		return bos.toByteArray();
	}
}
