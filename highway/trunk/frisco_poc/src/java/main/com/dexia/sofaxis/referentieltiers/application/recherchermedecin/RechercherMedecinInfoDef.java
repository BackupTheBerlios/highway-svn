package com.dexia.sofaxis.referentieltiers.application.recherchermedecin;

import java.util.Collection;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;


public interface RechercherMedecinInfoDef extends org.highway.bean.ValueObject {

	public Medecin getMedecin();
	
	public Adresse getAdresse();
	
	public Collection getTelephones();
	
	public Rib getRib();
}
