package org.highway.sample.access.facture;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("FACTURE")
public interface FactureDef extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getId();

	@MappedOn("MONTANT")
	Decimal getMontant();
	
	@MappedOn("STATUT")
	FactureStatut getStatut();
	
	@MappedOn("FOURN_ID")
	long getFournisseurId();
	
	@MappedOn("VIREM_ID")
	long getVirementId();
}
