package com.dexia.sofaxis.referentieltiers.access.compteur;

import java.util.Date;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

@org.highway.annotation.ValueObject
@VoMapping(table="COMPTEURFACTURE")
public interface CompteurDef  extends ValueObject {
	
	@VoMappingId(column="DOMAINE")
    public String getDomaine();
    
	@VoMappingProperty(column="COMPTEUR")
    public int getCompteur();
    
	@VoMappingProperty(column="DATEDERNIEREFACTURE")
    public Date getDateDerniereFacture();
    
}
