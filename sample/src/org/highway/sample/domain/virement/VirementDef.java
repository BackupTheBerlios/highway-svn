package org.highway.sample.domain.virement;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("VIREMENT")
public interface VirementDef extends ValueObject
{
	@Identity
	@MappedOn("ID")
	long getId();

	@MappedOn("MONTANT")
	Decimal getMontant();
	
	@MappedOn("FOURN_ID")
	long getFournisseurId();
	
	@MappedOn("FACTU_ID")
	Object getFactureId();
	
	@MappedOn("STATUT")
	VirementStatut getStatut();
}
