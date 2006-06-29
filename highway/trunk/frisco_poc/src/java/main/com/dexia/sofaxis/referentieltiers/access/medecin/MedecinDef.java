package com.dexia.sofaxis.referentieltiers.access.medecin;

import java.util.Date;

import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.GenerateEjb;
import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminatorValue;
import org.highway.annotation.VoMappingProperty;

import com.dexia.sofaxis.referentieltiers.access.personnemorale.PersonneMoraleTiersDef;
@GenerateEjb
@org.highway.annotation.ValueObject
@VoMapping(table="MEDECINTIERS")
@VoMappingDiscriminatorValue(value="MEDECIN")
public interface MedecinDef extends PersonneMoraleTiersDef
 {
	@VoMappingProperty(column="NUMEROADELI")
    public String getNumeroAdeli();

	@VoMappingProperty(column="TYPETITRE")
    public String getTypeTitre();

	@VoMappingProperty(column="TYPECATEGORIE")
    public String getTypeCategorie();
    
	@VoMappingProperty(column="TYPESPECIALITE")
    public SpecialiteMedecin getTypeSpecialite();

	@VoMappingProperty(column="DATEDEBUTAGREMENT")
    public Date getDateDebutAgrement();

	@VoMappingProperty(column="DATEFINAGREMENT")
    public Date getDateFinAgrement();

	@BeanPropertyMandatory
	@VoMappingProperty(column="PRENOM")
    public String getPrenom();
    
	@VoMappingProperty(column="TYPECIVILITE")
    public String getTypeCivilite();

	@VoMappingProperty(column="ACCEPTEEXPERTISES")
    public boolean getAccepteExpertises();

	@VoMappingProperty(column="ACCEPTECONTREVISITES")
    public boolean getAccepteContreVisites();

	@VoMappingProperty(column="NOMBREKMMAXIMUM")
    public Short getNombreKmMaximum();

	@VoMappingProperty(column="TYPEETAT")
    public String getTypeEtat();
    
	@VoMappingProperty(column="DATEINSCRIPTION")
    public Date getDateInscription();

	@VoMappingProperty(column="DATEMANDATEMENT")
    public Date getDateMandatement();

	@VoMappingProperty(column="DATENONUTILISABLE")
    public Date getDateNonUtilisable();

	@VoMappingProperty(column="DATEDERNIEREMAJLISTEDDASS")
    public Date getDateDerniereMajlisteDdass();



}
