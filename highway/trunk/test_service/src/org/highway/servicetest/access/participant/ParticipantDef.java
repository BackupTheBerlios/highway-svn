package org.highway.servicetest.access.participant;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="PARTICIPANT"
 */
@VoMapping(table="PARTICIPANT")
public interface ParticipantDef extends ValueObject {

	/**
	 * @socle.mapping.id column="EMPLOYE_ID"
	 */
	@VoMappingId(column="EMPLOYE_ID", type="long")
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