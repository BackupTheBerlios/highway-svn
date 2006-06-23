package com.dexia.sofaxis.referentieltiers.business.entreprise;


import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.RechercheEntrepriseCritere;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.services.BusinessService;



public interface EntrepriseTiers extends BusinessService, DynamicService{

	SearchResult<Entreprise> rechercherEntreprise(RechercheEntrepriseCritere critere);
	
}
