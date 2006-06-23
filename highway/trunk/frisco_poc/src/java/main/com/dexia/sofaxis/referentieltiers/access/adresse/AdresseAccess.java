package com.dexia.sofaxis.referentieltiers.access.adresse;


import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.tools.services.AccessService;



public interface AdresseAccess extends AccessService, DynamicService
{
	void creerOuMettreAJour(Adresse anAdresse);
	
	Adresse charger(String primaryKey);
}
