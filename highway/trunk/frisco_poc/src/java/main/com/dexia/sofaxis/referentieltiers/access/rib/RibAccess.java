/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.rib;


import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.tools.services.AccessService;



public interface RibAccess extends AccessService, DynamicService
{
	void creerOuMettreAJour(Rib aRib);
	
	Rib charger(String primaryKey);
}
