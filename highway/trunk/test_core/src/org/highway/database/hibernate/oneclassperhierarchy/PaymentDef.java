package org.highway.database.hibernate.oneclassperhierarchy;

import org.highway.bean.ValueObject;
import org.highway.database.DiscriminatorColumn;
import org.highway.database.Identity;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;

@Mapped
@MappedOn("PAYMENT1")
@DiscriminatorColumn(column="PAYMENT_TYPE", type="java.lang.String")
public interface PaymentDef extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getPaiementId();
	
	@MappedOn("AMOUNT")
	Integer getAmount();
}
