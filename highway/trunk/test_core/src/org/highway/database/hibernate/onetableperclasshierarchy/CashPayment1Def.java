package org.highway.database.hibernate.onetableperclasshierarchy;

import org.highway.database.DiscriminatorValue;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@DiscriminatorValue("CASH")
public interface CashPayment1Def extends Payment1Def
{
	@MappedOn("CURRENCY")
	String getCurrency();
}
