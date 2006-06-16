/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author attias
 */
public interface MapOfMapOfMap
{
	Object put(
		Object firstKey, Object secondKey, Object thirdKey, Object value);

	MapOfMap get(Object firstKey, boolean createIfNecessary);

	Map get(Object firstKey, Object secondKey, boolean createIfNecessary);

	Object get(Object firstKey, Object secondKey, Object thirdKey);

	MapOfMap remove(Object firstKey);

	Map remove(Object firstKey, Object secondKey);

	Object remove(Object firstKey, Object secondKey, Object thirdKey);

	boolean containsKey(Object firstKey);

	boolean containsKey(Object firstKey, Object secondKey);

	boolean containsKey(Object firstKey, Object secondKey, Object thirdKey);

	Set keySet();

	Set keySet(Object firstKey);

	Set keySet(Object firstKey, Object secondKey);

	Set entrySet();

	Set entrySet(Object firstKey);

	Set entrySet(Object firstKey, Object secondKey);

	Collection values();

	Collection values(Object firstKey);

	Collection values(Object firstKey, Object secondKey);

	int size();

	int size(Object firstKey);

	int size(Object firstKey, Object secondKey);

	boolean isEmpty();

	boolean isEmpty(Object firstKey);

	boolean isEmpty(Object firstKey, Object secondKey);

	boolean containsValue(Object value);

	boolean containsValue(Object firstKey, Object value);

	boolean containsValue(Object firstKey, Object secondKey, Object value);

	void putAll(MapOfMapOfMap tripleMap);

	void putAll(Object firstKey, MapOfMap map);

	void putAll(Object firstKey, Object secondKey, Map map);

	void clear();

	boolean equals(Object obj);

	int hashCode();
}
