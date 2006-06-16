/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Do not use, still under construction.<br>
 * Map de listes.
 *
 * @author attias
 */
public class MapOfListImpl extends MapOfCollectionImpl implements MapOfList
{
	private int listInitialCapacity = 10;

	public MapOfListImpl()
	{
		super();
	}

	public MapOfListImpl(int listInitialCapacity)
	{
		super();
		this.listInitialCapacity = listInitialCapacity;
	}

	public MapOfListImpl(MapOfCollection collectionMap)
	{
		super(collectionMap);
	}

	public List getList(Object key, boolean createIfNecessary)
	{
		return (List) get(key, createIfNecessary);
	}

	public List removeList(Object key)
	{
		return (List) remove(key);
	}

	public void add(Object key, int index, Object element)
	{
		getList(key, true).add(index, element);
	}

	public boolean addAll(Object key, int index, Collection c)
	{
		return getList(key, true).addAll(index, c);
	}

	public Object get(Object key, int index)
	{
		List list = getList(key, false);

		return (list == null) ? null : list.get(index);
	}

	public int indexOf(Object key, Object o)
	{
		List list = getList(key, false);

		return (list == null) ? (-1) : list.indexOf(o);
	}

	public int lastIndexOf(Object key, Object o)
	{
		List list = getList(key, false);

		return (list == null) ? (-1) : list.lastIndexOf(o);
	}

	public ListIterator listIterator(Object key)
	{
		List list = getList(key, true);

		if (list == null)
		{
			return new NullListIterator(key);
		}

		return list.listIterator();
	}

	public ListIterator listIterator(Object key, int index)
	{
		List list = getList(key, true);

		if (list == null)
		{
			return new NullListIterator(key, index);
		}

		return list.listIterator(index);
	}

	public Object remove(Object key, int index)
	{
		List list = getList(key, false);

		return (list == null) ? null : list.remove(index);
	}

	public Object set(Object key, int index, Object element)
	{
		return getList(key, true).set(index, element);
	}

	public List subList(Object key, int fromIndex, int toIndex)
	{
		List list = getList(key, false);

		return (list == null) ? null : list.subList(fromIndex, toIndex);
	}

	public void trimToSize()
	{
		for (Iterator iter = keySet().iterator(); iter.hasNext();)
		{
			((ArrayList) getList(iter.next(), false)).trimToSize();
		}
	}

	protected Collection createCollection()
	{
		return new ArrayList(listInitialCapacity);
	}

	protected Collection createCollection(int size)
	{
		return new ArrayList(size);
	}

	protected Collection createCollection(Collection collection)
	{
		return new ArrayList(collection);
	}

	public Object clone()
	{
		return new MapOfListImpl(this);
	}

	private class NullListIterator implements ListIterator
	{
		private Object key;
		private int index;
		private ListIterator iterator;

		private NullListIterator(Object key)
		{
			this(key, 0);
		}

		private NullListIterator(Object key, int index)
		{
			this.key = key;
			this.index = index;
		}

		public boolean hasNext()
		{
			return (iterator == null) ? false : iterator.hasNext();
		}

		public Object next()
		{
			if (iterator == null)
			{
				throw new NoSuchElementException();
			}

			return iterator.next();
		}

		public boolean hasPrevious()
		{
			return (iterator == null) ? false : iterator.hasPrevious();
		}

		public Object previous()
		{
			if (iterator == null)
			{
				throw new NoSuchElementException();
			}

			return iterator.previous();
		}

		public int nextIndex()
		{
			return (iterator == null) ? 0 : iterator.nextIndex();
		}

		public int previousIndex()
		{
			return (iterator == null) ? (-1) : iterator.previousIndex();
		}

		public void remove()
		{
			if (iterator == null)
			{
				throw new IllegalStateException();
			}

			iterator.remove();
		}

		public void set(Object o)
		{
			if (iterator == null)
			{
				throw new IllegalStateException();
			}

			iterator.set(o);
		}

		public void add(Object o)
		{
			if (iterator == null)
			{
				iterator = getList(key, true).listIterator(index);
			}

			iterator.add(o);
		}
	}
}
