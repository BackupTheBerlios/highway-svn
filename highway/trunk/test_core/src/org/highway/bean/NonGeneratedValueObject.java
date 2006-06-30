package org.highway.bean;
import org.highway.bean.ValueObjectAbstract;


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
