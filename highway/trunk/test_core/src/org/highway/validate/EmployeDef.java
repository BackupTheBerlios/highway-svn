package org.highway.validate;

import org.highway.bean.PropertySize;
import org.highway.bean.ValueObject;

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
	@PropertySize(min=2, max=5)
	String getNom();
	
	String getPrenom();
}
