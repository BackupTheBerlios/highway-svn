package org.highway.servicetest.access.employee;

import org.highway.servicetest.access.AccessService;

public interface EmployeeAccess extends AccessService
{
	void create(Employee employee);
	
	void delete(String firstname);
	
	int count(String firstname);
}
