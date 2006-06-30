package com.dexia.sofaxis.referentieltiers.access.compteur;

import java.util.Date;

import org.highway.database.Identity;
import org.highway.database.MappedOn;


@MappedOn("COMPTEURFACTURE")
public interface CompteurDef  extends org.highway.bean.ValueObject {
	
	@Identity
	@MappedOn("DOMAINE")
    public String getDomaine();
    
	@MappedOn("COMPTEUR")
    public int getCompteur();
    
	@MappedOn("DATEDERNIEREFACTURE")
    public Date getDateDerniereFacture();
    
}
