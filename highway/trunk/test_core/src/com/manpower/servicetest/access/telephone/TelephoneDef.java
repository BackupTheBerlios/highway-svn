package com.manpower.servicetest.access.telephone;

import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="TELEPHONE"
 */
public interface TelephoneDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	Long getId();
	
	/**
	 * @socle.mapping.property column="VALUE" 
	 */
	String getValue();
	
}