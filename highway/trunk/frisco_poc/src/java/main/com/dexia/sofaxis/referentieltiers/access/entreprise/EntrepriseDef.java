package com.dexia.sofaxis.referentieltiers.access.entreprise;


import java.util.Date;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminatorValue;
import org.highway.annotation.VoMappingProperty;

import com.dexia.sofaxis.referentieltiers.access.personnemorale.PersonneMoraleTiersDef;

@org.highway.annotation.ValueObject
@VoMapping(table="ENTREPRISE")
@VoMappingDiscriminatorValue(value="ENTREP")
public interface EntrepriseDef extends PersonneMoraleTiersDef
{
       
	@VoMappingProperty(column="NUMEROSIRET")
    public String getNumeroSiret();
	
	@VoMappingProperty(column="CODENAF")
    public String getCodeNaf();
	
	@VoMappingProperty(column="ISDANSGROUPEDEXIASOFAXIS")
    public Boolean getIsDansGroupeDexiaSofaxis();
	
	@VoMappingProperty(column="CONVENTIONCOLLECTIVE")
    public String getConventionCollective();
    
	@VoMappingProperty(column="NOMBRESALARIES")
    public Integer getNombreSalaries();
    
	@VoMappingProperty(column="NUMEROURSSAF")
    public String getNumeroUrssaf();
    
	@VoMappingProperty(column="TYPESOCIETE")
    public String getTypeSociete();
    
	@VoMappingProperty(column="ETABID")
    public Integer getEtabid();
    
	@VoMappingProperty(column="NUMEROASSOCIATION")
    public String getNumeroAssociation();
    
	@VoMappingProperty(column="DATEPUBLICATIONASSOCIATION")
    public Date getDatePublicationAssociation();
    
    
}
