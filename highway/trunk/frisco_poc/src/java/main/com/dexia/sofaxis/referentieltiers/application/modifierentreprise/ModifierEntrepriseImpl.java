package com.dexia.sofaxis.referentieltiers.application.modifierentreprise;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.EntrepriseAccess;
import com.dexia.sofaxis.tools.locator.Locator;

public class ModifierEntrepriseImpl implements ModifierEntreprise {

	public void modifierEntreprise(Entreprise entreprise) {
		EntrepriseAccess entrepriseAccess = (EntrepriseAccess) Locator
				.getAccessService(EntrepriseAccess.class);
		entrepriseAccess.creerOuMettreAJour(entreprise);
	}

}
