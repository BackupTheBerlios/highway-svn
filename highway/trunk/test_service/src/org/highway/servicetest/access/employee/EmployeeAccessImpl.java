package org.highway.servicetest.access.employee;

import java.util.List;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.DatabaseSession;

public class EmployeeAccessImpl extends DatabaseAccessBase implements EmployeeAccess
{
	public void create(Employee employee)
	{
		DatabaseSession session = getSession();
		session.insert(employee);
	}
	
	public void delete(String firstname)
	{
		DatabaseSession session = getSession();
		session.delete(
			"from Employee employee where employee.firstname = ?", 
			firstname
		);
	}
	
	public int count(String firstname)
	{
		DatabaseSession session = getSession();
		List result = session.select(
			"select count(employee.id) from Employee employee where employee.firstname = ?",
			firstname
		);
		return ((Integer) result.get(0)).intValue();
	}
}
