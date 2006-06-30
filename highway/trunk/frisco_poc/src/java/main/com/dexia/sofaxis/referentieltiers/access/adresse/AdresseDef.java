package com.dexia.sofaxis.referentieltiers.access.adresse;


import org.highway.database.Identity;
import org.highway.database.MappedOn;
import org.highway.vogen.GenerateAbstract;
import org.highway.vogen.SerialVersionUID;

@GenerateAbstract
@MappedOn("ADRESSE")
@SerialVersionUID(123L)
public interface AdresseDef  extends org.highway.bean.ValueObject {

	@Identity
	@MappedOn("ADRESSEID")
    public String getAdresseId();
    
	@MappedOn("COMPLEMENTREMISE")
    public String getComplementRemise();
    
	@MappedOn("TYPEVOIE")
    public String getTypeVoie();
    
	@MappedOn("NUMERO")
    public String getNumero();
    
	@MappedOn("NOMVOIE")
    public String getNomVoie();
    
	@MappedOn("COMPLEMENT")
    public String getComplement();
    
	@MappedOn("BOITEPOSTALE")
    public String getBoitePostale();
    
	@MappedOn("LIBELLELOCALITE")
    public String getLibelleLocalite();
    
	@MappedOn("CODEPOSTAL")
    public String getCodePostal();
    
	@MappedOn("LIBELLEACHEMINEMENT")
    public String getLibelleAcheminement();
    
	@MappedOn("TYPEPAYS")
    public String getTypePays();
    
	@MappedOn("AFFICHERLOCALITE")
    public Boolean getAfficherLocalite();
    
	@MappedOn("OBSERVATIONSADRESSE")
    public String getObservationsAdresse();
    
    
   
}
