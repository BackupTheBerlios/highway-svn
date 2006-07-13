package org.highway.database.hibernate.onetableperconcreteclass;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@MappedOn("CHEQUE_PAYMENT3")
public interface ChequePayment3Def extends Payment3Def
{
	@MappedOn("ORDERNAME")
	String getOrder();
}
