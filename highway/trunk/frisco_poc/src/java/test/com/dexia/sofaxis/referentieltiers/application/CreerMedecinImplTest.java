package com.dexia.sofaxis.referentieltiers.application;

import junit.framework.TestCase;

import org.highway.debug.DebugHome;
import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.application.creermedecin.CreationMedecinInfo;
import com.dexia.sofaxis.referentieltiers.application.creermedecin.CreerMedecin;
import com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecin;

public class CreerMedecinImplTest extends TestCase
{
	ServiceLocator locator;
	
	protected void setUp() throws Exception
	{
		super.setUp();

		AccessInit.init();
		locator = new DynamicLocator();
		
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.creermedecin.CreerMedecinImpl.dedoublonnerMedecin(Medecin, boolean)'
	 */
	public void testDedoublonnerMedecin()
	{
		Medecin medecin = new Medecin();
		medecin.setNom("Docky");
		medecin.setPrenom("okay");		
		
		CreationMedecinInfo info = ((CreerMedecin) locator.getService(CreerMedecin.class)).dedoublonnerMedecin(medecin, false);
		assertTrue(info.getMedecinCree()!=null);
		DebugHome.debugValue("info " , info);
		((ModifierMedecin) locator.getService(ModifierMedecin.class)).modifierMedecin(medecin);
		CreationMedecinInfo info2 = ((CreerMedecin) locator.getService(CreerMedecin.class)).dedoublonnerMedecin(medecin, false);
		DebugHome.debugValue("info2 " , info2);
		assertTrue(info2.getDoublons()!=null && !info2.getDoublons().isEmpty());
	}

}
