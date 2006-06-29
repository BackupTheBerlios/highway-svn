package org.highway.servicetest.access.firme;

import org.highway.bean.ValueObject;
import org.highway.database.Mapped;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;

/**
 * @socle.mapping table="FIRME"
 */
@Mapped(table="FIRME")
@org.highway.annotation.ValueObject
public interface FirmeDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	@Identity(column="ID", type="long")
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