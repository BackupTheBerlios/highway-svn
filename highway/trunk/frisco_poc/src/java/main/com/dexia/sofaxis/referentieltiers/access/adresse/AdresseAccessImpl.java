package com.dexia.sofaxis.referentieltiers.access.adresse;

import org.highway.database.DatabaseAccessBase;

public class AdresseAccessImpl extends DatabaseAccessBase implements AdresseAccess {

	public void creerOuMettreAJour(Adresse anAdresse) {
		getSession().insertOrUpdate(anAdresse);
	}

	public Adresse charger(String primaryKey) {
		return (Adresse) getSession().select(Adresse.class, primaryKey);
	}

}
