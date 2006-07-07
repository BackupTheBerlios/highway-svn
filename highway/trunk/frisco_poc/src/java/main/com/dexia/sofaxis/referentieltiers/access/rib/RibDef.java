package com.dexia.sofaxis.referentieltiers.access.rib;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("RIB")
public interface RibDef extends ValueObject{
	@Identity
	@MappedOn("RIBID")
    public String getRibId();
	@MappedOn("NUMEROCOMPTE")
    public String getNumeroCompte();
	@MappedOn("CODEBANQUE")
    public String getCodeBanque();
	@MappedOn("CODEGUICHET")
    public String getCodeGuichet();
	@MappedOn("CLE")
    public String getCle();
	@MappedOn("DOMICILIATION")
    public String getDomiciliation();
}
