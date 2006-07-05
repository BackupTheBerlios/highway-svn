package org.highway.servicetest.access.telephone;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;


@MappedOn("TELEPHONE")
public interface TelephoneDef extends ValueObject {
	

	@Identity
	@MappedOn("ID")
	Long getId();
	

	@MappedOn("VALUE")
	String getValue();
	
}