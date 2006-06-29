package org.highway.database.hibernate;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.annotation.VoMappingPropertyType;
import org.highway.database.hibernate.TrimInterceptor;
import org.highway.database.hibernate.TrimmedStringHibernateType;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="TRIM_TEST"
 */
@VoMapping(table="TRIM_TEST")
public interface TrimTestVoDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID")
	@VoMappingPropertyType("mon.type.a.moi")
	long getId();
	
	/**
	 * This property is not trimmed.
	 * 
	 * @socle.mapping.property column="PROPERTY_1"
	 */
	@VoMappingProperty(column="PROPERTY_1")
	String getProperty1();
	
	/**
	 * This property is trimmed through {@link TrimmedStringHibernateType}.
	 * 
	 * @socle.mapping.property column="PROPERTY_2"
	 *                         type="org.highway.database.hibernate.TrimmedStringHibernateType"
	 */
	@VoMappingProperty(column="PROPERTY_2")
	String getProperty2();
	
	/**
	 * This property is trimmed through {@link TrimInterceptor}.
	 * 
	 * @socle.mapping.property column="PROPERTY_3"
	 * @socle.vo.property.trimpolicy spaceboth
	 */
	@VoMappingProperty(column="PROPERTY_3")
	String getProperty3();
}
