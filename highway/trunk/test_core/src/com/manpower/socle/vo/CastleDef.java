package org.highway.vo;

import org.highway.annotation.VoMapping;

/**
 * @socle.vo.base.only
 */


@org.highway.annotation.ValueObject
@VoMapping(table="tableName")
public interface CastleDef extends BuildingDef
{	
	/**
	 * @socle.vo.property.min 1
	 * @socle.vo.property.max 3
	 */
	int getKeepHeight();
}
