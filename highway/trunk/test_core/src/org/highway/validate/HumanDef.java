package org.highway.validate;

import java.util.Date;

import org.highway.annotation.BeanPropertyMandatory;
import org.highway.annotation.BeanPropertyPattern;
import org.highway.annotation.BeanPropertySize;
import org.highway.vo.ValueObject;

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
	@BeanPropertySize(min=4, max=20)
	@BeanPropertyMandatory
	String getFirstName();
	
	/**
	 * @socle.vo.property.min 4
	 * @socle.vo.property.max 20
	 * @socle.vo.property.mandatory true
	 */
	@BeanPropertySize(min=4, max=20)
	@BeanPropertyMandatory
	String getLastName();
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	@BeanPropertyMandatory
	Date getBirthDate();
	
	/**
	 * @socle.vo.property.pattern "[a-zA-Z0-9._]*@[a-zA-Z0-9._]*"
	 */
	@BeanPropertyPattern("[a-zA-Z0-9._]*@[a-zA-Z0-9._]*")
	String getEmailAddress();	
}
