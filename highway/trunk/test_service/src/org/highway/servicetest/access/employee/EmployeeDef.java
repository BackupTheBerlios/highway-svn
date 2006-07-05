package org.highway.servicetest.access.employee;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("employee")
public interface EmployeeDef extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getId();

	@MappedOn("FIRSTNAME")
	String getFirstname();

	@MappedOn("LASTNAME")
	String getLastname();

	@MappedOn("EMAIL")
	String getEmail();
}
