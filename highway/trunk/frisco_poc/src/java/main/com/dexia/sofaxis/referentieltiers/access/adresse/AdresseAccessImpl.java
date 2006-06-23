package com.dexia.sofaxis.referentieltiers.access.adresse;

import com.dexia.sofaxis.referentieltiers.access.common.AccessServiceSessionImpl;

public class AdresseAccessImpl extends AccessServiceSessionImpl implements AdresseAccess {

	public void creerOuMettreAJour(Adresse anAdresse) {
		getSession().insertOrUpdate(anAdresse);
	}

	public Adresse charger(String primaryKey) {
		return (Adresse) getSession().select(Adresse.class, primaryKey);
	}

}
