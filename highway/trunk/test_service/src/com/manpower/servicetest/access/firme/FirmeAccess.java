package com.manpower.servicetest.access.firme;

import java.util.List;

import com.manpower.servicetest.access.AccessService;

public interface FirmeAccess extends AccessService {
	
    /**
     * @socle.service.transaction Supports
     */
	Firme charger(Long firmeId);
	
    /**
     * @socle.service.transaction Required
     */
	void creer(Firme firme);
	
    /**
     * @socle.service.transaction Required
     */
	void modifier(Firme firme);
	
    /**
     * @socle.service.transaction Supports
     */
	List rechercherTous();
	
    /**
     * @socle.service.transaction Supports
     */
	List rechercherTous(int first, int max);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Long firmeId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimerTous();

}
