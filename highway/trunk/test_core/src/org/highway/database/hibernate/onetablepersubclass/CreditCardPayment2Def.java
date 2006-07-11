package org.highway.database.hibernate.onetablepersubclass;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;
import org.highway.database.MappingKey;

@Mapped
@MappingKey("ID")
@MappedOn("CREDIT_PAYMENT2")
public interface CreditCardPayment2Def extends Payment2Def
{
	@MappedOn("CARDTYPE")
	String getCreditCardType();
}
