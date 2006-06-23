package com.dexia.sofaxis.referentieltiers.access.personnemorale;


import org.highway.vo.ValueObject;


@org.highway.annotation.ValueObject
public interface ReferentielTiersDef extends ValueObject {


    public String getTiersId();
    
    public String getIdentifiantFonctionnel();
    
    public String getNomCourt();   
    
    public String getAdresseId();
    
    public String getRibId();
    
}
