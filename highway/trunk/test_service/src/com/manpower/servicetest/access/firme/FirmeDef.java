package com.manpower.servicetest.access.firme;

import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="FIRME"
 */
public interface FirmeDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	Long getId();
	
	/**
	 * @socle.mapping.property column="NOM" 
	 */
	String getNom();

//	/**
//	 * @socle.mapping.property column="TYPE_CLIENT" 
//	 */
//	FirmeTypeClient getTypeClient();

}