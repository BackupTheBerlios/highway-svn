package com.dexia.sofaxis.referentieltiers.application;

import junit.framework.TestCase;

import org.highway.debug.DebugHome;
import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.RechercheMedecinCritere;
import com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecin;
import com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl;
import com.dexia.sofaxis.referentieltiers.application.recherchermedecin.RechercherMedecin;
import com.dexia.sofaxis.referentieltiers.application.recherchermedecin.RechercherMedecinInfo;
import com.dexia.sofaxis.tools.common.SearchResult;

public class RechercherMedecinImplTest extends TestCase
{
	ServiceLocator locator;
	
	protected void setUp() throws Exception
	{
		super.setUp();

		AccessInit.init();
		locator = new DynamicLocator();
		
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.recherchermedecin.RechercherMedecinImpl.rechercherMedecin(RechercheMedecinCritere)'
	 */
	public void testRechercherMedecin()
	{
		ModifierMedecin servicesCreation= new ModifierMedecinImpl();
		Medecin medecin = new Medecin();
		medecin.setNom("DOE");
		medecin.setPrenom("JOHN");
		servicesCreation.modifierMedecin(medecin);

		
		RechercheMedecinCritere critere = new RechercheMedecinCritere();
		critere.setNom("DOE");
		SearchResult result = ((RechercherMedecin) locator.getService(RechercherMedecin.class)).rechercherMedecin(critere);
		DebugHome.debugValue("resultat de la rechercher", result);
		assertTrue(result.getResult().size()>0);

	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.recherchermedecin.RechercherMedecinImpl.rechercherMedecinDetaille(RechercheMedecinCritere)'
	 */
	public void testRechercherMedecinDetaille()
	{
		ModifierMedecin servicesCreation= new ModifierMedecinImpl();
		Medecin medecin = new Medecin();
		medecin.setNom("DOE");
		medecin.setPrenom("JOHN");
		servicesCreation.modifierMedecin(medecin);
		Adresse adresse = new Adresse();
		adresse.setLibelleLocalite("BOULOGNE");
		adresse.setCodePostal("92100");
		servicesCreation.modifierAdresseMedecin(medecin.getTiersId(), adresse);

		
		RechercheMedecinCritere critere = new RechercheMedecinCritere();
		critere.setNom("DOE");
		SearchResult result = ((RechercherMedecin) locator.getService(RechercherMedecin.class)).rechercherMedecinDetaille(critere);
		DebugHome.debugValue("resultat de la recherche", result);
		assertTrue(result.getResult().size()>0 
				&& ((RechercherMedecinInfo)result.getResult().get(0)).getMedecin().getNom().equalsIgnoreCase("DOE")
				&& ((RechercherMedecinInfo)result.getResult().get(0)).getAdresse()!=null);
	}

}
