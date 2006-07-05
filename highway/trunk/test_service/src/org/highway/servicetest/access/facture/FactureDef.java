package org.highway.servicetest.access.facture;

import java.util.Date;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("FACTURE")
public interface FactureDef extends ValueObject
{

	@Identity
	@MappedOn("ID")
	Long getId();
	
	@MappedOn("DATE_CREATION")
	Date getDateCreation();
	

	@MappedOn("DEBUT")
	Date getDebut();
	
	@MappedOn("FIN")
	Date getFin();
	

	@MappedOn("ECHEANCE")
	FactureEcheance getEcheance();
	
	@MappedOn("MONTANT")
	Decimal getMontant();	


	@MappedOn("Decimal")
	Long getProjetId();

}
