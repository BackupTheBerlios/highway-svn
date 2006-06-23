package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.EntrepriseAccess;
import com.dexia.sofaxis.referentieltiers.access.entreprise.RechercheEntrepriseCritere;
import com.dexia.sofaxis.referentieltiers.business.compteur.CompteurBusiness;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.locator.Locator;

public class CreerEntrepriseImpl implements CreerEntreprise {

	public CreationEntrepriseInfo dedoublonnerEntreprise(Entreprise entreprise,
			boolean forcer) {
		CreationEntrepriseInfo infoStatus = new CreationEntrepriseInfo();

		// recherche des entreprises ayant le mêmes numéros siret
		EntrepriseAccess entrepriseAccess = Locator
				.getAccessService(EntrepriseAccess.class);
		RechercheEntrepriseCritere criteres = new RechercheEntrepriseCritere();
		criteres.setNumSiret(entreprise.getNumeroSiret());
		SearchResult infoRecherche = entrepriseAccess.chercherDoublon(criteres);

		// si le siret n'existe pas on créé l'entreprise
		if (((infoRecherche != null) && (infoRecherche.getResult().isEmpty()))
				|| forcer) {
			CompteurBusiness compteurBusiness = (CompteurBusiness) Locator
					.getBusinessService(CompteurBusiness.class);
			String identifiantFonctionnel = compteurBusiness
					.getNextEntrepriseNumber();
			entreprise.setIdentifiantFonctionnel(identifiantFonctionnel);
			infoStatus.setEntrepriseCree(entreprise);
		} else {
			infoStatus.setDoublons(infoRecherche.getResult());
		}

		return infoStatus;
	}

}
