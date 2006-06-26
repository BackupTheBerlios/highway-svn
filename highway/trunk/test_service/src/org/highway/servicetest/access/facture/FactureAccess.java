package org.highway.servicetest.access.facture;

import java.util.List;

import org.highway.servicetest.access.AccessService;

public interface FactureAccess extends AccessService
{

    /**
     * @socle.service.transaction Supports
     */
	Facture charger(Long factureId);
	
    /**
     * @socle.service.transaction Required
     */
	void creer(Facture facture);
	
    /**
     * @socle.service.transaction Required
     */
	void modifier(Facture facture);
	
    /**
     * @socle.service.transaction Supports
     */
	List rechercherFacturesPourProjet(Long projetId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Long factureId);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimerTous();

}
