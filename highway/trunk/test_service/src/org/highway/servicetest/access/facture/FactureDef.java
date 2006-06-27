package org.highway.servicetest.access.facture;

import java.util.Date;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.Decimal;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="FACTURE"
 */
@VoMapping(table="FACTURE")
public interface FactureDef extends ValueObject
{

	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID", type="long")
	Long getId();
	
    /**
     * @socle.mapping.property column="DATE_CREATION"
     */
	@VoMappingProperty(column="DATE_CREATION", type="date")
	Date getDateCreation();
	
    /**
     * @socle.mapping.property column="DEBUT"
     */
	@VoMappingProperty(column="DEBUT", type="date")
	Date getDebut();
	
    /**
     * @socle.mapping.property column="FIN"
     */
	@VoMappingProperty(column="FIN", type="date")
	Date getFin();
	
	/**
	 * @socle.mapping.property column="ECHEANCE" 
	 */
	@VoMappingProperty(column="ECHEANCE", type="FactureEcheance")
	FactureEcheance getEcheance();
	
	/**
	 * @socle.mapping.property column="MONTANT" 
	 */
	@VoMappingProperty(column="MONTANT", type="Decimal")
	Decimal getMontant();	

	/**
	 * @socle.mapping.property column="FIRME_ID"
	 */
	@VoMappingProperty(column="Decimal", type="long")
	Long getProjetId();

}
