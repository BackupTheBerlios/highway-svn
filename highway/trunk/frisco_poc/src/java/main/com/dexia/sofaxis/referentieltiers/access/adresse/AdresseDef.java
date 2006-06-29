package com.dexia.sofaxis.referentieltiers.access.adresse;


import java.util.Collection;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

@VoMapping(table="ADRESSE")
public interface AdresseDef  extends ValueObject {

	@VoMappingId(column="ADRESSEID")
    public String getAdresseId();
    
	@VoMappingProperty(column="COMPLEMENTREMISE")
    public String getComplementRemise();
    
	@VoMappingProperty(column="TYPEVOIE")
    public Collection<String> getTypeVoie();
    
	@VoMappingProperty(column="NUMERO")
    public String getNumero();
    
	@VoMappingProperty(column="NOMVOIE")
    public String getNomVoie();
    
	@VoMappingProperty(column="COMPLEMENT")
    public String getComplement();
    
	@VoMappingProperty(column="BOITEPOSTALE")
    public String getBoitePostale();
    
	@VoMappingProperty(column="LIBELLELOCALITE")
    public String getLibelleLocalite();
    
	@VoMappingProperty(column="CODEPOSTAL")
    public String getCodePostal();
    
	@VoMappingProperty(column="LIBELLEACHEMINEMENT")
    public String getLibelleAcheminement();
    
	@VoMappingProperty(column="TYPEPAYS")
    public String getTypePays();
    
	@VoMappingProperty(column="AFFICHERLOCALITE")
    public Boolean getAfficherLocalite();
    
	@VoMappingProperty(column="OBSERVATIONSADRESSE")
    public String getObservationsAdresse();
    
    
   
}
