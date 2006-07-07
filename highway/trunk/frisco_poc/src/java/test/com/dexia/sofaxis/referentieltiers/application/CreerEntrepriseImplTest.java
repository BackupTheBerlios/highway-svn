package com.dexia.sofaxis.referentieltiers.application;

import junit.framework.TestCase;

import org.highway.debug.DebugHome;
import org.highway.service.ServiceLocator;
import org.highway.service.dynamic.DynamicLocator;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.application.creerentreprise.CreationEntrepriseInfo;
import com.dexia.sofaxis.referentieltiers.application.creerentreprise.CreerEntreprise;
import com.dexia.sofaxis.referentieltiers.application.modifierentreprise.ModifierEntreprise;

public class CreerEntrepriseImplTest extends TestCase
{
	ServiceLocator locator;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		AccessInit.init();
		locator = new DynamicLocator();
		
	}
	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.creerentreprise.CreerEntrepriseImpl.dedoublonnerEntreprise(Entreprise, boolean)'
	 */
	public void testDedoublonnerEntreprise()
	{
		Entreprise entreprise = new Entreprise();
		entreprise.setNom("MyCompany");
		entreprise.setNumeroSiret("NS1234FR");		
		
		CreationEntrepriseInfo info = ((CreerEntreprise) locator.getService(CreerEntreprise.class)).dedoublonnerEntreprise(entreprise, false);
		assertTrue(info.getEntrepriseCree()!=null);
		DebugHome.debugValue("info " , info);
		((ModifierEntreprise) locator.getService(ModifierEntreprise.class)).modifierEntreprise(entreprise);
		CreationEntrepriseInfo info2 = ((CreerEntreprise) locator.getService(CreerEntreprise.class)).dedoublonnerEntreprise(entreprise, false);
		DebugHome.debugValue("info2 " , info2);
		assertTrue(info2.getDoublons()!=null && !info2.getDoublons().isEmpty());
		
	}

}
