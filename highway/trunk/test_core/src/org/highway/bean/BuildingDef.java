package org.highway.bean;

import java.util.Date;

import org.highway.bean.MandatoryProperty;
import org.highway.bean.PropertySize;
import org.highway.bean.ValueObject;

/**
 * @author David Attias
 */
public interface BuildingDef extends ValueObject
{
	long getId();
	
	@PropertySize(min=3,max=30)
	@MandatoryProperty
	String getName();
	
	@MandatoryProperty
	double getSurface();
	
	@MandatoryProperty
	Date getConstructionDate();
	
	 @MandatoryProperty
	int getFloorNumber();
	
	String[] getFirmeNames();
	
	/**
	 * @myapp.image.type gif
	 */
	byte[][] getFirmeLogos();	
}
