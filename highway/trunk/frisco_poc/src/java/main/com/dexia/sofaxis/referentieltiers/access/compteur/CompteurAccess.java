package com.dexia.sofaxis.referentieltiers.access.compteur;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;

/**
 * ATTENTION : cette classe n'est pas conçue pour être utilisée
 * dans un environnement de production (il manque la gestion des 
 * locks). 
 *
 */

public interface CompteurAccess extends Service, DynamicService
{
	public static final String TIERS_ENTREPRISE = "ENTREPRISE";
    public static final String TIERS_COLLECTIVITE = "COLLECTIVITE";
    public static final String TIERS_MEDECIN = "MEDECIN";
    
	/**
	 * Renvoie le prochain numéro pour le type de compteur sélectionné
	 * @param TypeCompteur
	 * @return le dernier numéro
	 */
	String getNextNumber(String TypeCompteur);

}
