package org.highway.validate;

import org.highway.vo.ValueObject;

/**
 * @author attias
 */
@org.highway.annotation.ValueObject
public interface EmployeDef extends ValueObject
{
	Long getId();
	
	Firme getFirme();
	
	/**
	 * @socle.vo.property.min 2
	 * @socle.vo.property.max 5
	 */
	String getNom();
	
	String getPrenom();
}
