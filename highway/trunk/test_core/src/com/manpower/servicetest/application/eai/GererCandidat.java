package com.manpower.servicetest.application.eai;

import com.manpower.servicetest.application.ApplicationService;

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
