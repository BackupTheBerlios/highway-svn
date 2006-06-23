package com.dexia.sofaxis.referentieltiers.access.entreprise;


import java.util.Date;

import com.dexia.sofaxis.referentieltiers.access.personnemorale.PersonneMoraleTiersDef;

@org.highway.annotation.ValueObject
public interface EntrepriseDef extends PersonneMoraleTiersDef
{
       
    
    public String getNumeroSiret();
    
    public String getCodeNaf();
    
    public Boolean getIsDansGroupeDexiaSofaxis();
    
    public String getConventionCollective();
    
    public Integer getNombreSalaries();
    
    public String getNumeroUrssaf();
    
    public String getTypeSociete();
    
    public Integer getEtabid();
    
    public String getNumeroAssociation();
    
    public Date getDatePublicationAssociation();
    
    
}
