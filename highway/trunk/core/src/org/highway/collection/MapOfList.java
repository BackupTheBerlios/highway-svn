/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 */
public interface MapOfList extends MapOfCollection
{
	List getList(Object key, boolean createIfNecessary);

	List removeList(Object key);

	void add(Object key, int index, Object element);

	boolean addAll(Object key, int index, Collection c);

	Object get(Object key, int index);

	int indexOf(Object key, Object o);

	int lastIndexOf(Object key, Object o);

	ListIterator listIterator(Object key);

	ListIterator listIterator(Object key, int index);

	Object remove(Object key, int index);

	Object set(Object key, int index, Object element);

	List subList(Object key, int fromIndex, int toIndex);
}
