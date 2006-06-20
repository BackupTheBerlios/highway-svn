package org.highway.validate;

import org.highway.validate.JavaBeanValidator;
import org.highway.validate.SizeValidator;

/**
 * @author David Attias
 */
public class FirmeValidator extends JavaBeanValidator
{
	public FirmeValidator()
	{
		setMandatory("nom", true);
		addValidator("nom", new SizeValidator(2, 5));
	}
}
