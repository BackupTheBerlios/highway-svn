package org.highway.database.hibernate.onetableperconcreteclass;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

public interface Payment3Def extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getPaymentId();
	
	@MappedOn("AMOUNT")
	Integer getAmount();
}
