package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.annotation.BeanPropertyMandatory;


@org.highway.annotation.ValueObject
public interface PersonneMoraleTiersDef  extends ReferentielTiersDef {


	@BeanPropertyMandatory
	public String getNomPostalLigne1();
  
    public String getNomPostalLigne2();

    @BeanPropertyMandatory
    public String getNom();


}
