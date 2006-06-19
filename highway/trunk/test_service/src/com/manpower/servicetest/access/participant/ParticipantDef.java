package com.manpower.servicetest.access.participant;

import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="PARTICIPANT"
 */
public interface ParticipantDef extends ValueObject {

	/**
	 * @socle.mapping.id column="EMPLOYE_ID"
	 */
	Long getEmployeId();
	
	/**
	 * @socle.mapping.id column="PROJET_ID"
	 */
	Long getProjetId();
	
	/**
	 * @socle.mapping.property column="ROLE"
	 */
	String getRole();
	
}