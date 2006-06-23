package org.highway.vo;

import java.util.Date;

import org.highway.annotation.BeanPropertyDescriptionLong;
import org.highway.annotation.BeanPropertyDescriptionShort;
import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.BeanPropertyPattern;
import org.highway.annotation.BeanPropertyReadOnly;
import org.highway.annotation.BeanPropertyScale;
import org.highway.annotation.BeanPropertySize;
import org.highway.annotation.BeanPropertyUpperCase;
import org.highway.annotation.VoMapping;
import org.highway.vo.ValueObject;

/**
 * @author David Attias
 */
@org.highway.annotation.ValueObject
@VoMapping(table="tableName")
public interface BuildingDef extends ValueObject
{
	/**
	 * 
	 */
	long getId();
	
	@BeanPropertySize(min=3,max=30)
	@BeanPropertyMandatory
	@BeanPropertyDescriptionLong("ceci est une description longue")
	@BeanPropertyDescriptionShort("ceci est une description courte")
	@BeanPropertyReadOnly
	@BeanPropertyUpperCase
	@BeanPropertyScale(100)
	@BeanPropertyPattern("jj/mm/aaaa")
	String getName();
	
	@BeanPropertyMandatory
	double getSurface();
	
	@BeanPropertyMandatory
	@BeanPropertyPattern("jj/mm/aaaa")
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
