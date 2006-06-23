package com.dexia.sofaxis.referentieltiers.access.compteur;

import java.util.Date;

import org.highway.vo.ValueObject;

@org.highway.annotation.ValueObject
public interface CompteurDef  extends ValueObject {

    public String getDomaine();
    

    public int getCompteur();
    

    public Date getDateDerniereFacture();
    
}
