package org.highway.database.hibernate.onetablepersubclass;

import org.highway.database.Mapped;
import org.highway.database.MappedOn;
import org.highway.database.MappingKey;

@Mapped
@MappingKey("ID")
@MappedOn("CHEQUE_PAYMENT2")
public interface ChequePayment2Def extends Payment2Def
{
	@MappedOn("ORDERNAME")
	String getOrder();
}
