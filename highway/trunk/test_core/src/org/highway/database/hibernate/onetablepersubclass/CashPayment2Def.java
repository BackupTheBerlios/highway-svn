package org.highway.database.hibernate.onetablepersubclass;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;
import org.highway.database.MappingKey;

@Mapped
@MappingKey("ID")
@MappedOn("CASH_PAYMENT2")
public interface CashPayment2Def extends Payment2Def
{
	@MappedOn("CURRENCY")
	String getCurrency();
}
