package com.dexia.sofaxis.referentieltiers.business.compteur;

import com.dexia.sofaxis.referentieltiers.access.compteur.CompteurAccess;
import com.dexia.sofaxis.tools.locator.Locator;

public class CompteurBusinessImpl implements CompteurBusiness {

	public String getNextCollectiviteNumber() {
		CompteurAccess compteurAccess = (CompteurAccess) Locator
		.getAccessService(CompteurAccess.class);
		return compteurAccess.getNextNumber(CompteurAccess.TIERS_COLLECTIVITE);
	}

	public String getNextEntrepriseNumber() {
		CompteurAccess compteurAccess = (CompteurAccess) Locator
		.getAccessService(CompteurAccess.class);
		return compteurAccess.getNextNumber(CompteurAccess.TIERS_ENTREPRISE);
	}

	public String getNextMedecinNumber() {
		CompteurAccess compteurAccess = (CompteurAccess) Locator
		.getAccessService(CompteurAccess.class);
		return compteurAccess.getNextNumber(CompteurAccess.TIERS_MEDECIN);
	}

}
