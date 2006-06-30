/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 */
public interface MapOfCollection
{
	int size();

	int size(Object key);

	boolean isEmpty();

	boolean isEmpty(Object key);

	Collection get(Object key, boolean createIfNecessary);

	boolean add(Object key, Object object);

	boolean addAll(MapOfCollection collectionMap);

	boolean addAll(Object key, Collection collection);

	void clear();

	void clear(Object key);

	Collection remove(Object key);

	boolean remove(Object key, Object object);

	boolean removeAll(Object key, Collection collection);

	boolean retainAll(Object key, Collection collection);

	boolean containsKey(Object key);

	boolean containsValue(Object object);

	boolean containsValue(Object key, Object object);

	boolean containsAll(Object key, Collection collection);

	Set entrySet();

	Set keySet();

	Collection values();

	Iterator iterator(Object key);

	Object[] toArray(Object key);

	Object[] toArray(Object key, Object[] array);

	String toString();

	boolean equals(Object obj);

	int hashCode();
}
