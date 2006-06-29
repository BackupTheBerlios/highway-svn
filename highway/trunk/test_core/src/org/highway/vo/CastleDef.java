package org.highway.vo;

import org.highway.annotation.VoBaseOnly;
import org.highway.bean.PropertySize;

/**
 * @socle.vo.base.only
 */
@VoBaseOnly
public interface CastleDef extends BuildingDef
{	
	public static final String TOWER_NUMBER = "towerNumber";
	
	/**
	 * @socle.vo.property.min 1
	 * @socle.vo.property.max 3
	 */
	@PropertySize(min=1, max=3)
	int getKeepHeight();
}
