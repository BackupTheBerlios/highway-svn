package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;

@org.highway.annotation.ValueObject
public interface EmailDef extends ValueObject {
	@VoMappingId(column="emailId")
    public String getEmailId();
    @VoMappingProperty(column="valeur")
    public String getValeur();
    

}
