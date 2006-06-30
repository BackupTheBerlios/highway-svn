package com.dexia.sofaxis.referentieltiers.access.entreprise;


import java.util.Date;

import org.highway.database.DiscriminatorValue;
import org.highway.database.MappedOn;

import com.dexia.sofaxis.referentieltiers.access.personnemorale.PersonneMoraleTiersDef;


@MappedOn("ENTREPRISE")
@DiscriminatorValue("ENTREP")
public interface EntrepriseDef extends PersonneMoraleTiersDef
{
       
	@MappedOn("NUMEROSIRET")
    public String getNumeroSiret();
	
	@MappedOn("CODENAF")
    public String getCodeNaf();
	
	@MappedOn("ISDANSGROUPEDEXIASOFAXIS")
    public Boolean getIsDansGroupeDexiaSofaxis();
	
	@MappedOn("CONVENTIONCOLLECTIVE")
    public String getConventionCollective();
    
	@MappedOn("NOMBRESALARIES")
    public Integer getNombreSalaries();
    
	@MappedOn("NUMEROURSSAF")
    public String getNumeroUrssaf();
    
	@MappedOn("TYPESOCIETE")
    public String getTypeSociete();
    
	@MappedOn("ETABID")
    public Integer getEtabid();
    
	@MappedOn("NUMEROASSOCIATION")
    public String getNumeroAssociation();
    
	@MappedOn("DATEPUBLICATIONASSOCIATION")
    public Date getDatePublicationAssociation();
    
    
}
