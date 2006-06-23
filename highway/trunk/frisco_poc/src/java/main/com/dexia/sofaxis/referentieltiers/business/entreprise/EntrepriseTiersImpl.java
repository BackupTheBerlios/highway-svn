/**
 * 
 */
package com.dexia.sofaxis.referentieltiers.business.entreprise;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.EntrepriseAccess;
import com.dexia.sofaxis.referentieltiers.access.entreprise.RechercheEntrepriseCritere;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.locator.Locator;


public class EntrepriseTiersImpl implements EntrepriseTiers {

	private static final int MAX_RESULT = 30;
	
	public SearchResult<Entreprise> rechercherEntreprise(RechercheEntrepriseCritere critere) {
		EntrepriseAccess entreprise = Locator.getAccessService(EntrepriseAccess.class);
		return entreprise.chargerEntreprise(critere, MAX_RESULT);
	}

	


}
