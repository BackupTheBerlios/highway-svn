package org.highway.servicetest.access.participant;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("PARTICIPANT")
public interface ParticipantDef extends ValueObject {


	@Identity
	@MappedOn("EMPLOYE_ID")
	Long getEmployeId();
	

	@MappedOn("PROJET_ID")
	Long getProjetId();
	
	@MappedOn("ROLE")
	String getRole();
	
}