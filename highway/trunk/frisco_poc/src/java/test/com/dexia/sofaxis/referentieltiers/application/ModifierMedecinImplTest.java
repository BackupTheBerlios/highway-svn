package com.dexia.sofaxis.referentieltiers.application;

import junit.framework.TestCase;

import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.MedecinAccess;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;
import com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecin;

public class ModifierMedecinImplTest extends TestCase
{
	ServiceLocator locator;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		AccessInit.init();
		locator = new DynamicLocator();
		
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl.modifierMedecin(Medecin)'
	 */
	public void testModifierMedecin()
	{
		Medecin medecin = new Medecin();
		medecin.setNom("LAINE");
		medecin.setPrenom("NICOLAS");
		((ModifierMedecin) locator.getService(ModifierMedecin.class)).modifierMedecin(medecin);
		assertTrue(!medecin.isNew());
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl.modifierAdresseMedecin(String, Adresse)'
	 */
	public void testModifierAdresseMedecin()
	{
		Medecin medecin = new Medecin();
		medecin.setNom("LAINE");
		medecin.setPrenom("NICOLAS");
		((ModifierMedecin) locator.getService(ModifierMedecin.class)).modifierMedecin(medecin);
		assertTrue(!medecin.isNew());
		Adresse adresse = new Adresse();
		adresse.setNumero("11");
		adresse.setNomVoie("RUE DU BOUT LA VILLE");
		adresse.setTypeVoie("RUE");
		((ModifierMedecin) locator.getService(ModifierMedecin.class)).modifierAdresseMedecin(medecin.getTiersId(), adresse);
		assertTrue(!adresse.isNew());
		medecin = ((MedecinAccess)new DynamicLocator().getService(MedecinAccess.class)).charger(medecin.getTiersId());
		assertTrue(medecin.getAdresseId()!=null);
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl.modifierRibMedecin(String, Rib)'
	 */
	public void testModifierRibMedecin()
	{
		Medecin medecin = new Medecin();
		medecin.setNom("LAINE");
		medecin.setPrenom("NICOLAS");
		((ModifierMedecin) locator.getService(ModifierMedecin.class)).modifierMedecin(medecin);
		assertTrue(!medecin.isNew());
		Rib rib = new Rib();
		rib.setCodeBanque("12345");
		rib.setCodeGuichet("112223");
		((ModifierMedecin) locator.getService(ModifierMedecin.class)).modifierRibMedecin(medecin.getTiersId(), rib);
		assertTrue(!rib.isNew());
		medecin = ((MedecinAccess) locator.getService(MedecinAccess.class)).charger(medecin.getTiersId());
		assertTrue(medecin.getRibId()!=null);
		
	}

}
