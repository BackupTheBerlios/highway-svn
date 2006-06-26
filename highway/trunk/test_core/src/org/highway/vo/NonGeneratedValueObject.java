package org.highway.vo;
import org.highway.vo.ValueObjectAbstract;


/**
 * @author attias
 */
public class NonGeneratedValueObject extends ValueObjectAbstract
{
	/**
	 * @socle.vo.property.min 5
	 * @socle.vo.property.max 15
	 */
	public String getName()
	{
		return "my name is dummy";
	}
}
