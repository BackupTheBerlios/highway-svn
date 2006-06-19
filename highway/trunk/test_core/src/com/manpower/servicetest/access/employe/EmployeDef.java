package com.manpower.servicetest.access.employe;

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
public interface EmployeDef extends ValueObject{

	/**
	 * @socle.mapping.property column="COACH_ID"
	 */
	Long getCoachId();
	
	/**
	 * @socle.mapping.property column="FIRME_ID"
	 */
	Long getFirmeId();
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	Long getId();
	
	/**
	 * @socle.mapping.property column="NOM" 
	 */
	String getNom();
	
	/**
	 * @socle.mapping.property column="PRENOM" 
	 */
	String getPrenom();
	
	/**
	 * @socle.mapping.property column="NIVEAU" 
	 */
	EmployeNiveau getNiveau();
	
	/**
	 * @socle.mapping.property column="SEXE" 
	 */
	EmployeSexe getSexe();
	
	/**
	 * @socle.mapping.property column="SALAIRE" 
	 */
	Decimal getSalaire();	
	
}
