package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;
@MappedOn("TELEPHONE")
public interface TelephoneDef  extends ValueObject {
	@Identity
	@MappedOn("TELEPHONEID")
    public String getTelephoneId();
	
	@MappedOn("VALEUR")
    public String getValeur();
    
	@MappedOn("DESCRIPTION")
    public String getDescription();
    
	@MappedOn("TYPETELEPHONE")
    public String getTypeTelephone();
}
