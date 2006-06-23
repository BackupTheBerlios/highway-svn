package org.highway.database.hibernate;

import org.highway.database.hibernate.TrimInterceptor;
import org.highway.database.hibernate.TrimmedStringHibernateType;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="TRIM_TEST"
 */
@org.highway.annotation.ValueObject
public interface TrimTestVoDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	long getId();
	
	/**
	 * This property is not trimmed.
	 * 
	 * @socle.mapping.property column="PROPERTY_1"
	 */
	String getProperty1();
	
	/**
	 * This property is trimmed through {@link TrimmedStringHibernateType}.
	 * 
	 * @socle.mapping.property column="PROPERTY_2"
	 *                         type="org.highway.database.hibernate.TrimmedStringHibernateType"
	 */
	String getProperty2();
	
	/**
	 * This property is trimmed through {@link TrimInterceptor}.
	 * 
	 * @socle.mapping.property column="PROPERTY_3"
	 * @socle.vo.property.trimpolicy spaceboth
	 */
	String getProperty3();
}
