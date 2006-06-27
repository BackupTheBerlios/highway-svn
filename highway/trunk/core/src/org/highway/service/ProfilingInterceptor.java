/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service;

import org.highway.exception.TechnicalException;
import org.highway.helper.MethodHelper;
import org.highway.helper.StringHelper;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * @author attias
 */
public class ProfilingInterceptor implements ServiceInterceptor
{
	private Writer out;

	/**
	 *
	 */
	public ProfilingInterceptor() throws IOException
	{
		FileOutputStream file = new FileOutputStream("profiling.txt");
		out = new BufferedWriter(new OutputStreamWriter(file, "US-ASCII"));
	}

	public Object invoke(ServiceRequest request) throws Throwable
	{
		long entering = System.currentTimeMillis();

		try
		{
			return request.invoke();
		}
		finally
		{
			long exiting = System.currentTimeMillis();
			profileService(entering, exiting, request.getMethod());
		}
	}

	/**
	 * @param entering
	 * @param exiting
	 * @param method
	 */
	private void profileService(long entering, long exiting, Method method)
	{
		try
		{
			// we use a buffer to avoid thread synchronization
			StringBuffer buffer = new StringBuffer(75);
			buffer.append(StringHelper.toString(entering)).append('\t');
			buffer.append(StringHelper.toString(exiting)).append('\t');
			buffer.append(Thread.currentThread().toString()).append('\t');
			buffer.append(MethodHelper.getClassAndMethodName(method, false));
			buffer.append("\r\n");

			// the write implem is synchronized
			// no thread issue if only one write
			out.write(buffer.toString());
			out.flush();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	protected void finalize() throws Throwable
	{
		out.close();
		super.finalize();
	}
}
