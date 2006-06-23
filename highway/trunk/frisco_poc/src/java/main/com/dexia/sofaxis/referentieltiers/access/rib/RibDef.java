package com.dexia.sofaxis.referentieltiers.access.rib;

import org.highway.vo.ValueObject;


@org.highway.annotation.ValueObject
public interface RibDef extends ValueObject{

    public String getRibId();
    
    public String getNumeroCompte();
    
    public String getCodeBanque();
    
    public String getCodeGuichet();
    
    public String getCle();
    
    public String getDomiciliation();
    
}
