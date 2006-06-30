package com.dexia.sofaxis.referentieltiers.access.compteur;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;

/**
 * ATTENTION : cette classe n'est pas con�ue pour �tre utilis�e
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
	 * Renvoie le prochain num�ro pour le type de compteur s�lectionn�
	 * @param TypeCompteur
	 * @return le dernier num�ro
	 */
	String getNextNumber(String TypeCompteur);

}
