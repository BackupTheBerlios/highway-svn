package com.dexia.sofaxis.referentieltiers.application.modifieradresse;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.adresse.AdresseAccess;
import com.dexia.sofaxis.tools.locator.Locator;

public class ModifierAdresseImpl implements ModifierAdresse {

	public ModifierAdresseImpl() {
		super();
	}

	public void modifierAdresse(Adresse adresse) {
		AdresseAccess adresseAccess = (AdresseAccess)Locator.getAccessService(AdresseAccess.class);
		adresseAccess.creerOuMettreAJour(adresse);
	}

}
