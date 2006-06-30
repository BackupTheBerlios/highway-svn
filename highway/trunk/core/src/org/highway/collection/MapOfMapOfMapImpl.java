/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Do not use, still under construction.
 *
 * 
 */
public class MapOfMapOfMapImpl implements MapOfMapOfMap
{
	private HashMap mainMap = new HashMap();

	public MapOfMapOfMapImpl(MapOfMapOfMap tripleMap)
	{
		mainMap = new HashMap(tripleMap.keySet().size());
		putAll(tripleMap);
	}

	public Object put(
		Object firstKey, Object secondKey, Object thirdKey, Object value)
	{
		MapOfMap dmap = get(firstKey, true);

		return dmap.put(secondKey, thirdKey, value);
	}

	public MapOfMap get(Object firstKey, boolean createIfNecessary)
	{
		MapOfMap dmap = (MapOfMap) mainMap.get(firstKey);

		if ((dmap == null) && createIfNecessary)
		{
			dmap = new MapOfMapImpl();
			mainMap.put(firstKey, dmap);
		}

		return dmap;
	}

	public Map get(
		Object firstKey, Object secondKey, boolean createIfNecessary)
	{
		MapOfMap dmap = get(firstKey, createIfNecessary);

		if (dmap == null)
		{
			return null;
		}

		return dmap.get(secondKey, createIfNecessary);
	}

	public Object get(Object firstKey, Object secondKey, Object thirdKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return null;
		}

		return dmap.get(secondKey, thirdKey);
	}

	public MapOfMap remove(Object firstKey)
	{
		return (MapOfMap) mainMap.remove(firstKey);
	}

	public Map remove(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return null;
		}

		return dmap.remove(secondKey);
	}

	public Object remove(Object firstKey, Object secondKey, Object thirdKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return null;
		}

		return dmap.remove(secondKey, thirdKey);
	}

	public boolean containsKey(Object firstKey)
	{
		return mainMap.containsKey(firstKey);
	}

	public boolean containsKey(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return false;
		}

		return dmap.containsKey(secondKey);
	}

	public boolean containsKey(
		Object firstKey, Object secondKey, Object thirdKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return false;
		}

		return dmap.containsKey(secondKey, thirdKey);
	}

	public Set keySet()
	{
		return mainMap.keySet();
	}

	public Set keySet(Object firstKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return Collections.EMPTY_SET;
		}

		return dmap.keySet();
	}

	public Set keySet(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return Collections.EMPTY_SET;
		}

		return dmap.keySet(secondKey);
	}

	public Set entrySet()
	{
		return mainMap.entrySet();
	}

	public Set entrySet(Object firstKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return Collections.EMPTY_SET;
		}

		return dmap.entrySet();
	}

	public Set entrySet(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return Collections.EMPTY_SET;
		}

		return dmap.entrySet(secondKey);
	}

	public Collection values()
	{
		ArrayList values = new ArrayList(size());
		Iterator dmaps = mainMap.values().iterator();

		while (dmaps.hasNext())
		{
			MapOfMap dmap = (MapOfMap) dmaps.next();

			if (dmap != null)
			{
				values.addAll(dmap.values());
			}
		}

		return values;
	}

	public Collection values(Object firstKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return Collections.EMPTY_SET;
		}

		return dmap.values();
	}

	public Collection values(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return Collections.EMPTY_SET;
		}

		return dmap.values(secondKey);
	}

	public int size()
	{
		int size = 0;
		Iterator dmaps = mainMap.values().iterator();

		while (dmaps.hasNext())
		{
			MapOfMap dmap = (MapOfMap) dmaps.next();

			if (dmap != null)
			{
				size += dmap.size();
			}
		}

		return size;
	}

	public int size(Object firstKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return 0;
		}

		return dmap.size();
	}

	public int size(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return 0;
		}

		return dmap.size(secondKey);
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public boolean isEmpty(Object firstKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return false;
		}

		return dmap.isEmpty();
	}

	public boolean isEmpty(Object firstKey, Object secondKey)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return false;
		}

		return dmap.isEmpty(secondKey);
	}

	public boolean containsValue(Object value)
	{
		Iterator dmaps = mainMap.values().iterator();

		while (dmaps.hasNext())
		{
			MapOfMap dmap = (MapOfMap) dmaps.next();

			if ((dmap != null) && dmap.containsValue(value))
			{
				return true;
			}
		}

		return false;
	}

	public boolean containsValue(Object firstKey, Object value)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return false;
		}

		return dmap.containsValue(value);
	}

	public boolean containsValue(
		Object firstKey, Object secondKey, Object value)
	{
		MapOfMap dmap = get(firstKey, false);

		if (dmap == null)
		{
			return false;
		}

		return dmap.containsValue(secondKey, value);
	}

	public void putAll(MapOfMapOfMap tripleMap)
	{
		if (tripleMap == null)
		{
			return;
		}

		Iterator entries = tripleMap.entrySet().iterator();

		while (entries.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries.next();
			putAll(entry.getKey(), (MapOfMap) entry.getValue());
		}
	}

	public void putAll(Object firstKey, MapOfMap doubleMap)
	{
		get(firstKey, true).putAll(doubleMap);
	}

	public void putAll(Object firstKey, Object secondKey, Map map)
	{
		get(firstKey, true).putAll(secondKey, map);
	}

	public void clear()
	{
		mainMap.clear();
	}

	public Object clone()
	{
		return new MapOfMapOfMapImpl(this);
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

		if (! (obj instanceof MapOfMapOfMap))
		{
			return false;
		}

		return entrySet().equals(((MapOfMapOfMap) obj).entrySet());
	}

	public int hashCode()
	{
		return mainMap.hashCode();
	}

	public String toString()
	{
		return mainMap.toString();
	}
}
