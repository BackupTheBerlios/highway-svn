package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingProperty;


@VoMapping(table="PERSONNEMORALETIERS")
public interface PersonneMoraleTiersDef  extends ReferentielTiersDef {


	
	@VoMappingProperty(column="NOMPOSTALLIGNE1")
	@BeanPropertyMandatory
	public String getNomPostalLigne1();
	
	@VoMappingProperty(column="NOMPOSTALLIGNE2")
    public String getNomPostalLigne2();


    @VoMappingProperty(column="NOM")
    @BeanPropertyMandatory
    public String getNom();


}
