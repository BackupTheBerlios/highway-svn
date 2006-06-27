package org.highway.servicetest.access.projet;

import java.util.Date;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.Decimal;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="PROJET"
 */
@VoMapping(table="PROJET")
@org.highway.annotation.ValueObject
public interface ProjetDef extends ValueObject {
	
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID", type="long")
	Long getId();
	
	/**
	 * @socle.mapping.property column="NOM" 
	 */
	@VoMappingProperty(column="NOM", type="string")
	String getNom();

    /**
     * @socle.mapping.property column="DEBUT"
     */
	@VoMappingProperty(column="DEBUT", type="date")
	Date getDebut();
	
	/**
	 * @socle.mapping.property column="FIRME_ID"
	 */
	@VoMappingProperty(column="FIRME_ID", type="long")
	Long getFirmeId();
	
	/**
	 * @socle.mapping.property column="TOTAL_FACTURE" 
	 */
	@VoMappingProperty(column="TOTAL_FACTURE", type="decimal")
	Decimal getTotalFacture();

}