package org.highway.validate;

import org.highway.helper.Wrapper;
import org.highway.validate.JavaBeanValidator;
import org.highway.validate.ValidateContext;

/**
 * 
 */
public class EmployeValidator extends JavaBeanValidator
{
	public EmployeValidator()
	{
		//		addValidator(Employe.NOM, new SizeValidator(5, false));
		//		addValidator(Employe.NOM, new SizeValidator(2, true));
		setMandatory(Employe.NOM, true);
		setMandatory(Employe.PRENOM, true);
		setMandatory(Employe.FIRME, true);
	}

	protected void validateObjectRules(Object valueObject,
		ValidateContext context)
	{
		super.validateObjectRules(valueObject, context);

		Employe employe = (Employe) valueObject;
		if (employe.getNom() == null && employe.getPrenom() == null)
			context.addProblem(this.getClass(), Wrapper.toList(Employe.NOM,
				Employe.PRENOM), false);
	}
}
