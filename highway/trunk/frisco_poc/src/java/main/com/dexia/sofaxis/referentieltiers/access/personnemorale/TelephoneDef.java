package com.dexia.sofaxis.referentieltiers.access.personnemorale;

import org.highway.vo.ValueObject;
@org.highway.annotation.ValueObject
public interface TelephoneDef  extends ValueObject {


    public String getTelephoneId();
    
    public String getValeur();
    
    public String getDescription();
    
    public String getTypeTelephone();
    
}
