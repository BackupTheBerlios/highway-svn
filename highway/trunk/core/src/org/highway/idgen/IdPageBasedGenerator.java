/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of {@link SimpleIdGenerator} based on pages of available unique ids.
 *
 * 
 */
public class IdPageBasedGenerator implements SimpleIdGenerator
{
	private IdPage mainPage;
	private Map pageMap;
	private IdPageSupplier supplier;
	private int length;

	/**
	 * Constructs a <tt>IdPageBasedGenerator</tt> with the specified length and {@link IdPageSupplier}.
	 *
	 * @param length length of the desired {@link IdPage}s
	 * @param supplier IdPageSupplier to achieve it
	 */
	public IdPageBasedGenerator(int length, IdPageSupplier supplier)
	{
		this.length = length;
		this.supplier = supplier;
	}

	public synchronized long getNextId()
	{
		if ((mainPage == null) || ! mainPage.hasNext())
		{
			mainPage = supplier.getNextPage(length, null);
		}

		return mainPage.next();
	}

	/**
	 * {@inheritDoc}<br>
	 * The object provided as argument is used to get the counter name (see 
	 * {@link DBIdPageSupplier#getNextPage(int, String)}) through its 
	 * <tt>toString()</tt> method.
	 * 
	 * @param argument object that provides the counter name through its 
	 *        <tt>toString()</tt> method
	 */
	public synchronized long getNextId(Object argument)
	{
		if (argument == null)
		{
			return getNextId();
		}

		if (pageMap == null)
		{
			pageMap = new HashMap();
		}

		IdPage page = (IdPage) pageMap.get(argument.toString());

		if ((page == null) || ! page.hasNext())
		{
			page = supplier.getNextPage(length, argument.toString());
			pageMap.put(argument.toString(), page);
		}

		return page.next();
	}
}
