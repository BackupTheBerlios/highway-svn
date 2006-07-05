package org.highway.servicetest.access.firme;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("FIRME")
public interface FirmeDef extends ValueObject {
	

	@Identity
	@MappedOn("ID")
	Long getId();
	
	@MappedOn("NOM")
	String getNom();


//	FirmeTypeClient getTypeClient();

}