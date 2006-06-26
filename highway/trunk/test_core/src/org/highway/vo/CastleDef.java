package org.highway.vo;

/**
 * @socle.vo.base.only
 */
public interface CastleDef extends BuildingDef
{	
	public static final String TOWER_NUMBER = "towerNumber";
	
	/**
	 * @socle.vo.property.min 1
	 * @socle.vo.property.max 3
	 */
	int getKeepHeight();
}
