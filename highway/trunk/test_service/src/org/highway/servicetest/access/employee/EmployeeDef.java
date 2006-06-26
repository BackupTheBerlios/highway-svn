package org.highway.servicetest.access.employee;

import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="EMPLOYEE"
 */
public interface EmployeeDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	long getId();

	/**
	 * @socle.mapping.property column="FIRSTNAME"
	 */
	String getFirstname();

	/**
	 * @socle.mapping.property column="LASTNAME"
	 */
	String getLastname();

	/**
	 * @socle.mapping.property column="EMAIL"
	 */
	String getEmail();
}
