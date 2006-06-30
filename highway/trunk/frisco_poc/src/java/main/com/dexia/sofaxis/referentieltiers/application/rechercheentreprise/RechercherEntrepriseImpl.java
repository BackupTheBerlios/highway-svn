/*
 * Created on 26 oct. 2004
 */
package com.dexia.sofaxis.referentieltiers.application.rechercheentreprise;

import org.highway.service.ejb.GenerateEjb;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.RechercheEntrepriseCritere;
import com.dexia.sofaxis.referentieltiers.business.entreprise.EntrepriseTiers;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.locator.Locator;

public class RechercherEntrepriseImpl implements RechercherEntreprise
{

	/**
	 * Recherche une entreprise � partir de crit�res
	 * 
	 * @param criteria liste des cri�res pour la recherche
	 * @return un objet recherche entreprise info
	 */
	public SearchResult<Entreprise> rechercherEntreprise(RechercheEntrepriseCritere criteres) {
		EntrepriseTiers entreprise = Locator.getBusinessService(EntrepriseTiers.class);
		return entreprise.rechercherEntreprise(criteres);
	}
	
	
}