package org.highway.sample.domain.fournisseur;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("FOURNISSEUR")
public interface FournisseurDef extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getId();

	@MappedOn("TOTAL_FACT")
	Decimal getMontantFactureTotal();
	
	@MappedOn("STATUT")
	FournisseurStatut getStatut();
}
