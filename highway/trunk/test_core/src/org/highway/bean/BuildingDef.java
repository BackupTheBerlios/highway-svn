package org.highway.bean;

import java.util.List;

import org.highway.vogen.GenerateAbstract;
import org.highway.vogen.GenerateBaseOnly;
import org.highway.vogen.SerialVersionUID;

/**
 * @author David Attias
 */
@GenerateAbstract
@GenerateBaseOnly
// @Superclass(ValueObjectAbstract2.class)
@SerialVersionUID(123L)
public interface BuildingDef extends ValueObject
{
	@MandatoryProperty
	@PropertySize(min = 3, max = 30)
	String getName();

	@MandatoryProperty
	double getSurface();

	@MandatoryProperty
	int getNumberOfFloors();

	@MandatoryProperty
	Corporation[] getCorporations();

	List<Employee> getEmployees();
	
}
