package com.dexia.sofaxis.referentieltiers.application;

import junit.framework.TestCase;

import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextHome;
import org.highway.service.context.SimpleRequestContext;

import com.dexia.sofaxis.referentieltiers.access.AccessInit;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecin;
import com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl;

public class MedecinTest extends TestCase
{
	ModifierMedecin services;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		DebugHome.debugEnter();
		DebugHome.setDebugLog(new Log4jDebugLog(false));
		RequestContext context = new SimpleRequestContext(null);
		RequestContextHome.setRequestContext(context);

		AccessInit.init();
		services = new ModifierMedecinImpl();
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl.modifierMedecin(Medecin)'
	 */
	public void testModifierMedecin()
	{
		Medecin medecin = new Medecin();
		medecin.setNom("LAINE");
		medecin.setPrenom("NICOLAS");
		services.modifierMedecin(medecin);
		assertTrue(!medecin.isNew());
	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl.modifierAdresseMedecin(String, Adresse)'
	 */
	public void testModifierAdresseMedecin()
	{

	}

	/*
	 * Test method for 'com.dexia.sofaxis.referentieltiers.application.modifiermedecin.ModifierMedecinImpl.modifierRibMedecin(String, Rib)'
	 */
	public void testModifierRibMedecin()
	{

	}

}
