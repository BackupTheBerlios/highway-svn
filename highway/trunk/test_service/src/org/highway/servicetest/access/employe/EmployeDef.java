package org.highway.servicetest.access.employe;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminator;
import org.highway.annotation.VoMappingDiscriminatorValue;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.Decimal;
import org.highway.vo.ValueObject;

///**
//* table-per-subclass
//* 
//* @socle.mapping table="EMPLOYE" 
//*/
/**
 * table-per-class-hierarchy
 *
 * @socle.mapping table="EMPLOYE" 
 *                discriminator-value="0"
 * @socle.mapping.discriminator type="char"
 *                              column="TYPE"
 */
@VoMapping(table="EMPLOYE")
@VoMappingDiscriminatorValue("0")
@VoMappingDiscriminator(type="char", column="TYPE")

public interface EmployeDef extends ValueObject{

	/**
	 * @socle.mapping.property column="COACH_ID"
	 */
	@VoMappingProperty(column="COACH_ID", type="long")
	Long getCoachId();
	
	/**
	 * @socle.mapping.property column="FIRME_ID"
	 */
	@VoMappingProperty(column="FIRME_ID", type="long")
	Long getFirmeId();
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingProperty(column="ID", type="long")
	Long getId();
	
	/**
	 * @socle.mapping.property column="NOM" 
	 */
	@VoMappingProperty(column="NOM", type="string")
	String getNom();
	
	/**
	 * @socle.mapping.property column="PRENOM" 
	 */
	@VoMappingProperty(column="PRENOM", type="string")
	String getPrenom();
	
	/**
	 * @socle.mapping.property column="NIVEAU" 
	 */
	@VoMappingProperty(column="NIVEAU", type="EmployeNiveau")
	EmployeNiveau getNiveau();
	
	/**
	 * @socle.mapping.property column="SEXE" 
	 */
	@VoMappingProperty(column="SEXE", type="EmployeSexe")
	EmployeSexe getSexe();
	
	/**
	 * @socle.mapping.property column="SALAIRE" 
	 */
	@VoMappingProperty(column="SALAIRE", type="decimal")
	Decimal getSalaire();	
	
}
