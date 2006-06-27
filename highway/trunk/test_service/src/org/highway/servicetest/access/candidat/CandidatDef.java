package org.highway.servicetest.access.candidat;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.annotation.VoSerialVersionUID;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="CANDIDAT"
 * @socle.vo.serialVersionUID 1
 * @socle.eai.request.method JDBC
 * @socle.eai.object.name Web_Candidat
 */
@VoMapping(table="CANDIDAT")
@VoSerialVersionUID(1)
@org.highway.annotation.ValueObject
public interface CandidatDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	@VoMappingId(column="ID", type="long")
	Long getId();

	/**
	 * @socle.mapping.property column="NAME"
	 */
	@VoMappingProperty(column="NAME", type="string")
	String getName();

}
