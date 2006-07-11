package org.highway.database.hibernate.oneclassperhierarchy;

import org.highway.database.DiscriminatorValue;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@DiscriminatorValue("CASH")
public interface CashPaymentDef extends PaymentDef
{
	@MappedOn("CURRENCY")
	String getCurrency();
}
