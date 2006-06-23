package com.dexia.sofaxis.referentieltiers.access.personnemorale;


@org.highway.annotation.ValueObject
public interface PersonneMoraleTiersDef  extends ReferentielTiersDef {


	/**
	 * @socle.vo.property.mandatory true
	 */
	public String getNomPostalLigne1();
  
    public String getNomPostalLigne2();
	/**
	 * @socle.vo.property.mandatory true
	 */
    public String getNom();


}
