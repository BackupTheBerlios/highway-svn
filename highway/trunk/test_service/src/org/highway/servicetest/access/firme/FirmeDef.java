package org.highway.servicetest.access.firme;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="FIRME"
 */
@VoMapping(table="FIRME")
@org.highway.annotation.ValueObject
public interface FirmeDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID", type="long")
	Long getId();
	
	/**
	 * @socle.mapping.property column="NOM" 
	 */
	@VoMappingProperty(column="NOM", type="string")
	String getNom();

//	/**
//	 * @socle.mapping.property column="TYPE_CLIENT" 
//	 */
//	FirmeTypeClient getTypeClient();

}