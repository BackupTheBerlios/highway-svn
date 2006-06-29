package org.highway.servicetest.access.telephone;

import org.highway.bean.ValueObject;
import org.highway.database.Mapped;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;

/**
 * @socle.mapping table="TELEPHONE"
 */
@Mapped(table="TELEPHONE")
public interface TelephoneDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	@Identity(column="ID", type="long")
	Long getId();
	
	/**
	 * @socle.mapping.property column="VALUE" 
	 */
	@VoMappingProperty(column="VALUE", type="string")
	String getValue();
	
}