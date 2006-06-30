/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.rib;


import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;



public interface RibAccess extends Service, DynamicService
{
	void creerOuMettreAJour(Rib aRib);
	
	Rib charger(String primaryKey);
}
