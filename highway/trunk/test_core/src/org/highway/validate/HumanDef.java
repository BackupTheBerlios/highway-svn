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
	@PropertySize(min=4, max=20)
	@MandatoryProperty
	String getFirstName();
	
	@PropertySize(min=4, max=20)
	@MandatoryProperty
	String getLastName();
	
	@MandatoryProperty
	Date getBirthDate();
	
	@PropertyPattern("[a-zA-Z0-9._]*@[a-zA-Z0-9._]*")
	String getEmailAddress();	
}
