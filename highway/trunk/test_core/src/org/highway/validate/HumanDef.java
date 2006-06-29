package org.highway.validate;

import java.util.Date;

import org.highway.bean.MandatoryProperty;
import org.highway.bean.PropertyPattern;
import org.highway.bean.PropertySize;
import org.highway.bean.ValueObject;

/**
 * @author David Attias
 */
public interface HumanDef extends ValueObject
{
	/**
	 * @socle.vo.property.min 4
	 * @socle.vo.property.max 20
	 * @socle.vo.property.mandatory true
	 */
	@PropertySize(min=4, max=20)
	@MandatoryProperty
	String getFirstName();
	
	/**
	 * @socle.vo.property.min 4
	 * @socle.vo.property.max 20
	 * @socle.vo.property.mandatory true
	 */
	@PropertySize(min=4, max=20)
	@MandatoryProperty
	String getLastName();
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	@MandatoryProperty
	Date getBirthDate();
	
	/**
	 * @socle.vo.property.pattern "[a-zA-Z0-9._]*@[a-zA-Z0-9._]*"
	 */
	@PropertyPattern("[a-zA-Z0-9._]*@[a-zA-Z0-9._]*")
	String getEmailAddress();	
}
