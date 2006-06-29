package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;
@VoMapping(table="TELEPHONE")
public interface TelephoneDef  extends ValueObject {

	@VoMappingId(column="TELEPHONEID")
    public String getTelephoneId();
	
    @VoMappingProperty(column="VALEUR")
    public String getValeur();
    
    @VoMappingProperty(column="DESCRIPTION")
    public String getDescription();
    
    @VoMappingProperty(column="TYPETELEPHONE")
    public String getTypeTelephone();
}
