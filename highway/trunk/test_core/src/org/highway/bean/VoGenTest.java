package org.highway.bean;

import junit.framework.TestCase;

public class VoGenTest extends TestCase
{
	private static String NAME = "Lavoisier";
	private static String[] FIRME_NAMES = new String[] {"Valtech", "EDF", "T-System"};

	public void testImplements()
	{
		assertTrue(Building.class.isAssignableFrom(ValueObject.class));
		assertTrue(Building.class.isAssignableFrom(BuildingDef.class));
	}
	
	public void testSuperclass()
	{
		assertEquals(Castle.class.getSuperclass(), Building.class);
		assertEquals(Building.class.getSuperclass(), BuildingBase.class);
		assertEquals(BuildingBase.class.getSuperclass(), ValueObjectAbstract2.class);
	}
	
	public void testSerialVersionUID()
	{
		assertEquals(BuildingBase.serialVersionUID, 123L);
	}

	
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
