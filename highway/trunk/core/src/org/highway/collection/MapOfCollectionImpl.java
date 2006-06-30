/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import org.highway.helper.CollectionHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
public abstract class MapOfCollectionImpl implements MapOfCollection,
	Serializable, Cloneable
{
	private HashMap map;

	public MapOfCollectionImpl()
	{
		this.map = new HashMap();
	}

	public MapOfCollectionImpl(MapOfCollection collectionMap)
	{
		this.map = new HashMap(collectionMap.keySet().size());
		addAll(collectionMap);
	}

	public Collection get(Object key, boolean createIfNecessary)
	{
		Collection subcollec = (Collection) map.get(key);

		if ((subcollec == null) && createIfNecessary)
		{
			subcollec = createCollection();
			map.put(key, subcollec);
		}

		return subcollec;
	}

	protected abstract Collection createCollection();

	public Iterator iterator(Object key)
	{
		Collection subcollec = get(key, false);

		if (subcollec == null)
		{
			return EmptyIterator.EMPTY_ITERATOR;
		}

		return subcollec.iterator();
	}

	public Collection values()
	{
		List values = new ArrayList(size());
		Iterator lists = map.values().iterator();

		while (lists.hasNext())
		{
			Collection subcollec = (Collection) lists.next();
			CollectionHelper.addAllIfNotNull(values, subcollec);
		}

		return values;
	}

	public Set keySet()
	{
		return map.keySet();
	}

	public Set entrySet()
	{
		return map.entrySet();
	}

	public boolean containsAll(Object key, Collection collection)
	{
		Collection subcollec = get(key, false);

		if (subcollec == null)
		{
			return false;
		}

		return subcollec.containsAll(collection);
	}

	public boolean containsValue(Object key, Object object)
	{
		Collection subcollec = get(key, false);

		if (subcollec == null)
		{
			return false;
		}

		return subcollec.contains(object);
	}

	public boolean containsValue(Object object)
	{
		Iterator subCollections = map.values().iterator();

		while (subCollections.hasNext())
		{
			Collection subcollec = (Collection) subCollections.next();

			if ((subcollec != null) && subcollec.contains(object))
			{
				return true;
			}
		}

		return false;
	}

	public boolean containsKey(Object key)
	{
		return map.containsKey(key);
	}

	public boolean retainAll(Object key, Collection collection)
	{
		Collection subcollec = get(key, false);

		if (subcollec == null)
		{
			return false;
		}

		return subcollec.retainAll(collection);
	}

	public boolean removeAll(Object key, Collection collection)
	{
		Collection subcollec = get(key, false);

		if (subcollec == null)
		{
			return false;
		}

		return subcollec.removeAll(collection);
	}

	public boolean remove(Object key, Object object)
	{
		Collection subcollec = get(key, false);

		if (subcollec == null)
		{
			return false;
		}

		return subcollec.remove(object);
	}

	public Collection remove(Object key)
	{
		return (Collection) map.remove(key);
	}

	public void clear(Object key)
	{
		Collection subcollec = get(key, false);

		if (subcollec != null)
		{
			subcollec.clear();
		}
	}

	public void clear()
	{
		map.clear();
	}

	public boolean addAll(Object key, Collection collection)
	{
		if (collection == null)
		{
			return false;
		}

		return get(key, true).addAll(collection);
	}

	public boolean addAll(MapOfCollection collectionMap)
	{
		if (collectionMap == null)
		{
			return false;
		}

		boolean changed = false;
		Iterator entries = collectionMap.entrySet().iterator();

		while (entries.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries.next();
			changed =
				changed
				|| addAll(entry.getKey(), (Collection) entry.getValue());
		}

		return changed;
	}

	public boolean add(Object key, Object object)
	{
		return get(key, true).add(object);
	}

	public boolean isEmpty(Object key)
	{
		Collection subcollec = get(key, false);

		return (subcollec == null) ? true : subcollec.isEmpty();
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public int size(Object key)
	{
		Collection sublist = get(key, false);

		return (sublist == null) ? 0 : sublist.size();
	}

	public int size()
	{
		int size = 0;
		Iterator iter = map.values().iterator();

		while (iter.hasNext())
		{
			Collection subcollec = (Collection) iter.next();

			if (subcollec != null)
			{
				size += subcollec.size();
			}
		}

		return size;
	}

	public Object[] toArray(Object key)
	{
		Collection subcollec = get(key, false);

		return (subcollec == null) ? null : subcollec.toArray();
	}

	public Object[] toArray(Object key, Object[] array)
	{
		Collection subcollec = get(key, false);

		return (subcollec == null) ? null : subcollec.toArray(array);
	}

	public String toString()
	{
		return map.toString();
	}
}
