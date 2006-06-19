package com.manpower.socle.vo;

import java.util.Date;

import org.highway.vo.ValueObject;

/**
 * @author David Attias
 */
public interface BuildingDef extends ValueObject
{
	/**
	 * 
	 */
	long getId();
	
	/**
	 * @socle.vo.property.min 3
	 * @socle.vo.property.max 30
	 * @socle.vo.property.mandatory true
	 */
	String getName();
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	double getSurface();
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	Date getConstructionDate();
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	int getFloorNumber();
	
	/**
	 * 
	 */
	String[] getFirmeNames();
	
	/**
	 * @myapp.image.type gif
	 */
	byte[][] getFirmeLogos();	
}
