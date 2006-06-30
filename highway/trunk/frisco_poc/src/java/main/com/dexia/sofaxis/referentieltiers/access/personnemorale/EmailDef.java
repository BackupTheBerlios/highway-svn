package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("EMAIL")
public interface EmailDef extends org.highway.bean.ValueObject {
	@MappedOn("emailId")
	@Identity
    public String getEmailId();
    @MappedOn("valeur")
    public String getValeur();
    

}
