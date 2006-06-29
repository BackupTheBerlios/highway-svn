package org.highway.database.hibernate;

import org.highway.bean.ValueObject;
import org.highway.database.Mapped;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;
import org.highway.database.hibernate.TrimInterceptor;
import org.highway.database.hibernate.TrimmedStringHibernateType;

/**
 * @socle.mapping table="TRIM_TEST"
 */
@Mapped(table="TRIM_TEST")
public interface TrimTestVoDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	@Identity(column="ID", type="long")
	long getId();
	
	/**
	 * This property is not trimmed.
	 * 
	 * @socle.mapping.property column="PROPERTY_1"
	 */
	@VoMappingProperty(column="PROPERTY_1", type="string")
	String getProperty1();
	
	/**
	 * This property is trimmed through {@link TrimmedStringHibernateType}.
	 * 
	 * @socle.mapping.property column="PROPERTY_2"
	 *                         type="org.highway.database.hibernate.TrimmedStringHibernateType"
	 */
	@VoMappingProperty(column="PROPERTY_2", type="string")
	String getProperty2();
	
	/**
	 * This property is trimmed through {@link TrimInterceptor}.
	 * 
	 * @socle.mapping.property column="PROPERTY_3"
	 * @socle.vo.property.trimpolicy spaceboth
	 */
	@VoMappingProperty(column="PROPERTY_3", type="string")
	String getProperty3();
}
