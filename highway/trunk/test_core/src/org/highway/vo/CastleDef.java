package org.highway.vo;

import org.highway.annotation.BeanPropertySize;
import org.highway.annotation.VoBaseOnly;

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
	@BeanPropertySize(min=1, max=3)
	int getKeepHeight();
}
