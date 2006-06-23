package com.dexia.sofaxis.referentieltiers.access.medecin;

import java.util.Date;

import com.dexia.sofaxis.referentieltiers.access.personnemorale.PersonneMoraleTiersDef;

@org.highway.annotation.ValueObject
public interface MedecinDef extends PersonneMoraleTiersDef
 {
    public String getNumeroAdeli();

    public String getTypeTitre();

    public String getTypeCategorie();
    
    public SpecialiteMedecin getTypeSpecialite();

    public Date getDateDebutAgrement();

    public Date getDateFinAgrement();

	/**
	 * @socle.vo.property.mandatory true
	 */
    public String getPrenom();
    
    public String getTypeCivilite();

    public boolean getAccepteExpertises();

    public boolean getAccepteContreVisites();

    public Short getNombreKmMaximum();

    public String getTypeEtat();
    
    public Date getDateInscription();

    public Date getDateMandatement();

    public Date getDateNonUtilisable();

    public Date getDateDerniereMajlisteDdass();



}
