package org.highway.servicetest.access.telephone;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="TELEPHONE"
 */
@VoMapping(table="TELEPHONE")
@org.highway.annotation.ValueObject
public interface TelephoneDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID", type="long")
	Long getId();
	
	/**
	 * @socle.mapping.property column="VALUE" 
	 */
	@VoMappingProperty(column="VALUE", type="string")
	String getValue();
	
}