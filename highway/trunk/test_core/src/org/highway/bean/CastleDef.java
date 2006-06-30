package org.highway.bean;

import org.highway.bean.PropertySize;
import org.highway.vogen.GenerateBaseOnly;

@GenerateBaseOnly
public interface CastleDef extends BuildingDef
{	
	public static final String TOWER_NUMBER = "towerNumber";
	
	@PropertySize(min=1, max=3)
	int getKeepHeight();
}
