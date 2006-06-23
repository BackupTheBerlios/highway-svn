package com.dexia.sofaxis.referentieltiers.access.adresse;


import org.highway.vo.ValueObject;

@org.highway.annotation.ValueObject
public interface AdresseDef  extends ValueObject {


   
    public String getAdresseId();
    

    public String getComplementRemise();
    

    public String getTypeVoie();
    

    public String getNumero();
    

    public String getNomVoie();
    

    public String getComplement();
    

    public String getBoitePostale();
    

    public String getLibelleLocalite();
    

    public String getCodePostal();
    

    public String getLibelleAcheminement();
    

    public String getTypePays();
    

    public Boolean getAfficherLocalite();
    

    public String getObservationsAdresse();
    
    
   
}
