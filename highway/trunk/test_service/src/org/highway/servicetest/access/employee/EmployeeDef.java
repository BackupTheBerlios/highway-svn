package org.highway.servicetest.access.employee;

import org.highway.bean.ValueObject;
import org.highway.database.Mapped;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;

/**
 * @socle.mapping table="EMPLOYEE"
 */
@Mapped(table="EMPLOYEE")
@org.highway.annotation.ValueObject
public interface EmployeeDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	@Identity(column="ID", type="long")
	long getId();

	/**
	 * @socle.mapping.property column="FIRSTNAME"
	 */
	@VoMappingProperty(column="FIRSTNAME", type="String")
	String getFirstname();

	/**
	 * @socle.mapping.property column="LASTNAME"
	 */
	@VoMappingProperty(column="LASTNAME", type="String")
	String getLastname();

	/**
	 * @socle.mapping.property column="EMAIL"
	 */
	@VoMappingProperty(column="EMAIL", type="String")
	String getEmail();
}
