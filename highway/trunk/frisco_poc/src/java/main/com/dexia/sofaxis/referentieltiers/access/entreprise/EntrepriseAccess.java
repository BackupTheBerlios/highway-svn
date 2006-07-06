package com.dexia.sofaxis.referentieltiers.access.entreprise;


import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.tools.common.SearchResult;



public interface EntrepriseAccess extends Service, DynamicService
{
	SearchResult<Entreprise> chargerEntreprise(RechercheEntrepriseCritere critere, int maxResultat);
	
	SearchResult<Entreprise> chercherDoublon(RechercheEntrepriseCritere critere);
	
	void creerOuMettreAJour(Entreprise entreprise);
}
