package com.manpower.socle.validate;

import java.util.Date;

import org.highway.helper.Wrapper;
import org.highway.validate.JavaBeanValidator;
import org.highway.validate.RangeValidator;
import org.highway.validate.ValidateContext;

/**
 * @author David Attias
 */
public class HumanValidator extends JavaBeanValidator
{
	/**
	 * Add basic custom validators on bean properties.
	 * Validators induce from metadata are automaticaly added.
	 */
	public HumanValidator()
	{
		this.addValidator(Human.BIRTH_DATE,
			new RangeValidator(null, false, new Date(), true));
	}
	
	/**
	 * Implement custum rules in this method.
	 * Add validation problems to the context.
	 */
	protected void validateObjectRules(Object object,
		ValidateContext context)
	{
		super.validateObjectRules(object, context);
		
		Human human = (Human) object;
		
		if ((human.getFirstName() != null) &&
			(human.getLastName() != null) &&
			(human.getFirstName().equals(human.getLastName())))
		{
			context.addProblem(this.getClass(), 
				Wrapper.toList(Human.FIRST_NAME, Human.LAST_NAME),
				false, "first and last name should not be equal");
		}
	}
}
