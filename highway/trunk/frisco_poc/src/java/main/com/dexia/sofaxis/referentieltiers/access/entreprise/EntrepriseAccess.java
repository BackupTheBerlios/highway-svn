package com.dexia.sofaxis.referentieltiers.access.entreprise;


import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.services.AccessService;



public interface EntrepriseAccess extends AccessService, DynamicService
{
	SearchResult<Entreprise> chargerEntreprise(RechercheEntrepriseCritere critere, int maxResultat);
	
	SearchResult<Entreprise> chercherDoublon(RechercheEntrepriseCritere critere);
	
	void creerOuMettreAJour(Entreprise entreprise);
}
