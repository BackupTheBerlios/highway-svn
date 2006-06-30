package com.dexia.sofaxis.referentieltiers.access.adresse;


import org.highway.service.dynamic.DynamicService;



public interface AdresseAccess extends DynamicService
{
	void creerOuMettreAJour(Adresse anAdresse);
	
	Adresse charger(String primaryKey);
}
