package org.highway.database.hibernate.oneclassperhierarchy;

import org.highway.database.DiscriminatorValue;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@DiscriminatorValue("CHEQUE")
public interface ChequePaymentDef extends PaymentDef
{
	@MappedOn("ORDERNAME")
	String getOrder();
}
