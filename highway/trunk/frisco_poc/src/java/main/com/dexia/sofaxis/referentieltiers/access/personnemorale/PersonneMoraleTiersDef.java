package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.bean.MandatoryProperty;
import org.highway.database.DiscriminatorColumn;
import org.highway.database.MappedOn;
import org.highway.database.MappingKey;


@MappedOn("PERSONNEMORALETIERS")
@MappingKey("TIERSID")
public interface PersonneMoraleTiersDef  extends ReferentielTiersDef {


	
	@MappedOn("NOMPOSTALLIGNE1")
	@MandatoryProperty
	public String getNomPostalLigne1();
	
	@MappedOn("NOMPOSTALLIGNE2")
    public String getNomPostalLigne2();


	@MappedOn("NOM")
    @MandatoryProperty
    public String getNom();


}
