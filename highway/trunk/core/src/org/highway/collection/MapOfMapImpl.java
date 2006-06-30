/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the MapOfMap interface using the HashMap class.
 *
 * 
 * @see java.util.Map
 * @see java.util.HashMap
 * 
 */
public class MapOfMapImpl implements MapOfMap, Serializable, Cloneable
{
	/**
	 * The main HashMap object that contains key1-map mappings.
	 */
	private HashMap mainMap;

	public MapOfMapImpl()
	{
		this.mainMap = new HashMap();
	}

	public MapOfMapImpl(MapOfMap doubleMap)
	{
		this.mainMap = new HashMap(doubleMap.keySet().size());
		putAll(doubleMap);
	}

	public int size()
	{
		int size = 0;
		Iterator submaps = mainMap.values().iterator();

		while (submaps.hasNext())
		{
			Map submap = (Map) submaps.next();

			if (submap != null)
			{
				size += submap.size();
			}
		}

		return size;
	}

	public int size(Object firstKey)
	{
		Map submap = getMap0(firstKey, false);

		return (submap == null) ? 0 : submap.size();
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public boolean isEmpty(Object firstKey)
	{
		Map submap = getMap0(firstKey, false);

		return (submap == null) ? true : submap.isEmpty();
	}

	public Map get(Object firstKey, boolean createIfNecessary)
	{
		return getMap0(firstKey, createIfNecessary);
	}

	public Object get(Object firstKey, Object secondKey)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return null;
		}

		return submap.get(secondKey);
	}

	public Object put(Object firstKey, Object secondKey, Object value)
	{
		return getMap0(firstKey, true).put(secondKey, value);
	}

	public void putAll(MapOfMap doubleMap)
	{
		if (doubleMap == null)
		{
			return;
		}

		Iterator entries = doubleMap.entrySet().iterator();

		while (entries.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries.next();
			putAll(entry.getKey(), (Map) entry.getValue());
		}
	}

	public void putAll(Object firstKey, Map map)
	{
		if (map == null)
		{
			return;
		}

		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			submap = new HashMap(map);
			this.mainMap.put(firstKey, submap);
		}
		else
		{
			submap.putAll(map);
		}
	}

	public Map remove(Object firstKey)
	{
		return (Map) mainMap.remove(firstKey);
	}

	public Object remove(Object firstKey, Object secondKey)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return null;
		}

		return submap.remove(secondKey);
	}

	public boolean containsKey(Object firstKey)
	{
		return mainMap.containsKey(firstKey);
	}

	public boolean containsKey(Object firstKey, Object secondKey)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return false;
		}

		return submap.containsKey(secondKey);
	}

	public boolean containsValue(Object value)
	{
		Iterator submaps = this.mainMap.values().iterator();

		while (submaps.hasNext())
		{
			Map submap = (Map) submaps.next();

			if ((submap != null) && submap.containsValue(value))
			{
				return true;
			}
		}

		return false;
	}

	public boolean containsValue(Object firstKey, Object value)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return false;
		}

		return submap.containsValue(value);
	}

	public Set keySet()
	{
		return mainMap.keySet();
	}

	public Set keySet(Object firstKey)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return Collections.EMPTY_SET;
		}

		return submap.keySet();
	}

	public Set entrySet()
	{
		return mainMap.entrySet();
	}

	public Set entrySet(Object firstKey)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return Collections.EMPTY_SET;
		}

		return submap.entrySet();
	}

	public Collection submaps()
	{
		return mainMap.values();
	}

	public Collection values()
	{
		return listValues();
	}

	public List listValues()
	{
		List list = new ArrayList(size());
		Iterator submaps = mainMap.values().iterator();

		while (submaps.hasNext())
		{
			Map submap = (Map) submaps.next();

			if (submap != null)
			{
				list.addAll(submap.values());
			}
		}

		return list;
	}

	public Collection values(Object firstKey)
	{
		return listValues(firstKey);
	}

	public List listValues(Object firstKey)
	{
		Map submap = getMap0(firstKey, false);

		if (submap == null)
		{
			return new ArrayList(0);
		}

		return new ArrayList(submap.values());
	}

	public void clear()
	{
		mainMap.clear();
	}

	/**
	 * Returns a shallow copy of this double map instance:
	 * the keys and values themselves are not cloned.
	 *
	 * @return a double map object clone of this one.
	 * @todo (attias) check that this implem is ok
	 */
	public Object clone()
	{
		return new MapOfMapImpl(this);
	}

	public String toString()
	{
		return this.mainMap.toString();
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (this == obj)
		{
			return true;
		}

		if (! (obj instanceof MapOfMap))
		{
			return false;
		}

		return entrySet().equals(((MapOfMap) obj).entrySet());
	}

	public int hashCode()
	{
		return mainMap.hashCode();
	}

	// implem methods
	private Map getMap0(Object firstKey, boolean createIfNecessary)
	{
		Map submap = (Map) this.mainMap.get(firstKey);

		if ((submap == null) && createIfNecessary)
		{
			submap = new HashMap();
			this.mainMap.put(firstKey, submap);
		}

		return submap;
	}
}
