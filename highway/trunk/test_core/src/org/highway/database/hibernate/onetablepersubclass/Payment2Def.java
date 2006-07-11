package org.highway.database.hibernate.onetablepersubclass;

import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@MappedOn("PAYMENT2")
public interface Payment2Def extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getPaymentId();
	
	@MappedOn("AMOUNT")
	Integer getAmount();
}
