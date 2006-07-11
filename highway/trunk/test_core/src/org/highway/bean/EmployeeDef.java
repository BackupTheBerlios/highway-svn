package org.highway.bean;

import org.highway.database.Identity;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@MappedOn("employee")
public interface EmployeeDef
{
	@Identity
	@MappedOn("nom")
	String getName();
}
