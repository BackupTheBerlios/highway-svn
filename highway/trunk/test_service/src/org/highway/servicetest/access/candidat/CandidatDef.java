package org.highway.servicetest.access.candidat;

import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="CANDIDAT"
 * @socle.vo.serialVersionUID 1
 * @socle.eai.request.method JDBC
 * @socle.eai.object.name Web_Candidat
 */
public interface CandidatDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="ID"
	 */
	Long getId();

	/**
	 * @socle.mapping.property column="NAME"
	 */
	String getName();

}
