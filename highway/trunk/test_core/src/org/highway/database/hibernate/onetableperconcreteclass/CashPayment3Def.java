package org.highway.database.hibernate.onetableperconcreteclass;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@MappedOn("CASH_PAYMENT3")
public interface CashPayment3Def extends Payment3Def
{
	@MappedOn("CURRENCY")
	String getCurrency();
}
