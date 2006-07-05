package org.highway.servicetest.access.projet;

import java.util.Date;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.MappedOn;


@MappedOn("PROJET")
public interface ProjetDef extends ValueObject {
	

	@Identity
	@MappedOn("ID")
	Long getId();
	

	@MappedOn("NOM")
	String getNom();


	@MappedOn("DEBUT")
	Date getDebut();
	

	@MappedOn("FIRME_ID")
	Long getFirmeId();
	

	@MappedOn("TOTAL_FACTURE")
	Decimal getTotalFacture();

}