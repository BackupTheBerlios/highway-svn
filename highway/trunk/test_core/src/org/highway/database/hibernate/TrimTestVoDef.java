package org.highway.database.hibernate;

import org.highway.bean.PropertyTrimPolicy;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;
import org.highway.database.MappingSpecialType;
import org.highway.helper.StringHelper.TrimPolicy;

/**
 * @socle.mapping table="TRIM_TEST"
 */
@Mapped
@MappedOn("TRIM_TEST")
public interface TrimTestVoDef extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getId();
	
	@MappedOn("PROPERTY_1")
	String getProperty1();
	
	@MappedOn("PROPERTY_2")
	@MappingSpecialType(TrimmedStringHibernateType.class)
	String getProperty2();
	
	@MappedOn("PROPERTY_3")
	@PropertyTrimPolicy(TrimPolicy.SPACE_BOTH)
	String getProperty3();
}
