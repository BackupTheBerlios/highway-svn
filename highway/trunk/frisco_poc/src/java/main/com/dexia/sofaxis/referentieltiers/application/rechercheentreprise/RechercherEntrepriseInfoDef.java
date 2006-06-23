package com.dexia.sofaxis.referentieltiers.application.rechercheentreprise;

import java.util.Collection;

import org.highway.vo.ValueObject;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;


public interface RechercherEntrepriseInfoDef extends ValueObject {

	public Entreprise getEntreprise();
	
	public Adresse getAdresse();
	
	public Collection getTelephones();
	
	public Rib getRib();
}
