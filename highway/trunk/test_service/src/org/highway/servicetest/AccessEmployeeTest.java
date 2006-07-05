package org.highway.servicetest;

import junit.framework.TestCase;

import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextHome;
import org.highway.service.context.SimpleRequestContext;
import org.highway.servicetest.access.employee.Employee;
import org.highway.servicetest.access.employee.EmployeeAccessImpl;
import org.highway.servicetest.application.ApplicationInit;

public class AccessEmployeeTest extends TestCase
{
	EmployeeAccessImpl access;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		RequestContext context = new SimpleRequestContext(null);
		RequestContextHome.setRequestContext(context);
		access = new EmployeeAccessImpl();
		ApplicationInit.init();
	}
	/*
	 * Test method for 'org.highway.servicetest.access.employee.EmployeeAccessImpl.count(String)'
	 */
	public void testCount()
	{
		 access.count("NICOLAS");
	}
	/*
	 * Test method for 'org.highway.servicetest.access.employee.EmployeeAccessImpl.create(Employee)'
	 */
	public void testCreate()
	{
		Employee employee = new Employee();
		employee.setId(22);
		employee.setFirstname("NICOLAS");
		employee.setLastname("LAINE");
		employee.setEmail("EMAIL!");
		access.create(employee);
		assertTrue(!employee.isDirty());
		assertTrue(!employee.isNew());
		
//		Employee john = new Employee();
//		john.setId(33);
//		john.setFirstname("John");
//		john.setLastname("Doe");
//		john.setEmail("john.doe@chezlui.com");
//		access.create(employee);
//		assertTrue(!employee.isDirty());
//		assertTrue(!employee.isNew());
	}

	/*
	 * Test method for 'org.highway.servicetest.access.employee.EmployeeAccessImpl.delete(String)'
	 */
	public void testDelete()
	{
		access.delete("NICOLAS");
	}



}
