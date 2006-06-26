package org.highway.servicetest.access.candidat;

import org.highway.servicetest.access.AccessService;



public interface CandidatAccess extends AccessService
{
	/**
	 * @socle.service.transaction Required
	 */
	void create(Candidat candidat);
	
	/**
	 * @socle.service.transaction Required
	 */
	void update(Candidat candidat);
	
	/**
	 * @socle.service.transaction Required
	 */
	void delete(Candidat candidat);
	
}
