package com.manpower.servicetest.access.employee;

import com.manpower.servicetest.access.AccessService;

public interface EmployeeAccess extends AccessService
{
	void create(Employee employee);
	
	void delete(String firstname);
	
	int count(String firstname);
}
