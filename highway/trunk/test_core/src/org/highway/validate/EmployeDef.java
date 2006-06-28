package org.highway.validate;

import org.highway.annotation.BeanPropertySize;
import org.highway.vo.ValueObject;

/**
 * @author attias
 */
public interface EmployeDef extends ValueObject
{
	Long getId();
	
	Firme getFirme();
	
	/**
	 * @socle.vo.property.min 2
	 * @socle.vo.property.max 5
	 */
	@BeanPropertySize(min=2, max=5)
	String getNom();
	
	String getPrenom();
}
