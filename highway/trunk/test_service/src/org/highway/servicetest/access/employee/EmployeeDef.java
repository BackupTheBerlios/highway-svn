package org.highway.servicetest.access.employee;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="EMPLOYEE"
 */
@VoMapping(table="EMPLOYEE")
public interface EmployeeDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID", type="long")
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
