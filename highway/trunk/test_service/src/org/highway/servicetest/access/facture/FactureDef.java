package org.highway.servicetest.access.facture;

import java.util.Date;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Mapped;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;

/**
 * @socle.mapping table="FACTURE"
 */
@Mapped(table="FACTURE")
@org.highway.annotation.ValueObject
public interface FactureDef extends ValueObject
{

	/**
	 * @socle.mapping.id column="ID"
	 */
	@Identity(column="ID", type="long")
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
