package org.highway.bean;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class VoGenTest extends TestCase
{
	private static String BUILDING_NAME = "Lavoisier";

	private static Corporation[] CORPORATIONS;

	private static List<Employee> VALTECH_EMPLOYEES;

	static
	{
		CORPORATIONS = new Corporation[3];
		CORPORATIONS[0] = new Corporation();
		CORPORATIONS[0].setName("Valtech");
		CORPORATIONS[1] = new Corporation();
		CORPORATIONS[1].setName("EDF");
		CORPORATIONS[2] = new Corporation();
		CORPORATIONS[2].setName("T-Systems");

		VALTECH_EMPLOYEES = new ArrayList<Employee>();
		Employee employee = new Employee();
		employee.setName("David Attias");
		VALTECH_EMPLOYEES.add(employee);
		employee = new Employee();
		employee.setName("Nicolas Lainé");
		VALTECH_EMPLOYEES.add(employee);
	}

	public void testImplements()
	{
		ValueObject vo = new Building();
		BuildingDef buildingDef = new Building();
//		assertTrue(Building.class.isAssignableFrom(ValueObject.class));
//		assertTrue(Building.class.isAssignableFrom(BuildingDef.class));
	}

	public void testSuperclass()
	{
		assertEquals(Castle.class.getSuperclass(), Building.class);
		assertEquals(Building.class.getSuperclass(), BuildingBase.class);
		// assertEquals(BuildingBase.class.getSuperclass(),
		// ValueObjectAbstract2.class);
	}

	public void testSerialVersionUID()
	{
		assertEquals(BuildingBase.serialVersionUID, 123L);
	}

	public void testNewAndDirty()
	{
		Building building = new Building();
		assertTrue(building.isNew());
		assertFalse(building.isDirty());
		
		building.setName(BUILDING_NAME);
		assertTrue(building.isNew());
		assertTrue(building.isDirty());
		
		building.setName(BUILDING_NAME);
		assertTrue(building.isNew());
		assertTrue(building.isDirty());
		
		building.setNumberOfFloors(4);
		assertTrue(building.isNew());
		assertTrue(building.isDirty());
		
		building.setSaved();
		assertFalse(building.isNew());
		assertFalse(building.isDirty());
		
		building.setName(BUILDING_NAME);
		assertFalse(building.isNew());
		assertFalse(building.isDirty());
		
		building.setNumberOfFloors(4);
		assertFalse(building.isNew());
		assertFalse(building.isDirty());
		
		building.setNumberOfFloors(5);
		assertFalse(building.isNew());
		assertTrue(building.isDirty());
	}
	
	public void testSetProperties()
	{
		Building building = new Building();
		
		building.setName(BUILDING_NAME);
		assertEquals(building.getName(), BUILDING_NAME);
		
		building.setCorporations(CORPORATIONS);
		assertEquals(building.getCorporations(), CORPORATIONS);

		building.setEmployees(VALTECH_EMPLOYEES);
		assertEquals(building.getCorporations(), CORPORATIONS);
	}

	public void testEquals()
	{
		Building building = new Building();
		building.setName(BUILDING_NAME);
		building.setCorporations(CORPORATIONS);
		building.setNumberOfFloors(5);
		assertEquals(building, building);

		Building building2 = new Building();
		building2.setName(BUILDING_NAME);
		building2.setCorporations(CORPORATIONS);
		building2.setNumberOfFloors(5);		
		assertEquals(building, building2);
		assertEquals(building.hashCode(), building2.hashCode());
		
		Building clone = (Building) building.clone();
		assertNotSame(building, clone);
		assertEquals(building, clone);
		assertEquals(building2, clone);
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
