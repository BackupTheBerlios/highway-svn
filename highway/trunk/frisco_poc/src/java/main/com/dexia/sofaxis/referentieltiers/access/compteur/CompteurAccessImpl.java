package com.dexia.sofaxis.referentieltiers.access.compteur;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.highway.exception.TechnicalException;
import org.highway.helper.DateHelper;

import com.dexia.sofaxis.referentieltiers.access.common.AccessServiceSessionImpl;


/**
 * ATTENTION : cette classe n'est pas con�ue pour �tre utilis�e
 * dans un environnement de production (il manque la gestion des 
 * locks). 
 *
 */

public class CompteurAccessImpl extends AccessServiceSessionImpl implements
		CompteurAccess
{
	

	
	
	public String getNextNumber(String TypeCompteur) {
		
		
		Compteur compteur = loadCompteur(TypeCompteur);
		String nextNumber = null;
		
		if (compteur == null)
		{
			throw new TechnicalException("Le type de compteur demand� n'existe pas !");
		}
		
		// choix du pr�fixe en fonction du tiers choisi
		if (TypeCompteur == CompteurAccess.TIERS_COLLECTIVITE)
		{
			nextNumber = getNextTiersNumber("001",compteur);
		}else if (TypeCompteur == CompteurAccess.TIERS_ENTREPRISE)
		{
			nextNumber = getNextTiersNumber("002",compteur);
		}
		else if (TypeCompteur == CompteurAccess.TIERS_MEDECIN)
		{
			nextNumber = getNextTiersNumber("003",compteur);
		}
		
		compteur.setCompteur(compteur.getCompteur()+1);
		compteur.setDateDerniereFacture(DateHelper.getTodayDateNoTime());
		update(compteur);
		
		
		
		return nextNumber;
	}
	
	
    /** Un num�ro fontrionnel de tiers : pr�fixe (3 char) + compteur (6 char)
     * sans checksum ni codage */
    private String getNextTiersNumber(String prefixe,Compteur compteur) {
      NumberFormat formatCompteur = new DecimalFormat("000000");
      return prefixe+formatCompteur.format(compteur.getCompteur()+1);
    }

    
	
    /**
     * M�thode priv� de chargement � partir de la cl� primaire
     * l'objet charg� correspondant � la cl� primaire est retourn� 
     * ou null si l'objet n'existe pas dans la base
     * @param primaryKey
     * @return 
     */
    private Compteur loadCompteur(String primaryKey)
    {
    	return (Compteur)getSession().select(Compteur.class,primaryKey);
    }
	
    
    /**
     * M�thode de mise � jour dans la base � partir du value object pass� 
     * en oparam�tre
     * @param valueObject
     */
    private void update(Compteur valueObject)
    {
    	getSession().update(valueObject);
    }

}
