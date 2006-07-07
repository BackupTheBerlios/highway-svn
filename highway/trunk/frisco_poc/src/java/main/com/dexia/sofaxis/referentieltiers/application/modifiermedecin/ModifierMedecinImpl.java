package com.dexia.sofaxis.referentieltiers.application.modifiermedecin;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.adresse.AdresseAccess;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.MedecinAccess;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;
import com.dexia.sofaxis.referentieltiers.access.rib.RibAccess;
import com.dexia.sofaxis.tools.common.UUIDHelper;
import com.dexia.sofaxis.tools.locator.Locator;

public class ModifierMedecinImpl implements ModifierMedecin {

	public void modifierMedecin(Medecin medecin) {
		MedecinAccess medecinAccess = Locator
			.getAccessService(MedecinAccess.class);
		medecinAccess.creerOuMettreAJour(medecin);
	}
	public void modifierAdresseMedecin(String medecinPk, Adresse adresse) {
		boolean isNew = adresse.isNew();
		if (isNew)
			adresse.setAdresseId(UUIDHelper.newUUID());
		
		AdresseAccess adresseAccess = Locator.getAccessService(AdresseAccess.class);
		adresseAccess.creerOuMettreAJour(adresse);
		if (isNew){
			MedecinAccess medecinAccess = Locator.getAccessService(MedecinAccess.class);
			Medecin medecin = medecinAccess.charger(medecinPk);
			medecin.setAdresseId(adresse.getAdresseId());
			medecinAccess.creerOuMettreAJour(medecin);
		}
	}
	public void modifierRibMedecin(String medecinPk, Rib rib) {
		if (rib.isNew()) {
			rib.setRibId(UUIDHelper.newUUID());
			MedecinAccess medecinAccess = Locator.getAccessService(MedecinAccess.class);
			Medecin medecin = medecinAccess.charger(medecinPk);
			medecin.setRibId(rib.getRibId());
			medecinAccess.creerOuMettreAJour(medecin);
		}
		RibAccess ribAccess = Locator.getAccessService(RibAccess.class);
		ribAccess.creerOuMettreAJour(rib);
	}
}
