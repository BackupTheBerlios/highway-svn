/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An object that maps key pairs to values.<br>
 * This interface represents a double indexed map.<br>
 * It means that a pair of keys are necessary to <tt>get</tt> and <tt>put</tt>
 * a value.
 *
 * @author David Attias
 * @see java.util.Map
 * @since 1.1
 *
 * @todo (attias) add the Set entrySet() method to this interface and add an
 * Entry inner interface like the one in Map.
 */
public interface MapOfMap
{
	/**
	 * Associates the specified value with the specified key pair.<br>
	 * If this double map previously contained a mapping for this key pair,
	 * the old value is replaced and returned.
	 *
	 * @param firstKey first key of the pair with which the specified value
	 * is to be associated.
	 * @param secondKey second key of the pair with which the specified value
	 * is to be associated.
	 * @param value value to be associated with the specified key pair.
	 * @return previous value associated with specified key pair or null.
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	Object put(Object firstKey, Object secondKey, Object value);

	/**
	 * Returns the map to which this double map maps the specified key.<br>
	 * Returns <tt>null</tt> if the double map contains no mapping for this key.<br>
	 * A return value of <tt>null</tt> does not <i>necessarily</i> indicate
	 * that the map contains no mapping for the key ; it's also possible that
	 * the double map explicitly maps the key to <tt>null</tt>.<br>
	 * The <tt>containsKey</tt> operation may be used to distinguish
	 * these two cases.
	 *
	 * @return the map to which this double map maps the specified key.
	 * @param firstKey key whose associated map is to be returned.
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	Map get(Object firstKey, boolean createIfNecessary);

	/**
	 * Returns the value associated to the specified key pair if present or
	 * <tt>null</tt>.
	 *
	 * @param firstKey first key of the pair whose associated value is
	 * to be returned.
	 * @param secondKey second key of the key pair whose associated value is
	 * to be returned.
	 * @return the value associated to the specified key pair.
	 * @see java.util.Map#get(java.lang.Object)
	 */
	Object get(Object firstKey, Object secondKey);

	/**
	 * Removes the map associated with the specified key from this double map
	 * if present.
	 *
	 * @param firstKey key whose map is to be removed.
	 * @return the map to wich the specified key is associated or <tt>null</tt>.
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	Map remove(Object firstKey);

	/**
	 * Removes the mapping for this key pair from this double map if present.
	 *
	 * @param firstKey first key of the pair whose mapping is to be removed.
	 * @param secondKey second key of the pair whose mapping is to be removed.
	 * @return previous value associated with specified key pair or
	 * <tt>null</tt>.
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	Object remove(Object firstKey, Object secondKey);

	/**
	 * Returns <tt>true</tt> if this double map contains a mapping for the
	 * specified key.
	 *
	 * @param key key whose presence in this Map is to be tested.
	 */
	boolean containsKey(Object firstKey);

	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the specified
	 * key pair.
	 *
	 * @param firstKey first key of the pair whose presence is to be tested.
	 * @param secondKey second key of the pair whose presence is to be tested.
	 */
	boolean containsKey(Object firstKey, Object secondKey);

	/**
	 * Returns a set view of the keys contained in this map. The set is
	 * backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa.  If the map is modified while an iteration over the set is
	 * in progress, the results of the iteration are undefined.  The set
	 * supports element removal, which removes the corresponding mapping from
	 * the map, via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
	 * <tt>removeAll</tt> <tt>retainAll</tt>, and <tt>clear</tt> operations.
	 * It does not support the add or <tt>addAll</tt> operations.
	 *
	 * @return a set view of the keys contained in this map.
	 */
	Set keySet();

	/**
	 *
	 */
	Set keySet(Object firstKey);

	Set entrySet();

	Set entrySet(Object firstKey);

	Collection submaps();

	/**
	 * Returns all the values of this double map.
	 *
	 * @return collection of values of the double map.
	 * @see #listValues()
	 */
	Collection values();

	/**
	 * Returns all the values of this double map.<br>
	 *
	 * The list returned is a snapshot of what you can find in this
	 * object. The list is NOT backed by this object, so changes
	 * to this object are NOT reflected in the list, and vice-versa.<br>
	 *
	 * The list returned supports all the list optional operations.
	 *
	 * @return the list of values of the double map.
	 */
	List listValues();

	/**
	 * Returns all the values associated to a key pair whose first key is the
	 * specified key.
	 *
	 * @param firstKey first key of pairs whose associated values are to be
	 * returned.
	 * @return collection of values.
	 * @see #listValues(Object)
	 */
	Collection values(Object firstKey);

	/**
	 * Returns all the values associated to a key pair whose first key is the
	 * specified key.<br>
	 *
	 * The list returned is a snapshot of what you can find in this
	 * object. The list is NOT backed by this object, so changes
	 * to this object are NOT reflected in the list, and vice-versa.<br>
	 *
	 * The list returned supports all the list optional operations.
	 *
	 * @param firstKey first key of pairs whose associated values are to be
	 * returned.
	 * @return list of values.
	 */
	List listValues(Object firstKey);

	/**
	 * Returns the number of mappings in this double map.
	  */
	int size();

	/**
	 * Returns the number of mappings in this double map.
	  */
	int size(Object firstKey);

	/**
	 * Returns <tt>true</tt> if this map does not contain any mappings.
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this map contains some mappings for the
	 * specified first key.
	 */
	boolean isEmpty(Object firstKey);

	/**
	 * Returns <tt>true</tt> if this double map maps one or more key pairs to the
	 * specified value.
	 *
	 * @param value value whose presence in this double map is to be tested.
	 */
	boolean containsValue(Object value);

	/**
	 * Returns <tt>true</tt> if this double map maps the
	 * specified value to a key pair whose first key is specified.
	 *
	 * @param value value whose presence in this double map is to be tested.
	 */
	boolean containsValue(Object firstKey, Object value);

	/**
	 * Copies all of the mappings from the specified double map to this one.<br>
	 * <br>
	 * These mappings replace any mappings that this double map had for any
	 * of the keys currently in the specified double map.
	 *
	 * @param doubleMap mappings to be stored in this double map.
	 */
	void putAll(MapOfMap doubleMap);

	/**
	 * Copies all of the mappings from the specified map to this one with the
	 * specified key as first key.<br>
	 * <br>
	 * These mappings replace any mappings that this double map had for any
	 * of the key pairs from the specified first key and the specified map
	 * keys.
	 *
	 * @param firstKey first key of the mappings to store in this map.
	 * @param map mappings to be stored in this double map.
	 */
	void putAll(Object firstKey, Map map);

	/**
	 * Removes all the mappings from this map.
	 */
	void clear();

	/**
	  * Compares the specified object with this map for equality.  Returns
	  * <tt>true</tt> if the given object is also a double map and the two maps
	  * represent the same mappings.  More formally, two maps <tt>t1</tt> and
	  * <tt>t2</tt> represent the same mappings if
	  * <tt>t1.entrySet().equals(t2.entrySet())</tt>.  This ensures that the
	  * <tt>equals</tt> method works properly across different implementations
	  * of the <tt>Map</tt> interface.
	  *
	  * @param o object to be compared for equality with this map.
	  * @return <tt>true</tt> if the specified object is equal to this map.
	  */
	boolean equals(Object obj);

	/**
	 * Returns the hash code value for this map.  The hash code of a map
	 * is defined to be the sum of the hashCodes of each entry in the map's
	 * entrySet view.  This ensures that <tt>t1.equals(t2)</tt> implies
	 * that <tt>t1.hashCode()==t2.hashCode()</tt> for any two maps
	 * <tt>t1</tt> and <tt>t2</tt>, as required by the general
	 * contract of Object.hashCode.
	 *
	 * @return the hash code value for this map.
	 * @see Map.Entry#hashCode()
	 * @see Object#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	int hashCode();
}
