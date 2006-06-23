package com.dexia.sofaxis.referentieltiers.application.recherchermedecin;

import java.util.Collection;

import org.highway.vo.ValueObject;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;


public interface RechercherMedecinInfoDef extends ValueObject {

	public Medecin getMedecin();
	
	public Adresse getAdresse();
	
	public Collection getTelephones();
	
	public Rib getRib();
}
