package com.manpower.socle.vo;

public class Castle extends CastleBase
{
	public static final String TOWER_NUMBER = "towerNumber";
	
	private int towerNumber;
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	public int getTowerNumber()
	{
		return towerNumber;
	}

	public void setTowerNumber(int towerNumber)
	{
		this.towerNumber = towerNumber;
	}
}
