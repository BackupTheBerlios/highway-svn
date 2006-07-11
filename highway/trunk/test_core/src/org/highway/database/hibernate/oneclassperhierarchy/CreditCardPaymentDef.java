package org.highway.database.hibernate.oneclassperhierarchy;

import org.highway.database.DiscriminatorValue;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@DiscriminatorValue("CREDIT")
public interface CreditCardPaymentDef extends PaymentDef
{
	@MappedOn("CARDTYPE")
	String getCreditCardType();
}
