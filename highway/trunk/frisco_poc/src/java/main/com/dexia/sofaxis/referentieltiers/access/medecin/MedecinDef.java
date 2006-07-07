package com.dexia.sofaxis.referentieltiers.access.medecin;

import java.util.Date;

import org.highway.bean.MandatoryProperty;
import org.highway.database.DiscriminatorValue;
import org.highway.database.MappedOn;
import org.highway.database.MappingKey;

import com.dexia.sofaxis.referentieltiers.access.personnemorale.PersonneMoraleTiersDef;

@MappedOn("MEDECINTIERS")
@DiscriminatorValue("MEDECIN")
@MappingKey("TIERSID")
public interface MedecinDef extends PersonneMoraleTiersDef
 {
	@MappedOn("NUMEROADELI")
    public String getNumeroAdeli();

	@MappedOn("TYPETITRE")
    public String getTypeTitre();

	@MappedOn("TYPECATEGORIE")
    public String getTypeCategorie();
    
	@MappedOn("TYPESPECIALITE")
    public String getTypeSpecialite();

	@MappedOn("DATEDEBUTAGREMENT")
    public Date getDateDebutAgrement();

	@MappedOn("DATEFINAGREMENT")
    public Date getDateFinAgrement();

	@MandatoryProperty
	@MappedOn("PRENOM")
    public String getPrenom();
    
	@MappedOn("TYPECIVILITE")
    public String getTypeCivilite();

	@MappedOn("ACCEPTEEXPERTISES")
    public boolean getAccepteExpertises();

	@MappedOn("ACCEPTECONTREVISITES")
    public boolean getAccepteContreVisites();

	@MappedOn("NOMBREKMMAXIMUM")
    public Short getNombreKmMaximum();

	@MappedOn("TYPEETAT")
    public String getTypeEtat();
    
	@MappedOn("DATEINSCRIPTION")
    public Date getDateInscription();

	@MappedOn("DATEMANDATEMENT")
    public Date getDateMandatement();

	@MappedOn("DATENONUTILISABLE")
    public Date getDateNonUtilisable();

	@MappedOn("DATEDERNIEREMAJLISTEDDASS")
    public Date getDateDerniereMajlisteDdass();

	public com.dexia.sofaxis.referentieltiers.access.adresse.Adresse getAdresse();

}
