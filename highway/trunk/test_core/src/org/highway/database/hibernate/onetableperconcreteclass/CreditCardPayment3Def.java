package org.highway.database.hibernate.onetableperconcreteclass;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@MappedOn("CREDIT_PAYMENT3")
public interface CreditCardPayment3Def extends Payment3Def
{
	@MappedOn("CARDTYPE")
	String getCreditCardType();
}
