package com.manpower.socle.validate;

import java.util.Date;

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
	String getFirstName();
	
	/**
	 * @socle.vo.property.min 4
	 * @socle.vo.property.max 20
	 * @socle.vo.property.mandatory true
	 */
	String getLastName();
	
	/**
	 * @socle.vo.property.mandatory true
	 */
	Date getBirthDate();
	
	/**
	 * @socle.vo.property.pattern "[a-zA-Z0-9._]*@[a-zA-Z0-9._]*"
	 */
	String getEmailAddress();	
}
