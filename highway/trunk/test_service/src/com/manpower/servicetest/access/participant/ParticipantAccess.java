package com.manpower.servicetest.access.participant;

import com.manpower.servicetest.access.AccessService;

public interface ParticipantAccess extends AccessService {
	
	/**
	 * @socle.service.transaction Supports
	 */
	Participant charger(Participant participant);
	
    /**
     * @socle.service.transaction Supports
     */
    Participant charger(Long projetId, Long employeId);
	
    /**
     * @socle.service.transaction Required
     */
	void creer(Participant participant);
	
//    /**
//     * @socle.service.transaction Required
//     */
//	void sauvegarder(Participant participant);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Long projetId, Long employeId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Participant participant);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimerPourProjet(Long projetId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimerTous();
	
}
