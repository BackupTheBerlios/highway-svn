/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Do not use, still under construction.
 *
 * @author attias
 */
public class EmptyIterator implements Iterator
{
	public static final Iterator EMPTY_ITERATOR = new EmptyIterator();

	private EmptyIterator()
	{
	}

	public boolean hasNext()
	{
		return false;
	}

	public Object next()
	{
		throw new NoSuchElementException();
	}

	public void remove()
	{
		throw new IllegalStateException();
	}
}
