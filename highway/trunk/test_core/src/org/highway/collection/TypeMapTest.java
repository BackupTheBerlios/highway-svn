package org.highway.collection;

import junit.framework.TestCase;

import org.highway.collection.TypeMap;

public class TypeMapTest extends TestCase {

	public void test()
	{
		TypeMap map = new TypeMap();
		map.put(Exception.class, Exception.class);
		assertTrue(map.containsKey(Exception.class));
		assertEquals(map.get(Exception.class), Exception.class);
		assertTrue(map.containsKey(RuntimeException.class));
		assertEquals(map.get(RuntimeException.class), Exception.class);
		assertFalse(map.containsKey(Error.class));
		assertEquals(map.get(Error.class), null);
	}
}
