package com.manpower.servicetest.access.projet;

import java.util.List;

import com.manpower.servicetest.access.AccessService;

public interface ProjetAccess extends AccessService {
	
    /**
     * @socle.service.transaction Supports
     */
	Projet charger(Long projetId);
	
    /**
     * @socle.service.transaction Required
     */
	void creer(Projet projet);
	
    /**
     * @socle.service.transaction Required
     */
	void modifier(Projet projet);
	
    /**
     * @socle.service.transaction Supports
     */
	List rechercherPourFirme(Long firmeId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Long projetId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimerTous();
	
}
