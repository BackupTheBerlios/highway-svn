package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingProperty;


@org.highway.annotation.ValueObject
@VoMapping(table="PERSONNEMORALETIERS")
public interface PersonneMoraleTiersDef  extends ReferentielTiersDef {


	@BeanPropertyMandatory
	@VoMappingProperty(column="NOMPOSTALLIGNE1")
	public String getNomPostalLigne1();
	@VoMappingProperty(column="NOMPOSTALLIGNE2")
    public String getNomPostalLigne2();

    @BeanPropertyMandatory
    @VoMappingProperty(column="NOM")
    public String getNom();


}
