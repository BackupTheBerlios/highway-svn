package org.highway.bean;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@MappedOn("employee")
@Mapped
public interface EmployeeDef
{
	@MappedOn("nom")
	String getName();
}
