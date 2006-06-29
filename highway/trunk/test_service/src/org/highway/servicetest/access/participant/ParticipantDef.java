package org.highway.servicetest.access.participant;

import org.highway.bean.ValueObject;
import org.highway.database.Mapped;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;

/**
 * @socle.mapping table="PARTICIPANT"
 */
@Mapped(table="PARTICIPANT")
@org.highway.annotation.ValueObject
public interface ParticipantDef extends ValueObject {

	/**
	 * @socle.mapping.id column="EMPLOYE_ID"
	 */
	@Identity(column="EMPLOYE_ID", type="long")
	Long getEmployeId();
	
	/**
	 * @socle.mapping.id column="PROJET_ID"
	 */
	@VoMappingProperty(column="PROJET_ID", type="long")
	Long getProjetId();
	
	/**
	 * @socle.mapping.property column="ROLE"
	 */
	@VoMappingProperty(column="ROLE", type="string")
	String getRole();
	
}