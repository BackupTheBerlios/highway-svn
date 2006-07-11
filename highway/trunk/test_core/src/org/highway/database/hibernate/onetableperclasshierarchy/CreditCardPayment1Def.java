package org.highway.database.hibernate.onetableperclasshierarchy;

import org.highway.database.DiscriminatorValue;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@DiscriminatorValue("CREDIT")
public interface CreditCardPayment1Def extends Payment1Def
{
	@MappedOn("CARDTYPE")
	String getCreditCardType();
}
