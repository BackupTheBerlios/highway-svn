package org.highway.servicetest.access.facture;

import java.util.Date;

import org.highway.vo.Decimal;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="FACTURE"
 */
public interface FactureDef extends ValueObject
{

	/**
	 * @socle.mapping.id column="ID"
	 */
	Long getId();
	
    /**
     * @socle.mapping.property column="DATE_CREATION"
     */
	Date getDateCreation();
	
    /**
     * @socle.mapping.property column="DEBUT"
     */
	Date getDebut();
	
    /**
     * @socle.mapping.property column="FIN"
     */
	Date getFin();
	
	/**
	 * @socle.mapping.property column="ECHEANCE" 
	 */
	FactureEcheance getEcheance();
	
	/**
	 * @socle.mapping.property column="MONTANT" 
	 */
	Decimal getMontant();	

	/**
	 * @socle.mapping.property column="FIRME_ID"
	 */
	Long getProjetId();

}
