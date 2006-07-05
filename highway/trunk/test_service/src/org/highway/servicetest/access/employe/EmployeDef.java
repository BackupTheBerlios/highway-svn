package org.highway.servicetest.access.employe;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.DiscriminatorColumn;
import org.highway.database.DiscriminatorValue;
import org.highway.database.Identity;
import org.highway.database.MappedOn;

@MappedOn("EMPLOYE")
@DiscriminatorValue("0")
@DiscriminatorColumn(type="char", column="TYPE")
public interface EmployeDef extends ValueObject{

	@MappedOn("COACH_ID")
	Long getCoachId();
	
	@MappedOn("FIRME_ID")
	Long getFirmeId();
	
	@Identity
	@MappedOn("ID")
	Long getId();
	
	@MappedOn("NOM")
	String getNom();
	
	@MappedOn("PRENOM")
	String getPrenom();
	
	@MappedOn("NIVEAU")
	EmployeNiveau getNiveau();
	
	@MappedOn("SEXE")
	EmployeSexe getSexe();
	
	@MappedOn("SALAIRE")
	Decimal getSalaire();	
}
