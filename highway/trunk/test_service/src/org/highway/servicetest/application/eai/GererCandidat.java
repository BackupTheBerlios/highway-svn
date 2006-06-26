package org.highway.servicetest.application.eai;

import org.highway.servicetest.access.candidat.Candidat;
import org.highway.servicetest.application.ApplicationService;

/**
 * @socle.service.generate.ejb
 */
public interface GererCandidat extends ApplicationService
{

	/**
	 * @socle.service.transaction Required
	 */
	public void engagerCandidat(Candidat candidat);

	/**
	 * @socle.service.transaction Required
	 */
	public void virerCandidat(Candidat candidat);
	
}