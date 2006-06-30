package org.highway.validate;

import org.highway.bean.PropertySize;
import org.highway.bean.ValueObject;

/**
 * 
 */
public interface EmployeDef extends ValueObject
{
	Long getId();
	
	Firme getFirme();
	
	@PropertySize(min=2, max=5)
	String getNom();
	
	String getPrenom();
}
