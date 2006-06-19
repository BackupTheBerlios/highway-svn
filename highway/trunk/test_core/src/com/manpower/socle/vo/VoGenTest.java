package com.manpower.socle.vo;

import junit.framework.TestCase;

public class VoGenTest extends TestCase
{
	private static String NAME = "touzet";
	private static String[] FIRME_NAMES = new String[] {"loreal", "manpower", "cetelem"};

	public void testSetPropertyValue1()
	{
		Building building = new Building();
		assertTrue(building.isNew());
		assertFalse(building.isDirty());
		building.setName(NAME);
		assertTrue(building.isDirty());
		assertTrue(building.getName() == NAME);
		building.setFirmeNames(FIRME_NAMES);
		assertTrue(building.isDirty());
		assertTrue(building.getFirmeNames() == FIRME_NAMES);
	}

	public void testEquals()
	{
		Building building = new Building();
		building.setName(NAME);
		building.setFirmeNames(FIRME_NAMES);
		building.setFloorNumber(5);
		assertEquals(building, building);
		
		Building clone = (Building) building.clone();
		assertNotSame(building, clone);
		assertEquals(building, clone);
		assertEquals(building.hashCode(), clone.hashCode());
	}
	
	public void testNullStringProperty()
	{
		Building building = new Building();
		assertNull(building.getName());
		building.setName("");
		assertEquals("", building.getName());
		building.setName(null);
		assertNull(building.getName());
	}
}
