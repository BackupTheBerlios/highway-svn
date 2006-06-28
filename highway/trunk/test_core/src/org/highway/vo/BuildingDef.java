package org.highway.vo;

import java.util.Date;

import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.BeanPropertySize;
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
	
	@BeanPropertySize(min=3,max=30)
	@BeanPropertyMandatory
	String getName();
	
	@BeanPropertyMandatory
	double getSurface();
	
	@BeanPropertyMandatory
	Date getConstructionDate();
	
	 @BeanPropertyMandatory
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
