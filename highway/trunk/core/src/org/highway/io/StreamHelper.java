/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Stream static help methods.
 * @author David Attias
 * @since 1.4.2
 */
public class StreamHelper
{
	/**
	 * Writes all the characters that can be reed from the specified Reader
	 * into the specified Writer.
	 * @param reader Reader to read from.
	 * @param writer Writer to write into.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void write(Reader reader, Writer writer)
		throws IOException
	{
		int n = 0;
		char[] buffer = new char[1024];

		while (true)
		{
			n = reader.read(buffer);

			if (n == -1)
			{
				return;
			}

			writer.write(buffer, 0, n);
		}
	}

	/**
	 * Writes all the bytes that can be reed from the specified InputStream
	 * into the specified OutputStream.
	 * @param in InputStream to read from.
	 * @param out OutputStream to write into.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void write(InputStream in, OutputStream out)
		throws IOException
	{
		int n = 0;
		byte[] buffer = new byte[1024];

		while (true)
		{
			n = in.read(buffer);

			if (n == -1)
			{
				return;
			}

			out.write(buffer, 0, n);
		}
	}

	/**
	 * Returns all the data that can be reed from the specified InputStream
	 * as a byte array.
	 * @param in InputStream to read from.
	 * @throws IOException if an I/O error occurs.
	 * @since 1.3
	 */
	public static byte[] read(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream(in.available());
		write(in, out);

		return out.toByteArray();
	}
}
