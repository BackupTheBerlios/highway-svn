package org.highway.servicetest.access.projet;

import java.util.Date;

import org.highway.vo.Decimal;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="PROJET"
 */
public interface ProjetDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	Long getId();
	
	/**
	 * @socle.mapping.property column="NOM" 
	 */
	String getNom();

    /**
     * @socle.mapping.property column="DEBUT"
     */
	Date getDebut();
	
	/**
	 * @socle.mapping.property column="FIRME_ID"
	 */
	Long getFirmeId();
	
	/**
	 * @socle.mapping.property column="TOTAL_FACTURE" 
	 */
	Decimal getTotalFacture();

}