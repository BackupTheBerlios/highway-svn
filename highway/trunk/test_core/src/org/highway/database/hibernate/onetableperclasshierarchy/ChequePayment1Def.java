package org.highway.database.hibernate.onetableperclasshierarchy;

import org.highway.database.DiscriminatorValue;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@DiscriminatorValue("CHEQUE")
public interface ChequePayment1Def extends Payment1Def
{
	@MappedOn("ORDERNAME")
	String getOrder();
}
